package com.akhilsukh01.mvtimes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class AgendaAddNote extends AppCompatActivity {

    long timeOfDay;
    FloatingActionButton saveNote;
    MaterialEditText editTitle;
    MaterialEditText editNote;
    String stringEditTitle;
    String stringEditNote;
    static ArrayList<String> oneTitle = new ArrayList<>();
    static ArrayList<String> oneNote = new ArrayList<>();
    ArrayList<String> displayNote = new ArrayList<>();
    ArrayList<String> displayTitle = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settingsPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
        Boolean themeCheck = settingsPref.getBoolean("theme", false);
        if (themeCheck){
            setTheme(R.style.MyMaterialDarkTheme);
        }
        else {
            setTheme(R.style.MyMaterialLightTheme);
        }

        setContentView(R.layout.activity_agenda_add_note);
        saveNote = findViewById(R.id.saveNote);
        editNote = findViewById(R.id.editNote);
        editTitle = findViewById(R.id.editTitle);

        Long calDate= getIntent().getLongExtra("CalendarDate", Long.valueOf(0));
        int calPosition= getIntent().getIntExtra("CalendarPosition", 0);
        Toast.makeText(AgendaAddNote.this, calDate + " " + calPosition, Toast.LENGTH_SHORT).show();
//        Log.i("onDateSelected", calDate + " " + calPosition);

        final Calendar time = Calendar.getInstance();
        //this means that the day selected was TODAY
        if (calPosition == 0){
            int year  = time.get(Calendar.YEAR);
            int month = time.get(Calendar.MONTH);
            int date  = time.get(Calendar.DATE);
            time.clear();
            time.set(year, month, date);
            long todayMillis2 = time.getTimeInMillis();
            timeOfDay = todayMillis2;
            Log.i("ASD", "Today: " +String.valueOf(todayMillis2));
        }
        else {
            timeOfDay = calDate;
            Log.i("ASD", "Other Day: " +String.valueOf(timeOfDay));
        }

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringEditTitle = Objects.requireNonNull(editTitle.getText()).toString();
                stringEditNote = Objects.requireNonNull(editNote.getText()).toString();

//                oneNote.add(String.valueOf(timeOfDay));
//                oneTitle.add(String.valueOf(stringEditTitle + "\t"));
//                oneNote.add(String.valueOf(stringEditNote + "\t"));
                saveArrayListNote(stringEditNote, String.valueOf(timeOfDay + "note"));
                saveArrayListTitle(stringEditTitle, String.valueOf(timeOfDay + "title"));
//                Log.i("ASD", "oneNote: " + oneNote.toString());
//                Log.i("ASD", "oneTitle:" + oneTitle.toString());
            }
        });



    }

    public void saveArrayListNote(String newNoteString, String key){
        SharedPreferences prefsNote = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefsNote.edit();

        String stringOfNotes = prefsNote.getString(key, "");
        ArrayList<String> displayTitleTemp = new ArrayList<>();
        if(!stringOfNotes.equals(""))
        {
            stringOfNotes = stringOfNotes.substring(1, stringOfNotes.length()-1);
            while(stringOfNotes.length() > 0)
            {
                if(stringOfNotes.indexOf(",") != -1) {
                    displayTitleTemp.add(stringOfNotes.substring(0, stringOfNotes.indexOf(",")));
                    stringOfNotes = stringOfNotes.substring(stringOfNotes.indexOf(",") + 1);
                    stringOfNotes = stringOfNotes.trim();
                }
                else {
                    displayTitleTemp.add(stringOfNotes);
                    stringOfNotes = "";
                }
            }
        }
        displayTitleTemp.add(newNoteString);
        editor.putString(key, displayTitleTemp.toString());
        editor.apply();
        Log.i("ASD", "savedString " + displayTitleTemp);
    }

    public void saveArrayListTitle(String newTitleString, String key){
        SharedPreferences prefsTitle = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefsTitle.edit();

        String stringOfTitles = prefsTitle.getString(key, "");
        ArrayList<String> displayTitleTemp = new ArrayList<>();
        if(!stringOfTitles.equals(""))
        {
            stringOfTitles = stringOfTitles.substring(1, stringOfTitles.length()-1);
            while(stringOfTitles.length() > 0)
            {
                if(stringOfTitles.indexOf(",") != -1) {
                    displayTitleTemp.add(stringOfTitles.substring(0, stringOfTitles.indexOf(",")));
                    stringOfTitles = stringOfTitles.substring(stringOfTitles.indexOf(",") + 1);
                    stringOfTitles = stringOfTitles.trim();
                }
                else {
                    displayTitleTemp.add(stringOfTitles);
                    stringOfTitles = "";
                }
            }
        }
        displayTitleTemp.add(newTitleString);
        editor.putString(key, displayTitleTemp.toString());
        editor.apply();
        Log.i("ASD", "savedString " + displayTitleTemp);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("dateJustAdded",String.valueOf(timeOfDay));
        setResult(2,intent);
        finish();//finishing activity
        super.onBackPressed();
    }
}
