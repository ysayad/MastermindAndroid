package dev.android.sae.mastermind.model.game;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class ModelGame {
    private boolean emptyPawnsFlags;
    private Combination[] proposals;
    private Combination defendant;
    private LinearLayout[] rows;
    private LinearLayout[] resRows;
    private int colorCount;
    private int proposalIndex;



    public ModelGame(boolean emptyPawnsFlags, LinearLayout[] rows, LinearLayout[] resRows){
        this.emptyPawnsFlags = emptyPawnsFlags;
        this.proposals = new Combination[10];
        for (int i = 0; i < 10; i++) {
            this.proposals[i] = new Combination();
        }
        this.colorCount = 0;
        this.proposalIndex = 0;
        this.rows = rows;
        this.resRows = resRows;
        this.defendant = new Combination();
        this.defendant.randomComposition();
        Log.d("defendant", this.defendant.toString());
    }

    public Combination getProposal() {
        return this.proposals[this.proposalIndex];
    }

    public int getProposalIndex() {
        return this.proposalIndex;
    }

    public void setProposalIndex(int proposalIndex) {
        this.proposalIndex = proposalIndex;
    }

    public int getColorCount() {
        return this.colorCount;
    }

    public void setColorCount(int colorCount) {
        this.colorCount = colorCount;
    }

    public View getPawn() {
        return this.rows[this.proposalIndex].getChildAt(this.colorCount);
    }

    public Combination getDefendant() {
        return this.defendant;
    }

    public View getResPawn(int index) {
        return this.resRows[this.proposalIndex].getChildAt(index);
    }


    /**
     * Ajoute un
     * @return
     */
  //
}
