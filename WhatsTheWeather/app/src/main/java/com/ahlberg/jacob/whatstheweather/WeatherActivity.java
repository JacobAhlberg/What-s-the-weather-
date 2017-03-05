package com.ahlberg.jacob.whatstheweather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahlberg.jacob.whatstheweather.model.DailyWeatherReport;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class WeatherActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, LocationListener
{

    //Hardcoded location: ?lat=9.9687&lon=76.299";

    final String URL_BASE = "http://api.openweathermap.org/data/2.5/forecast";
    final String URL_COORDS = "/?lat=";
    final String URL_UNITS_CELSIUS = "&units=metric";
    final String URL_UNITS_FAHRENHEIT = "&units=imperial";
    final String URL_API_KEY = "&APPID=6293987fb4d85b38ac93029090356751";

    private GoogleApiClient mGoogleApiClient;
    private final int PERMISSION_LOCATION = 111;
    private ArrayList<DailyWeatherReport> weatherReports = new ArrayList<>();
    private BottomNavigationView mBottomNav;
    private int mSelectedItem;

    private ImageView weatherIcon;
    private TextView weatherDate;
    private TextView currentTemp;
    private TextView lowTemp;
    private TextView cityCountry;
    private TextView weatherDescription;

    WeatherAdapter mWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherIcon = (ImageView)findViewById(R.id.weatherIcon);
        weatherDate = (TextView)findViewById(R.id.weatherDate);
        currentTemp = (TextView)findViewById(R.id.currentTemp);
        lowTemp = (TextView)findViewById(R.id.lowTemp);
        cityCountry = (TextView)findViewById(R.id.cityCountry);
        weatherDescription = (TextView)findViewById(R.id.weatherDescription);

        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.content_weather_reports);
//        WeatherReportViewHolder weatherReportViewHolder = new WeatherReportViewHolder(, this);

        mWeatherAdapter = new WeatherAdapter(weatherReports);
        recyclerView.setAdapter(mWeatherAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


    }

    void downloadWeatherData(Location location){
        final String fullCoords = URL_COORDS + location.getLatitude() + "&lon=" + location.getLongitude();;
        final String url = URL_BASE + fullCoords  + URL_UNITS_CELSIUS + URL_API_KEY;

        //Getting a json object back
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    Log.e("JSON", response.toString());
                        try {
                            JSONObject city = response.getJSONObject("city");
                            String cityName = city.getString("name");
                            String country = city.getString("country");
                            JSONArray list = response.getJSONArray("list");

                            for (int i = 0; i < 5; i++){
                                JSONObject obj = list.getJSONObject(i);
                                JSONObject main = obj.getJSONObject("main");
                                Double currentTemp = main.getDouble("temp");
                                Double maxTemp = main.getDouble("temp_max");
                                Double minTemp = main.getDouble("temp_min");

                                JSONArray weatherArr = obj.getJSONArray("weather");
                                JSONObject weather = weatherArr.getJSONObject(0);
                                String weatherType = weather.getString("main");

                                String rawDate = obj.getString("dt_txt");

                                DailyWeatherReport dailyWeatherReport = new DailyWeatherReport(cityName,
                                        country, rawDate, maxTemp.intValue(), minTemp.intValue(),
                                        currentTemp.intValue(), weatherType);

                                weatherReports.add(dailyWeatherReport);
                            }

                        } catch (JSONException e){

                        }

                        updateUI();
                        mWeatherAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);
    }

    public void updateUI(){
        if (weatherReports.size() > 0){
            DailyWeatherReport report = weatherReports.get(0);

            switch (report.getWeather()){
                case DailyWeatherReport.WEATHER_TYPE_CLOUDS:
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
                    break;

                case DailyWeatherReport.WEATHER_TYPE_RAIN:
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.rainy));
                    break;

                case DailyWeatherReport.WEATHER_TYPE_SNOW:
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.snow));
                    break;

                default:
                        weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.sunlogo));
            }

            weatherDate.setText(report.getFormattedDate());
            currentTemp.setText(Integer.toString(report.getTemp()) + "º");
            lowTemp.setText(Integer.toString(report.getMinTemp()) + "º");
            cityCountry.setText(report.getCityName() + ", " + report.getCountry());
            weatherDescription.setText(report.getWeather());
        }


    }

    @Override
    public void onLocationChanged(Location location) {
        downloadWeatherData(location);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_LOCATION);
        }
        else {
            startLocationServices();
        }



    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public void startLocationServices(){
        try{
            LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_LOW_POWER);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this);

        } catch (SecurityException exception){

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startLocationServices();
                }
                else{
                    Toast.makeText(this, "Can't run your location without permission", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

}


