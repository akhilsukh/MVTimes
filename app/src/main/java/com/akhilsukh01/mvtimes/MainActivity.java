package com.akhilsukh01.mvtimes;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    int[] BellMonArray;
    int[] BellTueArray;
    int[] BellWedArray;
    int[] BellThurArray;

    private Context mContext;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    public static final int NOTIFICATION_REQUEST_CODE = 101;

    private final static String TAG = "ASDASD";
    private ShimmerFrameLayout shimmer_view_container;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        BellMonArray = getResources().getIntArray(R.array.MonArray);
        BellTueArray = getResources().getIntArray(R.array.TueArray);
        BellWedArray = getResources().getIntArray(R.array.WedArray);
        BellThurArray = getResources().getIntArray(R.array.ThurArray);

        SharedPreferences settingsPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
        Boolean themeCheck = settingsPref.getBoolean("theme", false);
        if (themeCheck) {
            setTheme(R.style.MyMaterialDarkTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BOTTOM BUTTON
        ImageButton mButton3 = (ImageButton) findViewById(R.id.navButton);
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment BottomSheetFragment = new BottomSheetFragment();
                BottomSheetFragment.show(getSupportFragmentManager(), BottomSheetFragment.getTag());
            }
        });

        //Retrieving user's saved reminders
//        rem1 = (EditText) findViewById(R.id.reminder1);
//        rem2 = (EditText) findViewById(R.id.reminder2);
//        rem3 = (EditText) findViewById(R.id.reminder3);
//        rem4 = (EditText) findViewById(R.id.reminder4);
//        rem5 = (EditText) findViewById(R.id.reminder5);
//        SharedPreferences sharedPref = getSharedPreferences("userRem", Context.MODE_PRIVATE);
//        String rem1R = sharedPref.getString("save1", "");
//        String rem2R = sharedPref.getString("save2", "");
//        String rem3R = sharedPref.getString("save3", "");
//        String rem4R = sharedPref.getString("save4", "");
//        String rem5R = sharedPref.getString("save5", "");
//        rem1.setText(rem1R);
//        rem2.setText(rem2R);
//        rem3.setText(rem3R);
//        rem4.setText(rem4R);
//        rem5.setText(rem5R);

        final TextView timer = (TextView) findViewById(R.id.timer);
        final TextView week = (TextView) findViewById(R.id.week);
        shimmer_view_container = findViewById(R.id.shimmer_view_container);

//        Button buttonWeekdayTimeTable = (Button) findViewById(R.id.buttonBellSchedule);
//        Button buttonEditClassesActivity = (Button) findViewById(R.id.buttonEditClasses);
//        Button buttonSaveUserReminders = (Button) findViewById(R.id.saveUserRemindersButton);
//        Button buttonSettings = (Button) findViewById(R.id.buttonSettings);

        //Set fonts
        Typeface sf_pro_medium = Typeface.createFromAsset(getAssets(), "fonts/sf_pro_display_medium.ttf");
        timer.setTypeface(sf_pro_medium);
        week.setTypeface(sf_pro_medium);
//        rem1.setTypeface(sf_pro_medium);
//        rem2.setTypeface(sf_pro_medium);
//        rem3.setTypeface(sf_pro_medium);
//        rem4.setTypeface(sf_pro_medium);
//        rem5.setTypeface(sf_pro_medium);
        Typeface sf_pro_semibold = Typeface.createFromAsset(getAssets(), "fonts/sf_pro_display_semibold.ttf");
//        buttonWeekdayTimeTable.setTypeface(sf_pro_semibold);
//        buttonEditClassesActivity.setTypeface(sf_pro_semibold);
//        buttonSaveUserReminders.setTypeface(sf_pro_semibold);

        TextView gesturePad = findViewById(R.id.gesturePad);
        gesturePad.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeUp() {
//                Toast.makeText(MainActivity.this, "Up", Toast.LENGTH_SHORT).show();
                BottomSheetDialogFragment BottomSheetFragment = new BottomSheetFragment();
                BottomSheetFragment.show(getSupportFragmentManager(), BottomSheetFragment.getTag());
            }
        });

        //Buttons for activities
