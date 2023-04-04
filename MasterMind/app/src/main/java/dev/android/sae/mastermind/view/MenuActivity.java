package dev.android.sae.mastermind.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import dev.android.sae.mastermind.controller.menu.MenuButtonListener;
import dev.android.sae.mastermind.controller.menu.MenuCheckBoxListener;
import dev.android.sae.mastermind.R;
import dev.android.sae.mastermind.model.menu.ModelMenu;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_menu);
        ModelMenu model = new ModelMenu();

        //Attribution du listener aux boutons de choix du mode
        Button soloButton = (Button)this.findViewById(R.id.main_button_solo);
        Button hotSeatButton = (Button)this.findViewById(R.id.main_button_hot_seat);
        MenuButtonListener buttonListener = new MenuButtonListener(soloButton.getId(), this, model);
        soloButton.setOnClickListener(buttonListener);
        hotSeatButton.setOnClickListener(buttonListener);

        //Attribution du listener à la checkbox jetons vides
        CheckBox checkBox = (CheckBox) this.findViewById(R.id.main_checkbox_empty_pawn);
        checkBox.setOnCheckedChangeListener(new MenuCheckBoxListener(model));

    }
}