package dev.android.sae.mastermind.view;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import dev.android.sae.mastermind.R;
import dev.android.sae.mastermind.controller.game.ColorButtonListener;
import dev.android.sae.mastermind.model.game.Combination;
import dev.android.sae.mastermind.model.game.ModelGame;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
            model = new ModelGame(rows, resRows, buttonBox, comb, empty_flag);

        }
        ColorButtonListener cbl = new ColorButtonListener(model, buttonBox);
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

        this.findViewById(R.id.screen).setOnTouchListener(cbl);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                intent.putExtra("emptyPawnsFlag", empty_flag);
                startActivity(intent);
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
    }
}