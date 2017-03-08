package com.ahlberg.jacob.whatstheweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ${User} on 2017-03-08.
 * What's The Weather?
 * SMHI API
 */

public class Forecast {
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("timezone")
    private String timeZone;

    @SerializedName("currently")
    private CurrentlyWeatherReport currentlyWeatherReport;

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
}
