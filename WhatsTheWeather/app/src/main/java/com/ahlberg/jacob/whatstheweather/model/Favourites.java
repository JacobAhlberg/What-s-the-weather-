package com.ahlberg.jacob.whatstheweather.model;

/**
 * Created by Jacob on 2017-03-11.
 * What's The Weather?
 * Darksky API
 */

public class Favourites {
    private double longitude;
    private double latitude;
    private String state;
    private String weatherDescription;

    public Favourites(double longitude, double latitude, String state) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.state = state;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getState() {
        return state;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }
}
