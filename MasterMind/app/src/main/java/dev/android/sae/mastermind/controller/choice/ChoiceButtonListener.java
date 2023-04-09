package dev.android.sae.mastermind.controller.choice;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import dev.android.sae.mastermind.R;
import dev.android.sae.mastermind.model.game.Combination;
import dev.android.sae.mastermind.model.game.ModelGame;
import dev.android.sae.mastermind.model.game.Pawns;
import dev.android.sae.mastermind.model.menu.ModelMenu;
import dev.android.sae.mastermind.view.ChoiceActivity;
import dev.android.sae.mastermind.view.GameActivity;


public class ChoiceButtonListener implements View.OnClickListener{

    private Activity choiceActivity;
    private boolean empty_flag;
    private Combination defender;
    private ModelGame modelg;
    private int colorCount;

    public ChoiceButtonListener(Activity menuActivity, Boolean empty_flag, ModelGame modelg){
        this.choiceActivity = menuActivity;
        this.empty_flag = empty_flag;
        this.defender = new Combination();
        this.modelg = modelg;
        this.colorCount = 0;
    }

    @Override
    public void onClick(View view) {

        // Ajouter une couleur à la combinaison défenseur


        if (view.getId()==R.id.setting_validation_button) {
            if (this.modelg.getColorCount() == 4) {
                Intent i = new Intent(this.choiceActivity, GameActivity.class);
                i.putExtra("emptyPawnsFlag", this.empty_flag);
                i.putExtra("defender", this.defender.toString());
                this.choiceActivity.startActivity(i);
            }
        } else {
            if (this.modelg.getColorCount() <4) {
                Pawns color = Pawns.valueOf(view.getTag().toString());
                this.defender.addPawn(color);
                this.modelg.getPawn().setBackgroundTintList(ColorStateList.valueOf(color.getColor()));
                this.modelg.setColorCount(this.modelg.getColorCount() + 1);
            }
        }




    }
}
