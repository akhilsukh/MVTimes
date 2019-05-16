package com.akhilsukh01.mvtimes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


public class SettingsActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SharedPreferences settingsPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
        Boolean themeCheck = settingsPref.getBoolean("theme", false);
        if (themeCheck){
            setTheme(R.style.PreferenceScreenDark);
        }
        else {
            setTheme(R.style.PreferenceScreenLight);
        }

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        finish();
//        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
//        startActivity(intent);
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        assert i != null;
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}