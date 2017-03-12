package com.ahlberg.jacob.whatstheweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.currentDay)          TextView currentDayText;
    @BindView(R.id.my_toolbar)          Toolbar toolbar;
    @BindView(R.id.fahrenheitCheckBox)  CheckBox checkBox;
    @BindView(R.id.websiteBtn)          Button websiteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String currentDay = intent.getStringExtra("today");
        currentDayText.setText(currentDay);

        boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("checkBox", false);
        checkBox.setChecked(checked);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();


        websiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://darksky.net/");
                Intent websiteIntent = new Intent (Intent.ACTION_VIEW, uri);
                startActivity(websiteIntent);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor.putBoolean("degrees", false);
                    editor.putBoolean("checkBox", true);
                }
                else{
                    editor.putBoolean("degrees", true);
                    editor.putBoolean("checkBox", false);
                }

                editor.apply();
            }
        });


    }
}
