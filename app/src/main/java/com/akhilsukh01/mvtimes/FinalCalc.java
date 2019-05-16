package com.akhilsukh01.mvtimes;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FinalCalc extends AppCompatActivity {

    Button calc;
    EditText gradeCurrent;
    EditText gradeNeeded;
    EditText finalWeight;
    TextView message;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView gradeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settingsPref = getSharedPreferences("settings", Context.MODE_PRIVATE);
        Boolean themeCheck = settingsPref.getBoolean("theme", false);
        if (themeCheck){
            setTheme(R.style.MyMaterialDarkTheme);
        }
        else {
            setTheme(R.style.MyMaterialLightTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalcalc);

        calc = (Button) findViewById(R.id.calc);
        message = findViewById(R.id.message);
        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);
        text3 = findViewById(R.id.textView3);
        text4 = findViewById(R.id.textView4);
        text5 = findViewById(R.id.textView5);
        text2 = findViewById(R.id.textView2);
        text3 = findViewById(R.id.textView3);
        text4 = findViewById(R.id.textView4);
        text5 = findViewById(R.id.textView5);

        gradeCurrent = findViewById(R.id.gradeCurrent);
        gradeNeeded = findViewById(R.id.gradeNeeded);
        finalWeight = findViewById(R.id.finalWeight);
        gradeView = findViewById(R.id.gradeView);

        Typeface regular = Typeface.createFromAsset(getAssets(), "fonts/sf_pro_display_regular.ttf");
        text1.setTypeface(regular);
        text2.setTypeface(regular);
        text3.setTypeface(regular);
        text4.setTypeface(regular);
        text5.setTypeface(regular);
        message.setTypeface(regular);
        gradeCurrent.setTypeface(regular);
        gradeNeeded.setTypeface(regular);
        finalWeight.setTypeface(regular);
        gradeView.setTypeface(regular);

        Typeface semibold = Typeface.createFromAsset(getAssets(), "fonts/sf_pro_display_semibold.ttf");
        calc.setTypeface(semibold);

    }


    public void main(View view){

        if (gradeCurrent.getText().toString().equals("")  ){
            Toast.makeText(FinalCalc.this, "Your current grade doesn’t look right. Please put in a number for your current grade.", Toast.LENGTH_LONG).show();
        }
        else if (gradeNeeded.getText().toString().equals("")){
            Toast.makeText(FinalCalc.this, "Your target goal grade doesn’t look right. Please put in a number for your target grade.", Toast.LENGTH_LONG).show();
        }
        else if (finalWeight.getText().toString().equals("")){
            Toast.makeText(FinalCalc.this, "Your final percentage doesn’t look right. Please put in a number for your final percentage.", Toast.LENGTH_LONG).show();
        }
        else {
            Double Cv = Double.parseDouble(gradeCurrent.getText().toString());
            Double Rv = Double.parseDouble(gradeNeeded.getText().toString());
            Double Fv = Double.parseDouble(finalWeight.getText().toString());

            Double finalValue = (((100*Rv) - ((100-Fv)*Cv))/Fv);
            String finalVal = String.valueOf(finalValue);
            String finalV = finalVal.format("%.5s", finalVal);
            gradeView.setText(" " + finalV + "%");

            Log.i("cAmount", gradeCurrent.getText().toString());

            String parse1 = ("#" + "DBB9B9");
            String parse2 = ("#" + "DE8F8F");
            String parse3 = ("#" + "CB8787");
            String parse4 = ("#" + "D54D4D");
            String parse5 = ("#" + "D13E3E");
            String parse6 = ("#" + "ED0808");



            if (finalValue > 100){
                message.setText("On the bright side, grades don’t really matter anyway.");
                message.setTextColor(Color.parseColor(parse6));
                gradeView.setTextColor(Color.parseColor(parse6));
            }
            else if (finalValue > 90 && finalValue < 100){
                message.setText("Don’t give up! I believe in you!");
                message.setTextColor(Color.parseColor(parse5));
                gradeView.setTextColor(Color.parseColor(parse5));
            }
            else if (finalValue > 80 && finalValue < 90){
                message.setText("Relax, you can do it!");
                message.setTextColor(Color.parseColor(parse4));
                gradeView.setTextColor(Color.parseColor(parse4));
            }
            else if (finalValue > 70 && finalValue < 80){
                message.setText("You can do it, no problem!");
                message.setTextColor(Color.parseColor(parse3));
                gradeView.setTextColor(Color.parseColor(parse3));
            }
            else if (finalValue > 60 && finalValue < 70){
                message.setText(" Have fun (doing other things)!");
                message.setTextColor(Color.parseColor(parse2));
                gradeView.setTextColor(Color.parseColor(parse2));
            }
            else if (finalValue > 50 && finalValue < 60){
                message.setText("You don’t even need to bother studying.");
                message.setTextColor(Color.parseColor(parse1));
                gradeView.setTextColor(Color.parseColor(parse1));
            }
            else if (finalValue > 20 && finalValue < 50){
                message.setText("Maybe you can just draw a flower on the test or something.");
            }
        }
    }

}
