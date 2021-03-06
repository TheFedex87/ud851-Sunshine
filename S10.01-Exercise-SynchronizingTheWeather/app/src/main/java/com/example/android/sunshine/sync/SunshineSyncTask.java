package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

//  TODO (1) Create a class called SunshineSyncTask
public class SunshineSyncTask {
//  TODO (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
    synchronized public static void syncWeather(Context context) {
        try {
//          TODO (3) Within syncWeather, fetch new weather data
            URL weatherUrl = NetworkUtils.getUrl(context);
            String jsonWeather = NetworkUtils.getResponseFromHttpUrl(weatherUrl);
            ContentValues[] weatherValues = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, jsonWeather);

            if (weatherValues != null && weatherValues.length > 0) {
//              TODO (4) If we have valid results, delete the old data and insert the new
                ContentResolver contentResolver = context.getContentResolver();

                contentResolver.delete(
                        WeatherContract.WeatherEntry.CONTENT_URI,
                        null,
                        null
                );

                contentResolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI, weatherValues);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}