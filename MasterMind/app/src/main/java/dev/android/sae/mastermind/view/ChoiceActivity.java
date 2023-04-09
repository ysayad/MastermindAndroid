package dev.android.sae.mastermind.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
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

        ModelMenu model = new ModelMenu();
        Button confirm = (Button) this.findViewById(R.id.setting_validation_button);
        LinearLayout pawnRow = this.findViewById(R.id.pawnRow);
        ModelGame modelGame = new ModelGame(pawnRow);
        ChoiceButtonListener cbl = new ChoiceButtonListener(this, empty_flag, modelGame);
        LinearLayout buttonRow = this.findViewById(R.id.buttonRow);
        Button b = null;
        if (empty_flag) {
            b = new Button(buttonRow.getChildAt(1).getContext());
            b.setBackground(this.getResources().getDrawable(R.drawable.pawn));
            b.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            b.setTag("GRAY");
            b.setLayoutParams(this.findViewById(R.id.RED).getLayoutParams());
            LinearLayout ll = (LinearLayout) buttonRow.getChildAt(1);
            ll.addView(b);
        }

        for (int i = 0; i < buttonRow.getChildCount(); i++) {
            LinearLayout ll = (LinearLayout) buttonRow.getChildAt(i);
            for (int j = 0; j < ll.getChildCount(); j++) {
                Button bc = (Button) ll.getChildAt(j);
                bc.setOnClickListener(cbl);
            }

        }

        confirm.setOnClickListener(cbl);
    }
}
