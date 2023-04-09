package dev.android.sae.mastermind.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import dev.android.sae.mastermind.R;
import dev.android.sae.mastermind.controller.choice.ChoiceButtonListener;
import dev.android.sae.mastermind.model.game.ModelGame;
import dev.android.sae.mastermind.model.menu.ModelMenu;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_choice);
        this.getIntent();
        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();
        Boolean empty_flag = extras.getBoolean("emptyPawnsFlag");

        if (!empty_flag) {
            this.findViewById(R.id.GRAY).setEnabled(false);
        } else {
            this.findViewById(R.id.GRAY).setEnabled(true);
        }

        ModelMenu model = new ModelMenu();
        Button confirm = (Button) this.findViewById(R.id.setting_validation_button);
        LinearLayout pawnRow = this.findViewById(R.id.pawnRow);
        ModelGame modelGame = new ModelGame(pawnRow);
        ChoiceButtonListener cbl = new ChoiceButtonListener(this, empty_flag, modelGame);
        LinearLayout buttonRow = this.findViewById(R.id.buttonRow);

        for (int i = 0; i < buttonRow.getChildCount(); i++) {
            Button b = (Button) buttonRow.getChildAt(i);
            b.setOnClickListener(cbl);

        }

        confirm.setOnClickListener(cbl);
    }
}
