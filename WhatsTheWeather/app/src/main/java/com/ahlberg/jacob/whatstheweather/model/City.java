package com.ahlberg.jacob.whatstheweather.model;

/**
 * Created by Jacob on 2017-03-12.
 * What's The Weather?
 * Darksky API
 */

public class City {
    private double latitude;
    private double longitude;
    private String cityName;

    public City(double latitude, double longitude, String cityName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityName = cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCityName() {
        return cityName;
    }
}
