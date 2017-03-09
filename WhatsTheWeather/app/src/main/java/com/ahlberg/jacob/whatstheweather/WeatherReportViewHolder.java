package com.ahlberg.jacob.whatstheweather;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahlberg.jacob.whatstheweather.model.DailyWeatherReport;
import com.ahlberg.jacob.whatstheweather.model.WeeklyWeatherReport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ahlberg.jacob.whatstheweather.model.DailyWeatherReport.WEATHER_TYPE_LIGHTNING;

/**
 * Created by Jacob on 2017-03-05.
 */

public class WeatherReportViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_weather_icon) ImageView weatherIcon;
    @BindView (R.id.list_weather_day)TextView weatherDate;
    @BindView (R.id.list_weather_description)TextView weatherDescription;
    @BindView (R.id.list_temp_high)TextView tempHigh;
    @BindView (R.id.list_temp_low)TextView tempLow;

    public WeatherReportViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

}


