package com.example.android.sunshine.utilities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.example.android.sunshine.R;

/**
 * Created by Federico on 03/12/2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_screen);
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        int prefCount = prefScreen.getPreferenceCount();
        for (int i = 0; i < prefCount; i++){
            Preference p = prefScreen.getPreference(i);
            if (!(p instanceof CheckBoxPreference)){
                setPreferenceSummary(p, sharedPreferences.getString(p.getKey(), ""));
            }
        }
    }

    private void setPreferenceSummary(Preference p, Object value){
        String strValue = value.toString();
        String key = p.getKey();

        if (p instanceof ListPreference){
            ListPreference listPreference = (ListPreference)p;
            int indexOfValue = listPreference.findIndexOfValue(value.toString());
            if (indexOfValue >= 0) {
                String selectedOption = listPreference.getEntries()[indexOfValue].toString();
                p.setSummary(selectedOption);
            }
        } else {
            p.setSummary(strValue);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference p = findPreference(key);
        if (p != null && !(p instanceof CheckBoxPreference)) {
            setPreferenceSummary(p, sharedPreferences.getString(key, ""));
        }
    }

}
