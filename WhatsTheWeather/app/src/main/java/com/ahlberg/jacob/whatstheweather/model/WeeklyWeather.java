package com.ahlberg.jacob.whatstheweather.model;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.ahlberg.jacob.whatstheweather.R;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jacob on 2017-03-08.
 * What's The Weather?
 * Darksky API
 */

public class WeeklyWeather{
    @SerializedName("summary") private String summary;
    @SerializedName("icon") private String weatherType;
    @SerializedName("data") private List<Day> weeklyDays;

    public List<Day> getWeeklyDays() {
        return weeklyDays;
    }
}
