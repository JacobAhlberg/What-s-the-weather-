package com.ahlberg.jacob.whatstheweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Jacob on 2017-03-05.
 * What's The Weather?
 * Darksky API
 */

class WeatherReportViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_weather_icon) ImageView weatherIcon;
    @BindView (R.id.list_weather_day)TextView weatherDate;
    @BindView (R.id.list_weather_description)TextView weatherDescription;
    @BindView (R.id.list_temp_high)TextView tempHigh;
    @BindView (R.id.list_temp_low)TextView tempLow;

    WeatherReportViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

}


