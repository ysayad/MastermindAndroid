package dev.android.sae.mastermind.controller.game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import dev.android.sae.mastermind.R;
import dev.android.sae.mastermind.model.game.Combination;
import dev.android.sae.mastermind.model.game.ModelGame;
import dev.android.sae.mastermind.model.game.Pawns;
import dev.android.sae.mastermind.view.MenuActivity;

public class ColorButtonListener implements View.OnClickListener, View.OnTouchListener {
    private final ModelGame model;
    private final LinearLayout buttonBox;
    private boolean ended = false;

    public ColorButtonListener (ModelGame model, LinearLayout buttonBox) {
        this.model = model;
        this.buttonBox = buttonBox;

    }

    @SuppressLint({"ResourceAsColor", "NonConstantResourceId"})
    @Override
    public void onClick(View view) {
        Pawns pawnColor = Pawns.GRAY;
        // On récupère la couleur du pion
        pawnColor = Pawns.valueOf(view.getTag().toString());

        this.model.getProposal().addPawn(pawnColor);
        this.model.getPawn().setBackgroundTintList(ColorStateList.valueOf(pawnColor.getColor()));
        this.model.setColorCount(this.model.getColorCount()+1);

        if (this.model.getColorCount()>=4) {
            Combination res = this.model.getProposal().compare(this.model.getDefendant());
            if (res.winning()) {
                this.buttonBox.removeAllViews();
                TableLayout tableLayout = this.model.getVictoryView();
                this.buttonBox.addView(tableLayout);
                this.buttonBox.invalidate();
                this.ended = true;
            }
            this.setResRowsColor(res);


            this.model.setColorCount(0);
            this.model.setProposalIndex(this.model.getProposalIndex()+1);
            if (this.model.getProposalIndex()>=10) {
                this.buttonBox.removeAllViews();
                TableLayout tableLayout = this.model.getDefeatView();
                this.buttonBox.addView(tableLayout);
                this.buttonBox.invalidate();
                this.ended = true;
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (this.ended) {
                    Intent intent = new Intent(view.getContext(), MenuActivity.class);
                    intent.putExtra("emptyPawnsFlag", this.model.getEmptyPawnsFlag());
                    view.getContext().startActivity(intent);

                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    /**
     * Permet de changer la couleur des pions du résultat
     * @param res la combinaison de résultat
     */
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
