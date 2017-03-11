package com.ahlberg.jacob.whatstheweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jacob on 2017-03-08.
 * What's The Weather?
 * Darksky API
 */

public class Forecast {
    @SerializedName("latitude") private double latitude;
    @SerializedName("longitude") private double longitude;
    @SerializedName("timezone") private String timeZone;
    @SerializedName("currently") private CurrentlyWeatherReport currentlyWeatherReport;
    @SerializedName("daily") private WeeklyWeather weeklyWeather;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public CurrentlyWeatherReport getCurrentlyWeatherReport() {
        return currentlyWeatherReport;
    }

    public WeeklyWeather getWeeklyWeather() {
        return weeklyWeather;
    }
}
