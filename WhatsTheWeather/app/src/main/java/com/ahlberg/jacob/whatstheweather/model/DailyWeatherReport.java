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

    private String timeZone;
    private String weatherDescription;
    private double latitude;
    private double longitude;
    private double temperature;

    public DailyWeatherReport(String timeZone, String weatherDescription,
                              double latitude, double longitude,
                              double temperature) {

        this.timeZone = timeZone;
        this.weatherDescription = weatherDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getTemperature() {
        return temperature;
    }
}
