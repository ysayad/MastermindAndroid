package dev.android.sae.mastermind.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import dev.android.sae.mastermind.R;
import dev.android.sae.mastermind.controller.choice.ChoiceButtonListener;
import dev.android.sae.mastermind.controller.menu.MenuButtonListener;
import dev.android.sae.mastermind.model.menu.ModelMenu;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_choice);
        this.getIntent();

        ModelMenu model = new ModelMenu();
        Button confirm = (Button)this.findViewById(R.id.setting_validation_button);
        ChoiceButtonListener buttonListener = new ChoiceButtonListener(this, model);
        confirm.setOnClickListener(buttonListener);
    }
}
