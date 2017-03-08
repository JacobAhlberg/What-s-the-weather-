package com.ahlberg.jacob.whatstheweather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahlberg.jacob.whatstheweather.model.DailyWeatherReport;

import java.util.ArrayList;

/**
 * Created by Jacob on 2017-03-05.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherReportViewHolder> {

    private ArrayList<DailyWeatherReport> dailyWeatherReports;


    public WeatherAdapter(ArrayList<DailyWeatherReport> dailyWeatherReports) {
        this.dailyWeatherReports = dailyWeatherReports;
    }

    @Override
    public WeatherReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_weather, parent, false);
        return new WeatherReportViewHolder(card, parent.getContext());
    }

    @Override
    public void onBindViewHolder(WeatherReportViewHolder holder, int position) {
        DailyWeatherReport report = dailyWeatherReports.get(position);
        holder.updateUI(report);
    }

    @Override
    public int getItemCount() {
        return dailyWeatherReports.size();
    }
}
