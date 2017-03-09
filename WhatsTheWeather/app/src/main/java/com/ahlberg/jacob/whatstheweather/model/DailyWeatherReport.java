package com.ahlberg.jacob.whatstheweather.model;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Jacob on 2017-03-01.
 */

public class DailyWeatherReport {

    public final static String WEATHER_TYPE_CLEAR_DAY = "clear-day";
    public final static String WEATHER_TYPE_CLEAR_NIGHT = "clear-night";
    public final static String WEATHER_TYPE_RAIN = "rain";
    public final static String WEATHER_TYPE_SNOW = "snow";
    public final static String WEATHER_TYPE_SLEET = "sleet";
    public final static String WEATHER_TYPE_WIND = "wind";
    public final static String WEATHER_TYPE_FOG = "fog";
    public final static String WEATHER_TYPE_CLOUDY = "cloudy";
    public final static String WEATHER_TYPE_CLOUDY_DAY = "partly-cloudy-day";
    public final static String WEATHER_TYPE_CLOUDY_NIGHT = "partly-cloudy-night";
    public final static String WEATHER_TYPE_HAIL = "hail";
    public final static String WEATHER_TYPE_LIGHTNING = "thunderstorm";
    public final static String WEATHER_TYPE_TORNADO = "tornado";

    private String location;
    private String weatherDescription;
    private String weatherIcon;
    private double latitude;
    private double longitude;
    private int temperature;

    public DailyWeatherReport(String timeZone, String weatherDescription,
                              String weatherIcon, double latitude,
                              double longitude, int temperature) {

        this.location = location(timeZone);
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
    }

    private String location(String timeZone){
        String[] split = timeZone.split("/");
        return split[1];
    }

    public String getLocation() {
        return location;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getTemperature() {
        return temperature;
    }
}
