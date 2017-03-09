package com.ahlberg.jacob.whatstheweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ${User} on 2017-03-09.
 * What's The Weather?
 * SMHI API
 */

public class WeeklyDay {
    @SerializedName("0") private Day dayOne;
    @SerializedName("1") private Day dayTwo;
    @SerializedName("2") private Day dayThree;
    @SerializedName("3") private Day dayFour;
    @SerializedName("4") private Day dayFive;
    @SerializedName("5") private Day daySix;
    @SerializedName("6") private Day daySeven;
    @SerializedName("7") private Day dayEight;


    public Day getDayOne() {
        return dayOne;
    }

    public Day getDayTwo() {
        return dayTwo;
    }

    public Day getDayThree() {
        return dayThree;
    }

    public Day getDayFour() {
        return dayFour;
    }

    public Day getDayFive() {
        return dayFive;
    }

    public Day getDaySix() {
        return daySix;
    }

    public Day getDaySeven() {
        return daySeven;
    }

    public Day getDayEight() {
        return dayEight;
    }
}
