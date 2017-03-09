package com.ahlberg.jacob.whatstheweather.model;

import java.util.Calendar;

/**
 * Created by ${User} on 2017-03-09.
 * What's The Weather?
 * SMHI API
 */

public class Weekdays {

    public static String getDayOfWeek(){

        Calendar calendar = Calendar.getInstance();
        int dayInt = calendar.get(Calendar.DAY_OF_WEEK);
        String day = "";

        switch (dayInt) {
            case Calendar.SUNDAY:
                day = "Sunday";
                break;

            case Calendar.MONDAY:
                day = "Monday";
                break;

            case Calendar.TUESDAY:
                day = "Tuesday";
                break;

            case Calendar.WEDNESDAY:
                day = "Wednesday";
                break;

            case Calendar.THURSDAY:
                day = "Thursday";
                break;

            case Calendar.FRIDAY:
                day = "Friday";
                break;

            case Calendar.SATURDAY:
                day = "Saturday";
                break;
        }

        return day;
    }
}
