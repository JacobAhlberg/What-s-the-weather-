package com.ahlberg.jacob.whatstheweather.model;

/**
 * Created by Jacob on 2017-03-12.
 * What's The Weather?
 * Darksky API
 */

public class City {
    private double latitude;
    private double longitude;
    private String city;

    public City() {}

    public City(double latitude, double longitude, String city) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
