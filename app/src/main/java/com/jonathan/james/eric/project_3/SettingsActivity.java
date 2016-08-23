package com.jonathan.james.eric.project_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener, CompoundButton.OnCheckedChangeListener {

    Switch mSwitch;
    NumberPicker mPicker;
    Button mButton;

    private int hours;
    private boolean toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mPicker = (NumberPicker) findViewById(R.id.numberPicker);
        mSwitch = (Switch) findViewById(R.id.notificationsSwitch);
        mButton = (Button) findViewById(R.id.button);

        final Intent resultIntent = new Intent();
        resultIntent.putExtra("hours",hours);
        resultIntent.putExtra("switch",toggle);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        };

        mButton.setOnClickListener(listener);

    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int old, int i1) {
        hours = i1;

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        toggle = b;
    }
}