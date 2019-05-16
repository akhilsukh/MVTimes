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
import java.util.Objects;

public class TabEditTeachersRooms extends Fragment implements View.OnClickListener {

    EditText teacher1;
    EditText teacher2;
    EditText teacher3;
    EditText teacher4;
    EditText teacher5;
    EditText teacher6;
    EditText teacher7;
    EditText room1;
    EditText room2;
    EditText room3;
    EditText room4;
    EditText room5;
    EditText room6;
    EditText room7;

    TextView titleFree;
    TextView titlePeriod;
    TextView textHelper;

    Button resetClasses;
    Button saveClasses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_edit_teachersrooms, container, false);

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

        View contentView = View.inflate(this.getActivity(), R.layout.tab_edit_teachersrooms, null);

        teacher1 = (EditText) contentView.findViewById(R.id.teacher1);
        teacher2 = (EditText) contentView.findViewById(R.id.teacher2);
        teacher3 = (EditText) contentView.findViewById(R.id.teacher3);
        teacher4 = (EditText) contentView.findViewById(R.id.teacher4);
        teacher5 = (EditText) contentView.findViewById(R.id.teacher5);
        teacher6 = (EditText) contentView.findViewById(R.id.teacher6);
        teacher7 = (EditText) contentView.findViewById(R.id.teacher7);
        room1 = (EditText) contentView.findViewById(R.id.room1);
        room2 = (EditText) contentView.findViewById(R.id.room2);
        room3 = (EditText) contentView.findViewById(R.id.room3);
        room4 = (EditText) contentView.findViewById(R.id.room4);
        room5 = (EditText) contentView.findViewById(R.id.room5);
        room6 = (EditText) contentView.findViewById(R.id.room6);
        room7 = (EditText) contentView.findViewById(R.id.room7);

        titleFree = (TextView) contentView.findViewById(R.id.titleFree);
        titlePeriod = (TextView) contentView.findViewById(R.id.titlePeriod);
        textHelper = (TextView) contentView.findViewById(R.id.textHelper);

        resetClasses = (Button) contentView.findViewById(R.id.buttonResetTeachers);
        saveClasses = (Button) contentView.findViewById(R.id.buttonSaveTeachers);

        Typeface sf_pro_medium = Typeface.createFromAsset(this.getActivity().getAssets(), "fonts/sf_pro_display_medium.ttf");
        teacher1.setTypeface(sf_pro_medium);
        teacher2.setTypeface(sf_pro_medium);
        teacher3.setTypeface(sf_pro_medium);
        teacher4.setTypeface(sf_pro_medium);
        teacher5.setTypeface(sf_pro_medium);
        teacher6.setTypeface(sf_pro_medium);
        teacher7.setTypeface(sf_pro_medium);
        room1.setTypeface(sf_pro_medium);
        room2.setTypeface(sf_pro_medium);
        room3.setTypeface(sf_pro_medium);
        room4.setTypeface(sf_pro_medium);
        room5.setTypeface(sf_pro_medium);
        room6.setTypeface(sf_pro_medium);
        room7.setTypeface(sf_pro_medium);
        titleFree.setTypeface(sf_pro_medium);
        titlePeriod.setTypeface(sf_pro_medium);
        textHelper.setTypeface(sf_pro_medium);
        Typeface sf_pro_semibold = Typeface.createFromAsset(this.getActivity().getAssets(), "fonts/sf_pro_display_semibold.ttf");
        resetClasses.setTypeface(sf_pro_semibold);
        saveClasses.setTypeface(sf_pro_semibold);


        SharedPreferences sharedPref = Objects.requireNonNull(getContext()).getSharedPreferences("userTeachers", Context.MODE_PRIVATE);
        final String saveT1 = sharedPref.getString("teacher1", "");
        final String saveT2 = sharedPref.getString("teacher2", "");
        final String saveT3 = sharedPref.getString("teacher3", "");
        final String saveT4 = sharedPref.getString("teacher4", "");
        final String saveT5 = sharedPref.getString("teacher5", "");
        final String saveT6 = sharedPref.getString("teacher6", "");
        final String saveT7 = sharedPref.getString("teacher7", "");

        teacher1.setText(saveT1);
        teacher2.setText(saveT2);
        teacher3.setText(saveT3);
        teacher4.setText(saveT4);
        teacher5.setText(saveT5);
        teacher6.setText(saveT6);
        teacher7.setText(saveT7);

        final String saveR1 = sharedPref.getString("room1", "");
        final String saveR2 = sharedPref.getString("room2", "");
        final String saveR3 = sharedPref.getString("room3", "");
        final String saveR4 = sharedPref.getString("room4", "");
        final String saveR5 = sharedPref.getString("room5", "");
        final String saveR6 = sharedPref.getString("room6", "");
        final String saveR7 = sharedPref.getString("room7", "");

        room1.setText(saveR1);
        room2.setText(saveR2);
        room3.setText(saveR3);
        room4.setText(saveR4);
        room5.setText(saveR5);
        room6.setText(saveR6);
        room7.setText(saveR7);

        saveClasses.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                SharedPreferences sharedPref = getContext().getSharedPreferences("userTeachers", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("teacher1", teacher1.getText().toString());
                editor.putString("teacher2", teacher2.getText().toString());
                editor.putString("teacher3", teacher3.getText().toString());
                editor.putString("teacher4", teacher4.getText().toString());
                editor.putString("teacher5", teacher5.getText().toString());
                editor.putString("teacher6", teacher6.getText().toString());
                editor.putString("teacher7", teacher7.getText().toString());

                editor.putString("room1", room1.getText().toString());
                editor.putString("room2", room2.getText().toString());
                editor.putString("room3", room3.getText().toString());
                editor.putString("room4", room4.getText().toString());
                editor.putString("room5", room5.getText().toString());
                editor.putString("room6", room6.getText().toString());
                editor.putString("room7", room7.getText().toString());

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
                SharedPreferences sharedPref = getContext().getSharedPreferences("userTeachers", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("teacher1", "");
                editor.putString("teacher2", "");
                editor.putString("teacher3", "");
                editor.putString("teacher4", "");
                editor.putString("teacher5", "");
                editor.putString("teacher6", "");
                editor.putString("teacher7", "");

                editor.putString("room1", "");
                editor.putString("room2", "");
                editor.putString("room3", "");
                editor.putString("room4", "");
                editor.putString("room5", "");
                editor.putString("room6", "");
                editor.putString("room7", "");
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
