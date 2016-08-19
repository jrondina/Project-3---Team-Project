package com.jonathan.james.eric.project_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    Switch mSwitch;
    NumberPicker mPicker;
    private int hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mPicker = (NumberPicker) findViewById(R.id.numberPicker);
        mSwitch = (Switch) findViewById(R.id.notificationsSwitch);






    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int old, int i1) {
        hours = i1;

    }
}
