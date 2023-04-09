package dev.android.sae.mastermind.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import dev.android.sae.mastermind.R;
import dev.android.sae.mastermind.controller.settings.SettingsControler;

public class SettingsActivity extends AppCompatActivity {

    private SettingsControler controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);

        this.controller = new SettingsControler(this);
        CheckBox checkBox = (CheckBox) this.findViewById(R.id.setting_checkbox);
        Button button = (Button) this.findViewById(R.id.setting_validation_button);

        checkBox.setOnCheckedChangeListener(this.controller);
        button.setOnClickListener(this.controller);


    }
}