package com.ahlberg.jacob.whatstheweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.currentDay)          TextView currentDayText;
    @BindView(R.id.my_toolbar)          Toolbar toolbar;
    @BindView(R.id.fahrenheitCheckBox)  CheckBox checkBox;


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
