package com.ahlberg.jacob.whatstheweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jacob on 2017-03-08.
 * What's The Weather?
 * Darksky API
 */

public class CurrentlyWeatherReport {
    @SerializedName("summary") private String weatherDescription;
    @SerializedName("icon") private String icon;
    @SerializedName("temperature") private double temperature;

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getIcon() {
        return icon;
    }

    public double getTemperature() {
        return temperature;
    }
}
