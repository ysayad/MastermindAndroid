package dev.android.sae.mastermind.controller.menu;

import  android.app.Activity;
import android.content.Intent;
import android.view.View;

import dev.android.sae.mastermind.model.menu.ModelMenu;
import dev.android.sae.mastermind.view.GameActivity;

public class MenuButtonListener implements View.OnClickListener{

    private int soloButtonId;
    private Activity menuActivity;
    private ModelMenu model;

    public MenuButtonListener(int soloButtonId, Activity menuActivity, ModelMenu model){
        this.soloButtonId = soloButtonId;
        this.menuActivity = menuActivity;
        this.model = model;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == this.soloButtonId){
        }else{ //HotSeat est selectionné
            Intent i = new Intent(this.menuActivity, GameActivity.class);
            i.putExtra("emptyPawnsFlag",this.model.getEmptyPawnFlag());
            this.menuActivity.startActivity(i);
        }
    }
}