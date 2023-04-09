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
        LinearLayout buttonBox = this.findViewById(R.id.buttonBox);
        ModelGame model = new ModelGame(false, rows, resRows, buttonBox);
        ColorButtonListener cbl = new ColorButtonListener(model, buttonBox);
        LinearLayout buttonRow = this.findViewById(R.id.buttonRow);
        for (int i = 0; i < buttonRow.getChildCount(); i++) {
            Button b = (Button) buttonRow.getChildAt(i);
            b.setOnClickListener(cbl);

        }
    }
}