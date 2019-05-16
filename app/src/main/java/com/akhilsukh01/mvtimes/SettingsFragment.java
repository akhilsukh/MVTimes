package com.akhilsukh01.mvtimes;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
{
    Context mContext;
    private final static String TAG = "ASDASD";
    SharedPreferences settingsPref;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_main);
        settingsPref= getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        Log.d(TAG, "onCreate");
        mContext = getActivity().getApplicationContext();
    }


    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
        if (key.equals("dark_mode_switch"))
        {
//            Log.d(TAG, "dark mode switched");
            Boolean t = getPreferenceManager().getSharedPreferences().getBoolean("dark_mode_switch", false);
            if (t) {
                SharedPreferences.Editor editor = settingsPref.edit();
                editor.putBoolean("theme", true);
                editor.apply();
//                SettingsActivity.setTheme(R.style.MyMaterialDarkTheme);
                Toast.makeText(mContext, "Press back to apply new theme",Toast.LENGTH_SHORT ).show();
                Log.d(TAG, t.toString());
            }
            else {
                SharedPreferences.Editor editor = settingsPref.edit();
                editor.putBoolean("theme", false);
                editor.apply();
                Toast.makeText(mContext, "Press back to apply new theme",Toast.LENGTH_SHORT ).show();
                Log.d(TAG, t.toString());
            }
        }
        else if (key.equals("notification_switch"))
        {
            Boolean t = getPreferenceManager().getSharedPreferences().getBoolean("notification_switch", false);
            if (t) {
                SharedPreferences.Editor editor = settingsPref.edit();
                editor.putBoolean("notif", true);
                editor.apply();
                Log.d(TAG, t.toString());
            }
            else {
                SharedPreferences.Editor editor = settingsPref.edit();
                editor.putBoolean("notif", false);
                editor.apply();
                Log.d(TAG, t.toString());
            }
        }
    }
}