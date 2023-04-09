package dev.android.sae.mastermind.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import dev.android.sae.mastermind.controller.menu.MenuButtonListener;
import dev.android.sae.mastermind.R;
import dev.android.sae.mastermind.controller.menu.MenuSettingButtonListener;
import dev.android.sae.mastermind.model.menu.ModelMenu;

public class MenuActivity extends AppCompatActivity {

    private int requestCode;
    private MenuButtonListener buttonListener;
    private MenuSettingButtonListener settingListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();

        this.requestCode = 1;

        setContentView(R.layout.activity_menu);
        ModelMenu model = new ModelMenu();

        //Attribution du listener aux boutons de choix du mode
        Button soloButton = (Button)this.findViewById(R.id.main_button_solo);
        Button hotSeatButton = (Button)this.findViewById(R.id.main_button_hot_seat);
        this.buttonListener = new MenuButtonListener(soloButton.getId(), this, model);
        soloButton.setOnClickListener(buttonListener);
        hotSeatButton.setOnClickListener(buttonListener);

        //Attribution du listener au bouton de configuration
        ImageButton settingsButton = (ImageButton) this.findViewById(R.id.setting_button);
        this.settingListener = new MenuSettingButtonListener(model, this, this.requestCode);
        settingsButton.setOnClickListener(this.settingListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == this.requestCode && resultCode == this.RESULT_OK){
            //On passe par le controleur pour changer le modele
            this.settingListener.setEmptyPawnsFlag(data.getBooleanExtra("empty_pawns",false));

        }

    }
}