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
        Pawns color = null;
        // Ajouter une couleur à la combinaison défenseur
        switch (view.getId()){
            case R.id.RED:
                this.defender.addPawn(Pawns.RED);
                color = Pawns.RED;
                break;

            case R.id.BLUE:
                this.defender.addPawn(Pawns.BLUE);
                color = Pawns.BLUE;
                break;

            case R.id.GREEN:
                this.defender.addPawn(Pawns.GREEN);
                color = Pawns.GREEN;
                break;

            case R.id.YELLOW:
                this.defender.addPawn(Pawns.YELLOW);
                color = Pawns.YELLOW;
                break;

            case R.id.GRAY:
                this.defender.addPawn(Pawns.GRAY);
                color = Pawns.GRAY;
                break;

            case R.id.setting_validation_button:
                if (this.modelg.getColorCount() == 4) {
                    Intent i = new Intent(this.choiceActivity, GameActivity.class);
                    i.putExtra("emptyPawnsFlag",this.empty_flag);
                    i.putExtra("defender", this.defender.toString());
                    this.choiceActivity.startActivity(i);
                }
                break;
        }
        if (color != null && view.getId() != R.id.setting_validation_button) {
            Log.d("defenderC", this.defender.toString());
            this.modelg.getPawn().setBackgroundTintList(ColorStateList.valueOf(color.getColor()));
            this.modelg.setColorCount(this.modelg.getColorCount() + 1);
        }

    }
}
