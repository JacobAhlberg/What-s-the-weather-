package com.ahlberg.jacob.whatstheweather;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahlberg.jacob.whatstheweather.model.WeeklyWeatherReport;

import java.util.ArrayList;

/**
 * Created by Jacob on 2017-03-05.
 * What's The Weather?
 * Darksky API
 */

class WeatherAdapter extends RecyclerView.Adapter<WeatherReportViewHolder> {
    private Context context;
    private ArrayList<WeeklyWeatherReport> weeklyWeatherReports;

    WeatherAdapter(ArrayList<WeeklyWeatherReport> weeklyWeatherReport, Context context) {
        this.weeklyWeatherReports = weeklyWeatherReport;
        this.context = context;
    }

    @Override
    public WeatherReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_weather, parent, false);
        return new WeatherReportViewHolder(card, parent.getContext());
    }

    @Override
    public void onBindViewHolder(WeatherReportViewHolder holder, int position) {
        WeeklyWeatherReport report = weeklyWeatherReports.get(position);
        holder.weatherIcon.setImageDrawable(report.getDrawableToList(context));

        Boolean degree = PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("degrees", false);
        String tempMin = "" + WeeklyWeatherReport.getRightDegreeMin(report.getTemperatureMin(), degree);
        holder.tempLow.setText(tempMin);
        String tempMax = "" + WeeklyWeatherReport.getRightDegreeMax(report.getTemperatureMax(), degree);
        holder.tempHigh.setText(tempMax);

        holder.weatherDescription.setText(report.getSummary());
        String day = WeeklyWeatherReport.getDaysOfWeek(position + 1);
        holder.weatherDate.setText(day);

    }

    @Override
    public int getItemCount() {
        return weeklyWeatherReports.size();
    }
}
