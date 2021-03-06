package com.ahlberg.jacob.whatstheweather;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahlberg.jacob.whatstheweather.model.City;
import com.ahlberg.jacob.whatstheweather.model.DailyWeatherReport;
import com.ahlberg.jacob.whatstheweather.model.Forecast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FavouritesActivity extends AppCompatActivity implements Runnable {

    public FavouritesActivity(){ }

    final static String TAG = "TAG___________________";

    FirebaseRecyclerAdapter <City, FavouritesViewHolder> mAdapter = null;

    final String URL_BASE = "https://api.darksky.net/forecast/";
    final String URL_API_KEY = "6c068b0a84758c6c217c0bce95c85504";
    final OkHttpClient okHttpClient = new OkHttpClient();
    String location;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDataRefs = database.getReference();
    DatabaseReference dbLocationRefs;


    @BindView(R.id.latitudeTxt)         EditText latitudeTxt;
    @BindView(R.id.longitudeTxt)        EditText longitudeTxt;
    @BindView(R.id.addBtn)              Button addBtn;
    @BindView(R.id.content_favourites)  RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        ButterKnife.bind(this);

        dbLocationRefs = database.getReference().child("location");
        setUpFirebaseAdapter();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude = Double.parseDouble(latitudeTxt.getText().toString());
                double longitude = Double.parseDouble(longitudeTxt.getText().toString());

                if (latitude >= -180 || latitude <= 180 &&
                        longitude >= -180 && longitude <= 180) {
                    setAndFindLocation(latitude, longitude);
                }
                else Toast.makeText(FavouritesActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpFirebaseAdapter(){
        mAdapter = new FirebaseRecyclerAdapter<City, FavouritesViewHolder>(
                City.class,
                R.layout.card_favourites,
                FavouritesViewHolder.class,
                dbLocationRefs
        ) {
            @Override
            protected void populateViewHolder(FavouritesViewHolder viewHolder, City model, int position) {
                viewHolder.setCity(model.getCity());
                viewHolder.setLatitudeResult(model.getLatitude());
                viewHolder.setLongitudeResult(model.getLongitude());
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    /*
    * Gets the location depending on which latitude and longitude has as parameters
    * then grabs the data from Darksky.net
    * On response, convert the JSON and then by using Gson we can grab the data
    * easily from the classes i've made. Exactly the same as before when we were getting
    * current location from previous example.
    * */

    public void setAndFindLocation(final double latitude, final double longitude){
        final String fullCoordinates = "/" + latitude + "," + longitude;
        final String url = URL_BASE + URL_API_KEY + fullCoordinates;

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Forecast city = new Gson().fromJson(json, Forecast.class);
                String countryCity = city.getTimeZone();
                location = DailyWeatherReport.location(countryCity);

                FavouritesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String city = location;
                        Toast.makeText(FavouritesActivity.this, city + " added to favourites", Toast.LENGTH_LONG).show();
                        mDataRefs.child("location").child(city)
                                .child("latitude").setValue(latitude);
                        mDataRefs.child("location").child(city)
                                .child("longitude").setValue(longitude);
                        mDataRefs.child("location").child(city)
                                .child("city").setValue(city);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpFirebaseAdapter();
    }

    @Override
    public void run() {}
}
