package com.ahlberg.jacob.whatstheweather;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahlberg.jacob.whatstheweather.model.DailyWeatherReport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.ahlberg.jacob.whatstheweather.model.DailyWeatherReport.WEATHER_TYPE_LIGHTNING;

/**
 * Created by Jacob on 2017-03-05.
 */

public class WeatherReportViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ImageView weatherIcon;
    private TextView weatherDate;
    private TextView weatherDescription;
    private TextView tempHigh;
    private TextView tempLow;

    public WeatherReportViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;

        weatherIcon = (ImageView) itemView.findViewById(R.id.list_weather_icon);
        weatherDate = (TextView) itemView.findViewById(R.id.list_weather_day);
        weatherDescription = (TextView) itemView.findViewById(R.id.list_weather_description);
        tempHigh = (TextView) itemView.findViewById(R.id.list_temp_high);
        tempLow = (TextView) itemView.findViewById(R.id.list_temp_low);

    }

    public void updateUI (DailyWeatherReport report){

        switch (report.getWeatherIcon()){
            case DailyWeatherReport.WEATHER_TYPE_CLEAR_NIGHT:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.list_partially_cloudy));
                break;

            case DailyWeatherReport.WEATHER_TYPE_CLOUDY:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.list_cloudy));
                break;

            case DailyWeatherReport.WEATHER_TYPE_CLOUDY_DAY:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.list_cloudy));
                break;

            case DailyWeatherReport.WEATHER_TYPE_CLOUDY_NIGHT:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.list_cloudy));
                break;

            case DailyWeatherReport.WEATHER_TYPE_RAIN:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.list_rainy));
                break;

            case DailyWeatherReport.WEATHER_TYPE_SNOW:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.list_snow));
                break;

            case DailyWeatherReport.WEATHER_TYPE_SLEET:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.snow)); //NEW PICTURE PLEASE
                break;

            case DailyWeatherReport.WEATHER_TYPE_WIND:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cloudy)); // NEW PICTURE PLEASE
                break;

            case DailyWeatherReport.WEATHER_TYPE_FOG:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.list_fog));
                break;

            case DailyWeatherReport.WEATHER_TYPE_HAIL:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cloudy)); // NEW PICTURE PLEASE
                break;

            case DailyWeatherReport.WEATHER_TYPE_LIGHTNING:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.list_thunder_lightning));
                break;

            case DailyWeatherReport.WEATHER_TYPE_TORNADO:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.cloudy)); // NEW PICTURE PLEASE
                break;

            default:
                weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.list_sunny));
        }

        




    }

}


