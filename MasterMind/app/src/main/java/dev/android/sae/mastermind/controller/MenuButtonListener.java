package dev.android.sae.mastermind.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

public class MenuButtonListener implements View.OnTouchListener{

    private int soloButtonId;
    private Activity menuActivity;

    public MenuButtonListener(int soloButtonId, Activity menuActivity){
        this.soloButtonId = soloButtonId;
        this.menuActivity = menuActivity;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(view.getId() === this.soloButtonId){

        }else{ //HotSeat est selectionné
            Intent i = new Intent(this.menuActivity, DefenseurActivity.class);
        }
        return true;//On a consommé l'évènement
    }
}
