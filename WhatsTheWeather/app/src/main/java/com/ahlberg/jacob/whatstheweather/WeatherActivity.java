package com.ahlberg.jacob.whatstheweather;

import android.Manifest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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
import java.util.ArrayList;
import java.util.Calendar;
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

    final String URL_BASE = "https://api.darksky.net/forecast/";
    final String URL_API_KEY = "6c068b0a84758c6c217c0bce95c85504";
    final OkHttpClient okHttpClient = new OkHttpClient();

    private String today;
    private GoogleApiClient mGoogleApiClient;
    private final int PERMISSION_LOCATION = 111;
    private ArrayList<DailyWeatherReport> weatherReports = new ArrayList<>();
    private ArrayList<WeeklyWeatherReport> weeklyWeatherReports = new ArrayList<>();
    private List<Day> days;
    private boolean celsius = true;

    @BindView(R.id.weatherIcon)             ImageView weatherIcon;
    @BindView(R.id.weatherDate)             TextView weatherDate;
    @BindView(R.id.currentTemp)             TextView currentTemp;
    @BindView(R.id.cityCountry)             TextView cityCountry;
    @BindView(R.id.weatherDescription)      TextView weatherDescription;
    @BindView(R.id.navigation)              BottomNavigationView mBottomNav;
    @BindView(R.id.content_weather_reports) RecyclerView recyclerView;
    @BindView (R.id.settingsBtn)            ImageButton settingsBtn;
    @BindView(R.id.my_toolbar)              Toolbar myToolbar;
    @BindView(R.id.activity_weather)        ConstraintLayout background;

    WeatherAdapter mWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        Boolean late = DailyWeatherReport.getTimeOfTheDay();
        if (!late) background.setBackground(ContextCompat.getDrawable(this,
                R.drawable.morning_background));


        setSupportActionBar(myToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        celsius = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("degrees", false);

        Intent checkInternetIntent = new Intent();
        checkInternetIntent.setAction("internet");
        sendBroadcast(checkInternetIntent);


        mWeatherAdapter = new WeatherAdapter(weeklyWeatherReports, this);
        recyclerView.setAdapter(mWeatherAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(WeatherActivity.this, settingsBtn);
                popupMenu.getMenuInflater()
                        .inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String menuItem = "" + item.getTitle();
                        switch (menuItem){
                            case "Settings" :
                                Intent intent = new Intent(WeatherActivity.this,
                                        SettingsActivity.class);
                                intent.putExtra("today", today);
                                startActivity(intent);
                                break;
                            case "Help" :
                                Toast.makeText(WeatherActivity.this, "Not yet implemented", Toast.LENGTH_LONG)
                                        .show();
                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    void downloadWeatherData(Location location) {
        final String fullCoordinates = "/" + location.getLatitude() + "," + location.getLongitude();
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
                int temperature = (int) (temperatureFahrenheit + 0.5);

                DailyWeatherReport dailyReport = new DailyWeatherReport(timeZone,
                        weatherDescription, weatherIcon, latitude, longitude, temperature);
                weatherReports.add(dailyReport);

                days = forecast.getWeeklyWeather().getWeeklyDays();

                for (Day day: days){
                    int tempMin = (int) day.getTemperatureMin();
                    int tempMax = (int) day.getTemperatureMax();
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

    public void updateUI() {
        if (weatherReports.size() > 0) {
            DailyWeatherReport report = weatherReports.get(0);

            Drawable drawable = report.getDrawable(this);
            weatherIcon.setImageDrawable(drawable);

            String day = DailyWeatherReport
                    .getDayOfWeek(Calendar.getInstance().getTime());
            today = day;

            int degrees = DailyWeatherReport.fahrenheitToCelsius(celsius, report.getTemperature());
            String temperature = degrees + "°";
            currentTemp.setText(temperature);
            weatherDate.setText(day);
            cityCountry.setText(report.getLocation());
            weatherDescription.setText(report.getWeatherDescription());
        }
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
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    @Override
    public void onConnectionSuspended(int i) {}

    public void startLocationServices() {
        try {
            LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this);

        } catch (SecurityException ignored) { }
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

    @Override
    protected void onPause() {
        super.onPause();
        if (weeklyWeatherReports != null)   weeklyWeatherReports.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        celsius = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("degrees", false);
        updateUI();
        mWeatherAdapter.notifyDataSetChanged();
    }

}