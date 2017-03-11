package com.ahlberg.jacob.whatstheweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahlberg.jacob.whatstheweather.model.DailyWeatherReport;
import com.ahlberg.jacob.whatstheweather.model.WeeklyWeatherReport;

import java.util.ArrayList;

/**
 * Created by Jacob on 2017-03-05.
 * What's The Weather?
 * SMHI API
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
        Toast.makeText(context, "" + degree, Toast.LENGTH_LONG).show();

        String tempMin = "" + WeeklyWeatherReport.getRightDegreeMin(report
                .getTemperatureMin(), degree);
        Toast.makeText(context, tempMin, Toast.LENGTH_LONG).show();
        holder.tempLow.setText(tempMin);

        String tempMax = "" + WeeklyWeatherReport.getRightDegreeMax(report
                .getTemperatureMax(), degree);
        holder.tempHigh.setText(tempMax);
        holder.weatherDescription.setText(report.getSummary());
        holder.weatherDate.setText("HEJ");
    }

    @Override
    public int getItemCount() {
        return weeklyWeatherReports.size();
    }
}
