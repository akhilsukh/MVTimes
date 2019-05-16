package com.akhilsukh01.mvtimes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class AgendaActivity extends AppCompatActivity {

    long timeOfDay;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private HorizontalCalendar horizontalCalendar;

    ArrayList<String> displayNote = new ArrayList<>();
    ArrayList<String> displayTitle = new ArrayList<>();
    ArrayList<ExampleItem> exampleList = new ArrayList<>();

//    private int counter;

    Long calDate;
    int calPosition = 0;
    TextView tTitle;
    TextView tNote;

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

        setContentView(R.layout.activity_agenda);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        tTitle = findViewById(R.id.tTitle);
        tNote = findViewById(R.id.tNote);


        /* start 2 months ago from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* end after 2 months from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 3);


        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = this.getTheme();
        theme.resolveAttribute(R.attr.colorAccent, typedValue, true);
        @ColorInt int colorAccent = typedValue.data;

        // Default Date set to Today.
        final Calendar defaultSelectedDate = Calendar.getInstance();

        int yearT = defaultSelectedDate.get(Calendar.YEAR);
        int monthT = defaultSelectedDate.get(Calendar.MONTH);
        int dateT = defaultSelectedDate.get(Calendar.DATE);
        defaultSelectedDate.clear();
        defaultSelectedDate.set(yearT, monthT, dateT);
        long todayMillis2 = defaultSelectedDate.getTimeInMillis();
//        timeOfDay = timeF.getTimeInMillis();


        getArrayListNote(String.valueOf(todayMillis2 + "note"));
        getArrayListTitle(String.valueOf(todayMillis2 + "title"));

        Log.i("ASD", "Activity Returned: " + todayMillis2);

        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                    .range(startDate, endDate)
                    .datesNumberOnScreen(5)
                    .mode(HorizontalCalendar.Mode.DAYS)
                .configure()
                    .formatTopText("MMM")
                    .formatMiddleText("dd")
                    .formatBottomText("EEE")
                    .showTopText(true)
                    .showBottomText(true)
                    .textColor(Color.LTGRAY, colorAccent)
                .end()
                .defaultSelectedDate(defaultSelectedDate)
//                .addEvents(new CalendarEventsPredicate() {
//                    Random rnd = new Random();
//                    @Override
//                    public List<CalendarEvent> events(Calendar date) {
//                        List<CalendarEvent> events = new ArrayList<>();
//                        int count = rnd.nextInt(6);
//
//                        for (int i = 0; i <= count; i++){
//                            events.add(new CalendarEvent(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)), "event"));
//                        }
//
//                        return events;
//                    }
//                })
                .build();

        Log.i("Default Date", DateFormat.format("EEE, MMM d, yyyy", defaultSelectedDate).toString());

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
//                String selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", date).toString();
//                Toast.makeText(AgendaActivity.this, selectedDateStr + " selected!", Toast.LENGTH_SHORT).show();
//                Log.i("onDateSelected", selectedDateStr + " - Position = " + position);
                calDate = date.getTimeInMillis();
                calPosition = position;

                if (calPosition == 0) {
//                    Calendar cal = Calendar.getInstance();
//                    int yearT = defaultSelectedDate.get(Calendar.YEAR);
//                    int monthT = defaultSelectedDate.get(Calendar.MONTH);
//                    int dateT = defaultSelectedDate.get(Calendar.DATE);
//                    defaultSelectedDate.clear();
//                    defaultSelectedDate.set(yearT, monthT, dateT);
//                    long todayMillis2 = defaultSelectedDate.getTimeInMillis();
                    timeOfDay = defaultSelectedDate.getTimeInMillis();
                }
                else {
                    timeOfDay = calDate;
                }

                getArrayListNote(String.valueOf(timeOfDay + "note"));
                getArrayListTitle(String.valueOf(timeOfDay + "title"));
                exampleList.clear();

                int counter = displayTitle.size();
                for (int n = 0; n < counter; n++) {
                    exampleList.add(new ExampleItem(displayTitle.get(n), displayNote.get(n)));
                }
                mRecyclerView = findViewById(R.id.recycler_view_agenda);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mAdapter = new ExampleAdapter(exampleList);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);
            }

        });

//        Log.i("ASD", "ArrayTitle Values: " + displayTitle);
        Log.i("ASD", "TestValues: " + displayTitle);
//        if (isNull){
            exampleList.clear();
            int counter = displayTitle.size();
            for (int n = 0; n < counter; n++) {
                exampleList.add(new ExampleItem(displayTitle.get(n), displayNote.get(n)));
            }
//        }
        mRecyclerView = findViewById(R.id.recycler_view_agenda);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = findViewById(R.id.fabReturnToday);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                horizontalCalendar.goToday(false);
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.fabAddNote);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AgendaAddNote.class);
                intent.putExtra("CalendarDate", calDate);
                intent.putExtra("CalendarPosition", calPosition);
                startActivityForResult(intent, 2);
            }
        });

//                exampleList.clear();
//                exampleList.
//                exampleList.add(new ExampleItem(AgendaAddNote.oneNote.get(0), AgendaAddNote.oneNote.get(1)));
//                mRecyclerView = findViewById(R.id.recycler_view_agenda);
//                mRecyclerView.setHasFixedSize(true);
//                mLayoutManager = new LinearLayoutManager(this);
//                mAdapter = new ExampleAdapter(exampleList);
//                mRecyclerView.setLayoutManager(mLayoutManager);
//                mRecyclerView.setAdapter(mAdapter);
    }

    public void getArrayListNote(String key){
        SharedPreferences prefsNote = PreferenceManager.getDefaultSharedPreferences(this);
        String stringOfNotes = prefsNote.getString(key, "");
        ArrayList<String> displayNotesTemp = new ArrayList<>();
        if(!stringOfNotes.equals(""))
        {
            stringOfNotes = stringOfNotes.substring(1, stringOfNotes.length()-1);
            while(stringOfNotes.length() > 0)
            {
                if(stringOfNotes.indexOf(",") != -1) {
                    displayNotesTemp.add(stringOfNotes.substring(0, stringOfNotes.indexOf(",")));
                    stringOfNotes = stringOfNotes.substring(stringOfNotes.indexOf(",") + 1);
                    stringOfNotes = stringOfNotes.trim();
                }
                else {
                    displayNotesTemp.add(stringOfNotes);
                    stringOfNotes = "";
                }
            }
        }
            Log.i("ASD", "Notes: " + displayNotesTemp);
            displayNote = displayNotesTemp;
        }

    public void getArrayListTitle(String key){
        SharedPreferences prefsTitle = PreferenceManager.getDefaultSharedPreferences(this);
        String stringOfTitles = prefsTitle.getString(key,"");
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
        Log.i("ASD", "Titles: " + displayTitleTemp);
        displayTitle = displayTitleTemp;
        Log.i("ASD", "TestValues: " + displayTitle);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        String tempNote = Objects.requireNonNull(data.getExtras()).getString("dateJustAdded") + "note";
        String tempTitle = Objects.requireNonNull(data.getExtras()).getString("dateJustAdded") + "title";
            getArrayListNote(tempNote);
            getArrayListTitle(tempTitle);
            Log.i("ASD", "Activity Returned: " + data.getExtras().getString("dateJustAdded"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}