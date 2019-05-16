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
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TabEditClasses extends Fragment implements View.OnClickListener {

    EditText p1;
    EditText p2;
    EditText p3;
    EditText p4;
    EditText p5;
    EditText p6;
    EditText p7;
    Switch switch1;
    Switch switch2;
    Switch switch3;
    Switch switch4;
    Switch switch5;
    Switch switch6;
    Switch switch7;

    TextView titleFree;
    TextView titlePeriod;
    TextView textHelper;

    Button resetClasses;
    Button saveClasses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_edit_classes, container, false);

//        SharedPreferences settingsPref = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
//        Boolean themeCheck = settingsPref.getBoolean("theme", false);
//        if (themeCheck){
//            this.getActivity().setTheme(R.style.MyMaterialDarkTheme);
//        }
//        else {
//            this.getActivity().setTheme(R.style.MyMaterialLightTheme);
//        }

//        super.onCreate(savedInstanceState);
//        this.getActivity().setContentView(R.layout.tab_edit_classes);

        SharedPreferences sharedPref = getContext().getSharedPreferences("userClasses", Context.MODE_PRIVATE);
        View contentView = View.inflate(this.getActivity(), R.layout.tab_edit_classes, null);

        p1 = (EditText) contentView.findViewById(R.id.teacher1);
        p2 = (EditText) contentView.findViewById(R.id.teacher2);
        p3 = (EditText) contentView.findViewById(R.id.teacher3);
        p4 = (EditText) contentView.findViewById(R.id.teacher4);
        p5 = (EditText) contentView.findViewById(R.id.teacher5);
        p6 = (EditText) contentView.findViewById(R.id.teacher6);
        p7 = (EditText) contentView.findViewById(R.id.teacher7);
        switch1 = (Switch) contentView.findViewById(R.id.free1);
        switch2 = (Switch) contentView.findViewById(R.id.free2);
        switch3 = (Switch) contentView.findViewById(R.id.free3);
        switch4 = (Switch) contentView.findViewById(R.id.free4);
        switch5 = (Switch) contentView.findViewById(R.id.free5);
        switch6 = (Switch) contentView.findViewById(R.id.free6);
        switch7 = (Switch) contentView.findViewById(R.id.free7);

        titleFree = (TextView) contentView.findViewById(R.id.titleFree);
        titlePeriod = (TextView) contentView.findViewById(R.id.titlePeriod);
        textHelper = (TextView) contentView.findViewById(R.id.textHelper);

        resetClasses = (Button) contentView.findViewById(R.id.buttonResetTeachers);
        saveClasses = (Button) contentView.findViewById(R.id.buttonSaveTeachers);

        Typeface sf_pro_medium = Typeface.createFromAsset(this.getActivity().getAssets(), "fonts/sf_pro_display_medium.ttf");
        p1.setTypeface(sf_pro_medium);
        p2.setTypeface(sf_pro_medium);
        p3.setTypeface(sf_pro_medium);
        p4.setTypeface(sf_pro_medium);
        p5.setTypeface(sf_pro_medium);
        p6.setTypeface(sf_pro_medium);
        p7.setTypeface(sf_pro_medium);
        titleFree.setTypeface(sf_pro_medium);
        titlePeriod.setTypeface(sf_pro_medium);
        textHelper.setTypeface(sf_pro_medium);
        Typeface sf_pro_semibold = Typeface.createFromAsset(this.getActivity().getAssets(), "fonts/sf_pro_display_semibold.ttf");
        resetClasses.setTypeface(sf_pro_semibold);
        saveClasses.setTypeface(sf_pro_semibold);


        final String saveP1 = sharedPref.getString("p1", "");
        final String saveP2 = sharedPref.getString("p2", "");
        final String saveP3 = sharedPref.getString("p3", "");
        final String saveP4 = sharedPref.getString("p4", "");
        final String saveP5 = sharedPref.getString("p5", "");
        final String saveP6 = sharedPref.getString("p6", "");
        final String saveP7 = sharedPref.getString("p7", "");

        p1.setText(saveP1);
        p2.setText(saveP2);
        p3.setText(saveP3);
        p4.setText(saveP4);
        p5.setText(saveP5);
        p6.setText(saveP6);
        p7.setText(saveP7);

        if (saveP1.equals("")) {
            switch1.setChecked(true);
        }
        if (saveP2.equals("")) {
            switch2.setChecked(true);
        }
        if (saveP3.equals("")) {
            switch3.setChecked(true);
        }
        if (saveP4.equals("")) {
            switch4.setChecked(true);
        }
        if (saveP5.equals("")) {
            switch5.setChecked(true);
        }
        if (saveP6.equals("")) {
            switch6.setChecked(true);
        }
        if (saveP7.equals("")) {
            switch7.setChecked(true);
        }

//        Toast.makeText(getContext(), "Recalled", Toast.LENGTH_SHORT).show();

        saveClasses.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                SharedPreferences sharedPref = getContext().getSharedPreferences("userClasses", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();
//                if (switch1.isChecked()) {
//                    editor.putString("p1", "");
//                }
//                if (switch2.isChecked()) {
//                    editor.putString("p2", "");
//                }
//                if (switch3.isChecked()) {
//                    editor.putString("p3", "");
//                }
//                if (switch4.isChecked()) {
//                    editor.putString("p4", "");
//                }
//                if (switch5.isChecked()) {
//                    editor.putString("p5", "");
//                }
//                if (switch6.isChecked()) {
//                    editor.putString("p6", "");
//                }
//                if (switch7.isChecked()) {
//                    editor.putString("p7", "");
//                }
//                editor.apply();

                editor.putString("p1", p1.getText().toString());
                editor.putString("p2", p2.getText().toString());
                editor.putString("p3", p3.getText().toString());
                editor.putString("p4", p4.getText().toString());
                editor.putString("p5", p5.getText().toString());
                editor.putString("p6", p6.getText().toString());
                editor.putString("p7", p7.getText().toString());
                editor.apply();

//                Intent intent = new Intent(getContext(), MainActivity.class);
//                startActivity(intent);

                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

            }
        });

        resetClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                SharedPreferences sharedPref = getContext().getSharedPreferences("userClasses", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("p1", "");
                editor.putString("p2", "");
                editor.putString("p3", "");
                editor.putString("p4", "");
                editor.putString("p5", "");
                editor.putString("p6", "");
                editor.putString("p7", "");
                editor.apply();


                Intent resetR = new Intent(getContext(), MainActivity.class);
                startActivity(resetR);

                Toast.makeText(getContext(), "Reset", Toast.LENGTH_LONG).show();
            }
        });
//        saveClasses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG).show();
//            }
//        });

        return contentView;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.buttonSaveTeachers) {
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG).show();
        } else if (id == R.id.buttonResetTeachers) {
                Toast.makeText(getContext(), "Reset", Toast.LENGTH_LONG).show();
        }

//        switch (view.getId()) {
//            case R.id.buttonSaveTeachers: {
//                Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG).show();
//            }
//            case R.id.buttonResetTeachers: {
//                Toast.makeText(getContext(), "Reset", Toast.LENGTH_LONG).show();
//            }
//
//            break;
//        }

    }
}