//        buttonWeekdayTimeTable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, TimeTableContainer.class);
//                startActivity(intent);
//            }
//        });
//        buttonEditClassesActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View t) {
//                Intent intent2 = new Intent(MainActivity.this, TabEditClasses.class);
//                startActivity(intent2);
//            }
//        });
//        buttonSettings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View t) {
//                Intent intent3 = new Intent(MainActivity.this, SettingsActivity.class);
//                startActivity(intent3);
//            }
//        });


        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                //setting objects to variable names


                                long date = System.currentTimeMillis();

                                // setting time format
                                //minutes
                                SimpleDateFormat minutesSDF = new SimpleDateFormat("mm");
                                //seconds
                                SimpleDateFormat secondsSDF = new SimpleDateFormat("ss");
                                //Weekday name formatted
                                //Weekday name full
                                SimpleDateFormat weekdayFullSDF = new SimpleDateFormat("EEEE");
                                //hours
                                SimpleDateFormat hoursSDF = new SimpleDateFormat("HH");


                                // creating strings using date
                                String minutesString = minutesSDF.format(date);
                                String weekdayFullString = weekdayFullSDF.format(date);
                                String hoursString = hoursSDF.format(date);
                                String secondsString = secondsSDF.format(date);


                                //converts string for minutes to int
                                int minutesInt = Integer.parseInt(minutesString);
                                //converts string for hours to int
                                int hoursInt = Integer.parseInt(hoursString);
                                //converts string for seconds to int
                                int secondsInt = Integer.parseInt(secondsString);
                                //current time in minutes
                                int currentTimeToSubtract = (((hoursInt) * 60) + (minutesInt));
                                int currentSecondsToSubtract = (59 - secondsInt);


                                //Set weekday title
                                week.setText(weekdayFullString);


                                //Determines weekday for following if/else statements
                                int weekdayCodeNumber = 0;
                                if (weekdayFullString.equalsIgnoreCase("Monday")) {
                                    weekdayCodeNumber = 1;
                                } else if (weekdayFullString.equalsIgnoreCase("Tuesday") || weekdayFullString.equalsIgnoreCase("Friday")) {
                                    weekdayCodeNumber = 2;
                                } else if (weekdayFullString.equalsIgnoreCase("Wednesday")) {
                                    weekdayCodeNumber = 3;
                                } else if (weekdayFullString.equalsIgnoreCase("Thursday")) {
                                    weekdayCodeNumber = 4;
                                }


                                //retrieves user's periods(+free periods)  and displays them under countdown
                                SharedPreferences sharedPrefClasses = getSharedPreferences("userClasses", Context.MODE_PRIVATE);
//                                int period = 0;
                                final String saveP1 = sharedPrefClasses.getString("p1", "");
                                final String saveP2 = sharedPrefClasses.getString("p2", "");
                                final String saveP3 = sharedPrefClasses.getString("p3", "");
                                final String saveP4 = sharedPrefClasses.getString("p4", "");
                                final String saveP5 = sharedPrefClasses.getString("p5", "");
                                final String saveP6 = sharedPrefClasses.getString("p6", "");
                                final String saveP7 = sharedPrefClasses.getString("p7", "");

                                String savePeriod1 = saveP1;
                                String savePeriod2 = saveP2;
                                String savePeriod3 = saveP3;
                                String savePeriod4 = saveP4;
                                String savePeriod5 = saveP5;
                                String savePeriod6 = saveP6;
                                String savePeriod7 = saveP7;

                                if (saveP1.equals("") || saveP1.equals(null)) {
                                    savePeriod1 = "free";
                                }
                                if (saveP2.equals("") || saveP2.equals(null)) {
                                    savePeriod2 = "free";
                                }
                                if (saveP3.equals("") || saveP3.equals(null)) {
                                    savePeriod3 = "free";
                                }
                                if (saveP4.equals("") || saveP4.equals(null)) {
                                    savePeriod4 = "free";
                                }
                                if (saveP5.equals("") || saveP5.equals(null)) {
                                    savePeriod5 = "free";
                                }
                                if (saveP6.equals("") || saveP6.equals(null)) {
                                    savePeriod6 = "free";
                                }
                                if (saveP7.equals("") || saveP7.equals(null)) {
                                    savePeriod7 = "free";
                                }

