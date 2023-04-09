package dev.android.sae.mastermind.view;

import androidx.appcompat.app.AppCompatActivity;
import dev.android.sae.mastermind.R;
import dev.android.sae.mastermind.controller.game.ColorButtonListener;
import dev.android.sae.mastermind.model.game.Combination;
import dev.android.sae.mastermind.model.game.ModelGame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_game);
        this.getIntent();

        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();
        String defender = extras.getString("defender");
        Boolean empty_flag = extras.getBoolean("emptyPawnsFlag");

        if (!empty_flag) {
            this.findViewById(R.id.GRAY).setEnabled(false);
        } else {
            this.findViewById(R.id.GRAY).setEnabled(true);
        }

        TableLayout tl = this.findViewById(R.id.tableRow);
        LinearLayout[] rows = new LinearLayout[10];
        LinearLayout[] resRows = new LinearLayout[10];

        for (int i = 1; i < tl.getChildCount(); i++) {
            TableRow tr = (TableRow) tl.getChildAt(i);
            rows[i-1] = (LinearLayout) tr.getChildAt(0);
            resRows[i-1] = (LinearLayout) tr.getChildAt(1);

        }

        LinearLayout buttonBox = this.findViewById(R.id.buttonBox);
        ModelGame model = new ModelGame(rows, resRows, buttonBox, empty_flag);

        if (!defender.contains("EMPTY")) {
            Combination comb = Combination.stringToCombination(defender);
            model = new ModelGame(rows, resRows, buttonBox, comb);

        }
        ColorButtonListener cbl = new ColorButtonListener(model, buttonBox);
        LinearLayout buttonRow = this.findViewById(R.id.buttonRow);

        for (int i = 0; i < buttonRow.getChildCount(); i++) {
            Button b = (Button) buttonRow.getChildAt(i);
            b.setOnClickListener(cbl);

        }

        this.findViewById(R.id.screen).setOnTouchListener(cbl);
    }
}