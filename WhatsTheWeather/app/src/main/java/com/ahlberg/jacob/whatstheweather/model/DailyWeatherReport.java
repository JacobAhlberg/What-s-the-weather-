package com.ahlberg.jacob.whatstheweather.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.ahlberg.jacob.whatstheweather.R;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jacob on 2017-03-01.
 * What's The Weather?
 * Darksky API
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

    public static String location(String timeZone){
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

    public Drawable getDrawable(Context context){
        switch (getWeatherIcon()) {
            case WEATHER_TYPE_CLEAR_NIGHT:  return ContextCompat.getDrawable(context, R.drawable.partially_cloudy);
            case WEATHER_TYPE_CLOUDY:       return ContextCompat.getDrawable(context, R.drawable.cloudy);
            case WEATHER_TYPE_CLOUDY_DAY:   return ContextCompat.getDrawable(context, R.drawable.cloudy);
            case WEATHER_TYPE_CLOUDY_NIGHT: return ContextCompat.getDrawable(context, R.drawable.cloudy);
            case WEATHER_TYPE_RAIN:         return ContextCompat.getDrawable(context, R.drawable.rainy);
            case WEATHER_TYPE_SNOW:         return ContextCompat.getDrawable(context, R.drawable.snow);
            case WEATHER_TYPE_SLEET:        return ContextCompat.getDrawable(context, R.drawable.snow); //NEW PICTURE PLEASE
            case WEATHER_TYPE_WIND:         return ContextCompat.getDrawable(context, R.drawable.cloudy); // NEW PICTURE PLEASE
            case WEATHER_TYPE_FOG:          return ContextCompat.getDrawable(context, R.drawable.fog);
            case WEATHER_TYPE_HAIL:         return ContextCompat.getDrawable(context, R.drawable.snow); // NEW PICTURE PLEASE
            case WEATHER_TYPE_LIGHTNING:    return ContextCompat.getDrawable(context, R.drawable.thunder_lightning);
            case WEATHER_TYPE_TORNADO:      return ContextCompat.getDrawable(context, R.drawable.cloudy);
            default:                        return ContextCompat.getDrawable(context, R.drawable.sunny);
        }

    }

    public static boolean getTimeOfTheDay() {
        LocalTime now = LocalTime.now();
        LocalTime limit = new LocalTime("15:00");
        return now.isAfter(limit);
    }

    public static String getDayOfWeek(Date date) {
        @SuppressLint("SimpleDateFormat")   //Getting the current day
                SimpleDateFormat format = new SimpleDateFormat("EEEE");
        return format.format(date);
    }

    public static int fahrenheitToCelsius(boolean celsius, int temperature){
        if (celsius) return (int) ((((temperature - 32) * 5) / 9) + 0.5);
        else  return temperature;
    }
}
