package com.ahlberg.jacob.whatstheweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ${User} on 2017-03-09.
 * What's The Weather?
 * SMHI API
 */

public class WeeklyWeatherReport {
    @SerializedName("summary") private String summary;
    @SerializedName("icon") private String icon;
    @SerializedName("data") private List<WeeklyDay> weeklyDays;

    public List<WeeklyDay> getWeeklyDays() {
        return weeklyDays;
    }
}
