package com.ahlberg.jacob.whatstheweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchActivity extends AppCompatActivity {

    final static String GOOGLE_MAPS_KEY = "AIzaSyBFEwHLYYRV9z55LRFYOMPyRB2BoYhOIbI";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
