package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    private TextView mDisplayWeatherTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO (2) Display the weather forecast that was passed from MainActivity
        mDisplayWeatherTV = (TextView) findViewById(R.id.tv_display_weather);
        Intent intentFromCaller = getIntent();
        if (intentFromCaller != null) {
            if (getIntent().hasExtra(Intent.EXTRA_TEXT)) {
                String weatherData = getIntent().getStringExtra(Intent.EXTRA_TEXT);
                mDisplayWeatherTV.setText(weatherData);
            }
        }
    }
}