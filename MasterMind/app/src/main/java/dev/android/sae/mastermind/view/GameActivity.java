package dev.android.sae.mastermind.view;

import androidx.appcompat.app.AppCompatActivity;
import dev.android.sae.mastermind.R;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_game);
        this.getIntent();
    }
}