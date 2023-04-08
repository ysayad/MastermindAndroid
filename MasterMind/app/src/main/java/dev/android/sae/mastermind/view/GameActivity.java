package dev.android.sae.mastermind.view;

import androidx.appcompat.app.AppCompatActivity;
import dev.android.sae.mastermind.R;
import dev.android.sae.mastermind.controller.game.ColorButtonListener;
import dev.android.sae.mastermind.model.game.ModelGame;

import android.os.Bundle;
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
        TableLayout tl = this.findViewById(R.id.tableRow);
        LinearLayout[] rows = new LinearLayout[10];
        LinearLayout[] resRows = new LinearLayout[10];
        for (int i = 1; i < tl.getChildCount(); i++) {
            TableRow tr = (TableRow) tl.getChildAt(i);
            rows[i-1] = (LinearLayout) tr.getChildAt(0);
            resRows[i-1] = (LinearLayout) tr.getChildAt(1);

        }
        ModelGame model = new ModelGame(false, rows, resRows);
        ColorButtonListener cbl = new ColorButtonListener(model);
        TableLayout buttonRow = this.findViewById(R.id.buttonRow);
        for (int i = 0; i < buttonRow.getChildCount(); i++) {
            LinearLayout ll = (LinearLayout) buttonRow.getChildAt(i);
            for (int j = 0; j < ll.getChildCount(); j++) {
                Button b = (Button) ll.getChildAt(j);
                b.setOnClickListener(cbl);
            }
        }
    }
}