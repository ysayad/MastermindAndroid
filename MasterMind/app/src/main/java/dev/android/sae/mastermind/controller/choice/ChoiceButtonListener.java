package dev.android.sae.mastermind.controller.choice;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import dev.android.sae.mastermind.model.menu.ModelMenu;
import dev.android.sae.mastermind.view.ChoiceActivity;
import dev.android.sae.mastermind.view.GameActivity;


public class ChoiceButtonListener implements View.OnClickListener{

    private Activity choiceActivity;
    private ModelMenu model;

    public ChoiceButtonListener(Activity menuActivity, ModelMenu model){
        this.choiceActivity = menuActivity;
        this.model = model;
    }

    @Override
    public void onClick(View view) {
            Intent i = new Intent(this.choiceActivity, GameActivity.class);
            i.putExtra("emptyPawnsFlag",this.model.getEmptyPawnFlag());
            this.choiceActivity.startActivity(i);
    }
}
