package dev.android.sae.mastermind.controller.menu;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;

import dev.android.sae.mastermind.model.menu.ModelMenu;
import dev.android.sae.mastermind.view.GameActivity;
import dev.android.sae.mastermind.view.SettingsActivity;

public class MenuSettingButtonListener implements View.OnClickListener {

    private ModelMenu model;
    private Activity menuActivity;
    private int requestCode;

    public MenuSettingButtonListener(ModelMenu model, Activity menuActivity, int requestCode){
        this.model = model;
        this.menuActivity = menuActivity;
        this.requestCode = requestCode;
    }

    @Override
    public void onClick(View view) {
            Intent i = new Intent(this.menuActivity, SettingsActivity.class);
            i.putExtra("emptyPawnsFlag",this.model.getEmptyPawnFlag());
            this.menuActivity.startActivityForResult(i,this.requestCode);
        }

    public void setEmptyPawnsFlag(boolean flag){
        this.model.setEmptyPawnActivated(flag);
    }
    }

