package com.akhilsukh01.mvtimes;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.Button;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    Button bClasses;
    Button bBellSchedule;
    Button bReminders;
    Button bFinalCalc;
    Button bSettings;

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet, null);
        dialog.setContentView(contentView);
        bBellSchedule = contentView.findViewById(R.id.buttonBellSchedule);
        bBellSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TimeTableContainer.class);
                startActivity(intent);
            }
        });

        bClasses = contentView.findViewById(R.id.buttonEditClasses);
        bClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditClassesContainer.class);
                startActivity(intent);
            }
        });

        bReminders = contentView.findViewById(R.id.buttonAddReminders);
        bReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AgendaActivity.class);
                startActivity(intent);
            }
        });

        bFinalCalc = contentView.findViewById(R.id.buttonFinalCalc);
        bFinalCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FinalCalc.class);
                startActivity(intent);
            }
        });

        bSettings = contentView.findViewById(R.id.buttonSettings);
        bSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        Typeface sf_pro_medium = Typeface.createFromAsset(getContext().getAssets(), "fonts/sf_pro_display_semibold.ttf");
        bClasses.setTypeface(sf_pro_medium);
        bBellSchedule.setTypeface(sf_pro_medium);
        bReminders.setTypeface(sf_pro_medium);
        bFinalCalc.setTypeface(sf_pro_medium);
        bSettings.setTypeface(sf_pro_medium);
    }


}