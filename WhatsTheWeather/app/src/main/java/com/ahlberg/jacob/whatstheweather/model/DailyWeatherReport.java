package com.ahlberg.jacob.whatstheweather.model;

/**
 * Created by Jacob on 2017-03-01.
 */

public class DailyWeatherReport {
    public final static String WEATHER_TYPE_CLOUDS = "Clouds";
    public final static String WEATHER_TYPE_LIGHTNING = "Lightning";
    public final static String WEATHER_TYPE_CLEAR = "Clear";
    public final static String WEATHER_TYPE_RAIN = "Rain";
    public final static String WEATHER_TYPE_WIND = "Wind";
    public final static String WEATHER_TYPE_SNOW = "Snow";

    private String cityName;
    private String country;
    private String weather;
    private String formattedDate;
    private int temp;
    private int maxTemp;
    private int minTemp;

    public DailyWeatherReport(String cityName, String country,
                              String rawDate, int maxTemp,
                              int minTemp, int temp, String weather) {
        this.cityName = cityName;
        this.country = country;
        this.formattedDate = rawDateToFormatted(rawDate);
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.temp = temp;
        this.weather = weather;
    }

    public String rawDateToFormatted (String rawDate){

        return rawDate;
    }

    public String getCityName() {return cityName;}

    public String getCountry() {return country;}

    public String getFormattedDate() {return formattedDate;}

    public int getMaxTemp() {return maxTemp;}

    public int getMinTemp() {return minTemp;}

    public int getTemp() {return temp;}

    public String getWeather() {return weather;}

}
