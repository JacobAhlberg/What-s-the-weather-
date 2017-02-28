package com.ahlberg.jacob.whatstheweather;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class WeatherActivity extends AppCompatActivity {

    final String URL_BASE = "http://api.openweathermap.org/data/2.5/forecast";
    final String URL_COORDS = "/?lat=9.9687&lon=76.299";
    final String URL_UNITS = "&units=metric";
    final String URL_API_KEY = "&APPID=6293987fb4d85b38ac93029090356751";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        final String url = URL_BASE + URL_COORDS + URL_UNITS + URL_API_KEY;

        /*
        * Getting a json object back*/
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("FUN", "Response " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("FUN", "Error" + error.getLocalizedMessage());
            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);

    }
}
