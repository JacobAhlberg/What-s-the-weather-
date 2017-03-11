package com.ahlberg.jacob.whatstheweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jacob on 2017-03-09.
 * What's The Weather?
 * Darksky API
 */

public class Day {

    @SerializedName("summary") private String summary;
    @SerializedName("icon") private String icon;
    @SerializedName("temperatureMin") private double temperatureMin;
    @SerializedName("temperatureMax") private double temperatureMax;

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

}
