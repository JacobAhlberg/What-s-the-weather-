package com.ahlberg.jacob.whatstheweather.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.ahlberg.jacob.whatstheweather.R;

/**
 * Created by ${User} on 2017-03-09.
 * What's The Weather?
 * SMHI API
 */

public class WeeklyWeatherReport {
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

    private String summary;
    private String icon;
    private int temperatureMin;
    private int temperatureMax;

    public WeeklyWeatherReport(String summary, String icon,
                               int temperatureMin, int temperatureMax) {

        this.summary = summary;
        this.icon = icon;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public int getTemperatureMin() {
        return temperatureMin;
    }

    public int getTemperatureMax() {
        return temperatureMax;
    }

    public Drawable getDrawableToList (Context context){

        switch (getIcon()){
            case WEATHER_TYPE_CLEAR_NIGHT:  return ContextCompat.getDrawable(context, R.drawable.list_partially_cloudy);
            case WEATHER_TYPE_CLOUDY:       return ContextCompat.getDrawable(context, R.drawable.list_cloudy);
            case WEATHER_TYPE_CLOUDY_DAY:   return ContextCompat.getDrawable(context, R.drawable.list_cloudy);
            case WEATHER_TYPE_CLOUDY_NIGHT: return ContextCompat.getDrawable(context, R.drawable.list_cloudy);
            case WEATHER_TYPE_RAIN:         return ContextCompat.getDrawable(context, R.drawable.list_rainy);
            case WEATHER_TYPE_SNOW:         return ContextCompat.getDrawable(context, R.drawable.list_snow);
            case WEATHER_TYPE_SLEET:        return ContextCompat.getDrawable(context, R.drawable.list_snow); //NEW PICTURE PLEASE
            case WEATHER_TYPE_WIND:         return ContextCompat.getDrawable(context, R.drawable.list_cloudy); // NEW PICTURE PLEASE
            case WEATHER_TYPE_FOG:          return ContextCompat.getDrawable(context, R.drawable.list_fog);
            case WEATHER_TYPE_HAIL:         return ContextCompat.getDrawable(context, R.drawable.list_cloudy); // NEW PICTURE PLEASE
            case WEATHER_TYPE_LIGHTNING:    return ContextCompat.getDrawable(context, R.drawable.list_thunder_lightning);
            case WEATHER_TYPE_TORNADO:      return ContextCompat.getDrawable(context, R.drawable.list_cloudy); // NEW PICTURE PLEASE
            default:                        return ContextCompat.getDrawable(context, R.drawable.list_sunny);
        }


    }


}
