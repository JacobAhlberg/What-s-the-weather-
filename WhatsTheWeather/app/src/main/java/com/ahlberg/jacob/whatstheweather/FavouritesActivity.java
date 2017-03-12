package com.ahlberg.jacob.whatstheweather;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahlberg.jacob.whatstheweather.model.Favourites;
import com.ahlberg.jacob.whatstheweather.model.Forecast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FavouritesActivity extends AppCompatActivity {
    final static String TAG = "TAG___________________";

    final String URL_BASE = "https://api.darksky.net/forecast/";
    final String URL_API_KEY = "6c068b0a84758c6c217c0bce95c85504";
    final OkHttpClient okHttpClient = new OkHttpClient();
    private String rightLocation;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabase = database.getReference();

    @BindView(R.id.latitudeTxt)         EditText latitudeTxt;
    @BindView(R.id.longitudeTxt)        EditText longitudeTxt;
    @BindView(R.id.addBtn)              Button addBtn;
    @BindView(R.id.content_favourites)  RecyclerView recyclerView;

    private ArrayList<Favourites> favourites = new ArrayList<>();
    FavouritesAdapter favouritesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        ButterKnife.bind(this);

        favouritesAdapter = new FavouritesAdapter(favourites, this);
        recyclerView.setAdapter(favouritesAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude = 0;
                double longitude = 0;
                try {
                    latitude = Double.parseDouble(latitudeTxt.getText().toString());
                    longitude = Double.parseDouble(longitudeTxt.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(FavouritesActivity.this, "Only numbers", Toast.LENGTH_LONG).show();
                }
                if (latitude >= -180 || latitude <= 180 &&
                        longitude >= -180 && longitude <= 180) {
                    findLocation(latitude, longitude);
                    Toast.makeText(FavouritesActivity.this, rightLocation, Toast.LENGTH_LONG).show();
                    mDatabase.child("City").setValue("\"" + rightLocation + "\"");
                    mDatabase.child("City").child("\"" + rightLocation + "\"").child("latitude").setValue(latitude);
                    mDatabase.child("City").child("\"" + rightLocation + "\"").child("longitude").setValue(longitude);
                }
                else Toast.makeText(FavouritesActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
            }



        });


     mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {


//             Favourites newFavourite = new Favourites(latitude, longitude, state);
//             favourites.add(newFavourite);
//             favouritesAdapter.notifyDataSetChanged();

         }

         @Override
         public void onCancelled(DatabaseError databaseError) {

         }
     });
    }

    /*
    * Gets the location depending on which latitude and longitude has as parameters
    * then grabs the data from Darksky.net
    * On response, convert the JSON and then by using Gson we can grab the data
    * easily from the classes i've made. Exactly the same as before when we were getting
    * current location from previous example.
    * */
    public void findLocation(double latitude, double longitude){
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
                rightLocation = city.getTimeZone();
            }
        });

    }
}