//                                //retrieves user's teacher and rooms and displays them under countdown
//                                SharedPreferences sharedPrefTeachers = getSharedPreferences("userClasses", Context.MODE_PRIVATE);
////                                int period = 0;
//                                final String saveT1 = sharedPrefTeachers.getString("room1", "");
//                                final String saveT2 = sharedPrefTeachers.getString("room2", "");
//                                final String saveT3 = sharedPrefTeachers.getString("room3", "");
//                                final String saveT4 = sharedPrefTeachers.getString("room4", "");
//                                final String saveT5 = sharedPrefTeachers.getString("room5", "");
//                                final String saveT6 = sharedPrefTeachers.getString("room6", "");
//                                final String saveT7 = sharedPrefTeachers.getString("room7", "");
//
//                                String saveTeacher1 = saveT1;
//                                String saveTeacher2 = saveT2;
//                                String saveTeacher3 = saveT3;
//                                String saveTeacher4 = saveT4;
//                                String saveTeacher5 = saveT5;
//                                String saveTeacher6 = saveT6;
//                                String saveTeacher7 = saveT7;
//
//                                if (saveT1.equals("") || saveT1.equals(null)) {
//                                    saveTeacher1 = "your next class";
//                                }
//                                if (saveT2.equals("") || saveT2.equals(null)) {
//                                    saveTeacher2 = "your next class";
//                                }
//                                if (saveT3.equals("") || saveT3.equals(null)) {
//                                    saveTeacher3 = "your next class";
//                                }
//                                if (saveT4.equals("") || saveT4.equals(null)) {
//                                    saveTeacher4 = "your next class";
//                                }
//                                if (saveT5.equals("") || saveT5.equals(null)) {
//                                    saveTeacher5 = "your next class";
//                                }
//                                if (saveT6.equals("") || saveT6.equals(null)) {
//                                    saveTeacher6 = "your next class";
//                                }
//                                if (saveT7.equals("") || saveT7.equals(null)) {
//                                    saveTeacher7 = "your next class";
//                                }


                                if (weekdayCodeNumber == 1 || weekdayCodeNumber == 2 || weekdayCodeNumber == 3 || weekdayCodeNumber == 4) {

                                    //checks day and time for monday
                                    if (weekdayCodeNumber == 1) {
                                        if (currentTimeToSubtract > 0 && currentTimeToSubtract < 925) {

                                            if (currentTimeToSubtract > 0 && currentTimeToSubtract <= 520) {
                                                timer.setText("1st period (" + savePeriod1 + ") starts in " + String.valueOf(BellMonArray[0] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 520 && currentTimeToSubtract < 565) {
                                                timer.setText("2nd period (" + savePeriod2 + ") starts in " + String.valueOf(BellMonArray[1] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 570 && currentTimeToSubtract < 615) {
                                                timer.setText("3rd period (" + savePeriod3 + ") starts in " + String.valueOf(BellMonArray[2] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 620 && currentTimeToSubtract < 670) {
                                                timer.setText("Brunch starts in " + String.valueOf(BellMonArray[3] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract >= 670 && currentTimeToSubtract < 685) {
                                                timer.setText("4th period (" + savePeriod4 + ") starts in " + String.valueOf(BellMonArray[4] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 690 && currentTimeToSubtract < 735) {
                                                timer.setText("5th period (" + savePeriod5 + ") starts in " + String.valueOf(BellMonArray[5] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 740 && currentTimeToSubtract < 785) {
                                                timer.setText("Lunch starts in " + String.valueOf(BellMonArray[6] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract >= 785 && currentTimeToSubtract < 825) {
                                                timer.setText("6th period (" + savePeriod6 + ") starts in " + String.valueOf(BellMonArray[7] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 830 && currentTimeToSubtract < 875) {
                                                timer.setText("7th period (" + savePeriod7 + ") starts in " + String.valueOf(BellMonArray[8] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 880 && currentTimeToSubtract < 925) {
                                                timer.setText("School ends in " + String.valueOf(BellMonArray[9] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract < 925) {
                                                timer.setText("Get to your next class");
                                            }
                                        } else if (currentTimeToSubtract >= 925) {
                                            timer.setText("School's out!");
                                        }
                                    }

                                    //checks day and time for tuesday/friday
                                    if (weekdayCodeNumber == 2) {
                                        if (currentTimeToSubtract > 0 && currentTimeToSubtract < 925) {

                                            if (currentTimeToSubtract > 0 && currentTimeToSubtract <= 480) {
                                                timer.setText("1st period (" + savePeriod1 + ") starts in " + String.valueOf(BellTueArray[0] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 480 && currentTimeToSubtract < 525) {
                                                timer.setText("2nd period (" + savePeriod2 + ") starts in " + String.valueOf(BellTueArray[1] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 530 && currentTimeToSubtract < 575) {
                                                timer.setText("Tutorial starts in " + String.valueOf(BellTueArray[2] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 580 && currentTimeToSubtract < 615) {
                                                timer.setText("3rd period (" + savePeriod3 + ") starts in " + String.valueOf(BellTueArray[3] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 620 && currentTimeToSubtract < 670) {
                                                timer.setText("Brunch starts in " + String.valueOf(BellTueArray[4] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract >= 670 && currentTimeToSubtract < 685) {
                                                timer.setText("4th period (" + savePeriod4 + ") starts in " + String.valueOf(BellTueArray[5] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 690 && currentTimeToSubtract < 735) {
                                                timer.setText("5th period (" + savePeriod5 + ") starts in " + String.valueOf(BellTueArray[6] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 740 && currentTimeToSubtract < 785) {
                                                timer.setText("Lunch starts in " + String.valueOf(BellTueArray[7] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract >= 785 && currentTimeToSubtract < 825) {
                                                timer.setText("6th period (" + savePeriod6 + ") starts in " + String.valueOf(BellTueArray[8] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 830 && currentTimeToSubtract < 875) {
                                                timer.setText("7th period (" + savePeriod7 + ") starts in " + String.valueOf(BellTueArray[9] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 880 && currentTimeToSubtract < 925) {
                                                timer.setText("School ends in " + String.valueOf(BellTueArray[10] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract < 925) {
                                                timer.setText("Get to your next class");
                                            }
                                        } else if (currentTimeToSubtract >= 925) {
                                            timer.setText("School's out!");
                                        }
                                    }


                                    //checks day and time for wednesday
                                    if (weekdayCodeNumber == 3) {
                                        if (currentTimeToSubtract > 0 && currentTimeToSubtract < 920) {

                                            if (currentTimeToSubtract > 0 && currentTimeToSubtract <= 535) {
                                                timer.setText("4th period (" + savePeriod4 + ") starts in " + String.valueOf(BellWedArray[0] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 535 && currentTimeToSubtract < 630) {
                                                timer.setText("Tutorial starts in " + String.valueOf(BellWedArray[1] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 635 && currentTimeToSubtract < 675) {
                                                timer.setText("Brunch starts in " + String.valueOf(BellWedArray[2] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract >= 675 && currentTimeToSubtract < 690) {
                                                timer.setText("5th period (" + savePeriod5 + ") starts in " + String.valueOf(BellWedArray[3] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 695 && currentTimeToSubtract < 785) {
                                                timer.setText("Lunch starts in " + String.valueOf(BellWedArray[4] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract >= 785 && currentTimeToSubtract < 825) {
                                                timer.setText("6th period (" + savePeriod6 + ") starts in " + String.valueOf(BellWedArray[5] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 830 && currentTimeToSubtract < 920) {
                                                timer.setText("School ends in " + String.valueOf(BellWedArray[6] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract < 920) {
                                                timer.setText("Get to your next class");
                                            }
                                        } else if (currentTimeToSubtract > 920) {
                                            timer.setText("School's out!");
                                        }
                                    }

                                    //checks day and time for thursday
                                    if (weekdayCodeNumber == 4) {
                                        if (currentTimeToSubtract > 0 && currentTimeToSubtract < 920) {

                                            if (currentTimeToSubtract > 0 && currentTimeToSubtract <= 480) {
                                                timer.setText("1st period (" + savePeriod1 + ") starts in " + String.valueOf(BellThurArray[0] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 480 && currentTimeToSubtract < 575) {
                                                timer.setText("2nd period (" + savePeriod2 + ") starts in " + String.valueOf(BellThurArray[1] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 580 && currentTimeToSubtract < 670) {
                                                timer.setText("Brunch starts in " + String.valueOf(BellThurArray[2] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract >= 670 && currentTimeToSubtract < 685) {
                                                timer.setText("3rd period (" + savePeriod3 + ") starts in " + String.valueOf(BellThurArray[3] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 690 && currentTimeToSubtract < 785) {
                                                timer.setText("Lunch starts in " + String.valueOf(BellThurArray[4] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract >= 785 && currentTimeToSubtract < 825) {
                                                timer.setText("7th period (" + savePeriod7 + ") starts in " + String.valueOf(BellThurArray[5] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract > 830 && currentTimeToSubtract < 920) {
                                                timer.setText("School ends in " + String.valueOf(BellThurArray[6] - currentTimeToSubtract) + " minutes " + currentSecondsToSubtract + " seconds");
                                            } else if (currentTimeToSubtract < 920) {
                                                timer.setText("Get to your next class");
                                            }
                                        } else if (currentTimeToSubtract > 920) {
                                            timer.setText("School's out!");
                                        }
                                    }

                                } else {
                                    timer.setText("Enjoy your weekend!!!");
                                }
                            }

                        });

                        SharedPreferences settingsPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
                        Boolean notifCheck = settingsPref.getBoolean("notif", false);
                        if (notifCheck) {
                            String message = timer.getText().toString();
                            String title = week.getText().toString();
                            createNotification(title, message);
                        }
                        else {
                            cancelNotification();
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

        mContext = this.getApplicationContext();
//    public void saveUserReminders(View view) {
//        SharedPreferences sharedPref = getSharedPreferences("userRem", Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString("save1", rem1.getText().toString());
//        editor.putString("save2", rem2.getText().toString());
//        editor.putString("save3", rem3.getText().toString());
//        editor.putString("save4", rem4.getText().toString());
//        editor.putString("save5", rem5.getText().toString());
//        editor.apply();
//
//        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
//    }

    }
    public void cancelNotification() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(NOTIFICATION_REQUEST_CODE); // Notification ID to cancel
    }

    @Override
    protected void onDestroy() {
        cancelNotification();
        super.onDestroy();
    }

    public void createNotification(String title, String message) {
        /**Creates an explicit intent for an Activity in your app**/
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,1 /* Request code */, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.new_notification);
        mBuilder.setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setOngoing(true)
//                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Countdown Notification", importance);
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.enableVibration(true);
//            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(NOTIFICATION_REQUEST_CODE, mBuilder.build());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

