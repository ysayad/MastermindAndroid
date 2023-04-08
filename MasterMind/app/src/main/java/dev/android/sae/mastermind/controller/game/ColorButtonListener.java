package dev.android.sae.mastermind.controller.game;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import dev.android.sae.mastermind.R;
import dev.android.sae.mastermind.model.game.Combination;
import dev.android.sae.mastermind.model.game.ModelGame;
import dev.android.sae.mastermind.model.game.Pawns;

public class ColorButtonListener implements View.OnClickListener {
    private ModelGame model;
    public ColorButtonListener (ModelGame model) {
        this.model = model;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        if (this.model.getProposalIndex()>=10) return;

        Pawns pawnColor = Pawns.GRAY;
        switch (view.getId()){
            case R.id.RED:
                pawnColor = Pawns.RED;
                break;

            case R.id.BLUE:
                pawnColor = Pawns.BLUE;
                break;

            case R.id.GREEN:
                pawnColor = Pawns.GREEN;
                break;

            case R.id.YELLOW:
                pawnColor = Pawns.YELLOW;
                break;

            case R.id.BLACK:
                pawnColor = Pawns.BLACK;
                break;

            case R.id.WHITE:
                pawnColor = Pawns.WHITE;
                break;

            case R.id.GRAY:
                pawnColor = Pawns.GRAY;
                break;
        }

        this.model.getProposal().addPawn(pawnColor);
        this.model.getPawn().setBackgroundTintList(view.getBackgroundTintList());
        this.model.setColorCount(this.model.getColorCount()+1);
        if (this.model.getColorCount()>=4) {
            Combination res = this.model.getProposal().compare(this.model.getDefendant());
            this.setResRowsColor(res);
            Log.d("w", res.winning()+"");

            this.model.setColorCount(0);
            this.model.setProposalIndex(this.model.getProposalIndex()+1);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void setResRowsColor(Combination res) {
        for (int i = 0; i < res.getComposition().length; i++) {
            View pawn = this.model.getResPawn(i);
            switch (res.getComposition()[i]) {
                case BLACK:
                    pawn.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                    break;
                case WHITE:
                    pawn.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                    break;
            }
        }
    }
}
