package com.ahlberg.jacob.whatstheweather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahlberg.jacob.whatstheweather.model.DailyWeatherReport;
import com.ahlberg.jacob.whatstheweather.model.Day;
import com.ahlberg.jacob.whatstheweather.model.Forecast;
import com.ahlberg.jacob.whatstheweather.model.WeeklyWeatherReport;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WeatherActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, LocationListener {
    final static String TAG = "TAG";

    final String URL_BASE = "https://api.darksky.net/forecast/";
    final String URL_API_KEY = "6c068b0a84758c6c217c0bce95c85504";
    final OkHttpClient okHttpClient = new OkHttpClient();

    private GoogleApiClient mGoogleApiClient;
    private final int PERMISSION_LOCATION = 111;
    private ArrayList<DailyWeatherReport> weatherReports = new ArrayList<>();
    private ArrayList<WeeklyWeatherReport> weeklyWeatherReports = new ArrayList<>();
    private boolean celcius = true;

    @BindView(R.id.weatherIcon) ImageView weatherIcon;
    @BindView(R.id.weatherDate) TextView weatherDate;
    @BindView(R.id.currentTemp) TextView currentTemp;
    @BindView(R.id.cityCountry) TextView cityCountry;
    @BindView(R.id.weatherDescription) TextView weatherDescription;
    @BindView(R.id.navigation) BottomNavigationView mBottomNav;
    @BindView(R.id.content_weather_reports) RecyclerView recyclerView;
    @BindView(R.id.my_toolbar) Toolbar myToolbar;

    WeatherAdapter mWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mWeatherAdapter = new WeatherAdapter(weeklyWeatherReports, this);
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

    void downloadWeatherData(Location location) {
        //https://api.darksky.net/forecast/6c068b0a84758c6c217c0bce95c85504/42.3601,-71.0589
        final String fullCoords = "/" + location.getLatitude() + "," + location.getLongitude();
        final String url = URL_BASE + URL_API_KEY + fullCoords;

        Log.e("URL________________", url);

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
                Forecast forecast = new Gson().fromJson(json, Forecast.class);

                String timeZone = forecast.getTimeZone();
                String weatherDescription = forecast
                        .getCurrentlyWeatherReport()
                        .getWeatherDescription();
                String weatherIcon = forecast
                        .getCurrentlyWeatherReport()
                        .getIcon();
                double latitude = forecast.getLatitude();
                double longitude = forecast.getLongitude();
                double temperatureFahrenheit = forecast
                        .getCurrentlyWeatherReport()
                        .getTemperature();
                int temperature = fahrenheitToCelcius(temperatureFahrenheit);

                DailyWeatherReport dailyReport = new DailyWeatherReport(timeZone,
                        weatherDescription, weatherIcon, latitude, longitude, temperature);
                weatherReports.add(dailyReport);

                List<Day> days = forecast.getWeeklyWeather().getWeeklyDays();

                for (Day day: days){
                    double fahrenheitMinTemp = day.getTemperatureMin();
                    int tempMin = fahrenheitToCelcius(fahrenheitMinTemp);
                    fahrenheitMinTemp = day.getTemperatureMax();
                    int tempMax = fahrenheitToCelcius(fahrenheitMinTemp);
                    WeeklyWeatherReport weeklyWeatherReport = new WeeklyWeatherReport(day.getSummary(),
                            day.getIcon(), tempMin, tempMax);
                    weeklyWeatherReports.add(weeklyWeatherReport);
                }

                WeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI();
                        mWeatherAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public static String getDayOfWeek(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        return format.format(date);
    }

    public void updateUI() {
        if (weatherReports.size() > 0) {
            DailyWeatherReport report = weatherReports.get(0);

            Drawable drawable = report.getDrawable(this);
            weatherIcon.setImageDrawable(drawable);

            String day = getDayOfWeek(Calendar.getInstance().getTime());
            String temperature = "" + report.getTemperature() + "°";
            currentTemp.setText(temperature);
            weatherDate.setText(day);
            cityCountry.setText(report.getLocation());
            weatherDescription.setText(report.getWeatherDescription());
        }
    }

    private int fahrenheitToCelcius(double temperature){
        if (celcius) return (int) ((((temperature - 32) * 5) / 9) + 0.5);
        else  return (int) temperature;
    }

    @Override
    public void onLocationChanged(Location location) {
        downloadWeatherData(location);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_LOCATION);
        } else {
            startLocationServices();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {   }

    @Override
    public void onConnectionSuspended(int i) {}

    public void startLocationServices() {
        try {
            LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this);

        } catch (SecurityException exception) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationServices();
                } else {
                    Toast.makeText(this, "Can't run your location without permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}