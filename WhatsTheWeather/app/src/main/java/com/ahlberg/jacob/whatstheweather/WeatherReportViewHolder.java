package com.ahlberg.jacob.whatstheweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahlberg.jacob.whatstheweather.model.DailyWeatherReport;

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

        weatherDate.setText(report.getFormattedDate());

        switch (report.getWeather()){
            case DailyWeatherReport.WEATHER_TYPE_CLOUDS:
                weatherIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.cloudy));
                break;

            case DailyWeatherReport.WEATHER_TYPE_RAIN:
                weatherIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.rainy));
                break;

            case DailyWeatherReport.WEATHER_TYPE_SNOW:
                weatherIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.snow));
                break;

            default:
                weatherIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.sunlogo));
        }
    }

}
