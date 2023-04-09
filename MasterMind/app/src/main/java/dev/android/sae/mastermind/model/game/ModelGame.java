package dev.android.sae.mastermind.model.game;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import dev.android.sae.mastermind.R;

public class ModelGame {
    private final boolean emptyPawnsFlags;
    private final Combination[] proposals;
    private final Combination defendant;
    private final LinearLayout[] rows;
    private final LinearLayout[] resRows;
    private int colorCount;
    private int proposalIndex;
    private final LinearLayout buttonBox;



    public ModelGame(boolean emptyPawnsFlags, LinearLayout[] rows, LinearLayout[] resRows, LinearLayout buttonBox){
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
        this.buttonBox = buttonBox;
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

    public TableLayout getVictoryView() {
        TableLayout victoryView = new TableLayout(this.buttonBox.getContext());
        TextView victory = new TextView(victoryView.getContext());
        victory.setText("Vous avez gagné !");
        victory.setTextColor(Color.BLACK);
        TextView tryCount = new TextView(victoryView.getContext());
        tryCount.setText("Vous avez trouvé en " + (this.proposalIndex+1) + " essais");
        tryCount.setTextColor(Color.BLACK);
        LinearLayout victoryLayout = getDefendantCombinationView(victoryView.getContext());
        victoryView.addView(victory);
        victoryView.addView(tryCount);
        victoryView.addView(victoryLayout);

        return victoryView;
    }

    public TableLayout getDefeatView() {
        TableLayout defeatView = new TableLayout(this.buttonBox.getContext());
        TextView defeat = new TextView(defeatView.getContext());
        defeat.setText("Vous avez perdu !");
        defeat.setTextColor(Color.BLACK);
        TextView tryCount = new TextView(defeatView.getContext());
        tryCount.setText("Vous avez épuisé vos 10 essais");
        tryCount.setTextColor(Color.BLACK);
        LinearLayout defeatLayout = getDefendantCombinationView(defeatView.getContext());
        defeatView.addView(defeat);
        defeatView.addView(tryCount);
        defeatView.addView(defeatLayout);

        return defeatView;
    }

    public LinearLayout getDefendantCombinationView(Context ctx) {
        LinearLayout ll = new LinearLayout(ctx);
        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView tv = new TextView(ctx);
        tv.setTextColor(Color.BLACK);
        tv.setText("La combinaison était : ");
        ll.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout rl = new RelativeLayout(ll.getContext());
        rl.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rl.setBackgroundResource(R.drawable.rounded_row);
        rl.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.game_row_color)));
        rl.setPadding(10, 10, 10, 10);
        rl.setGravity(Gravity.CENTER);
        Pawns[] pawns = this.defendant.getComposition();
        for (int i = 0; i < 4; i++) {
            View pawn = new View(ctx);
            int size = (int) ctx.getResources().getDimension(R.dimen.game_big_pawn_radius);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size, size);
            pawn.setBackgroundResource(R.drawable.pawn);
            pawn.setBackgroundTintList(ColorStateList.valueOf(pawns[i].getColor()));
            pawn.setId(i+1);
            params.leftMargin = 10;
            params.rightMargin = 10;
            if (i>0) {
                params.addRule(RelativeLayout.RIGHT_OF, i);
            }
            rl.addView(pawn, params);

        }
        ll.addView(tv);
        ll.addView(rl);

        Log.d("defendantCombinationVie", rl.getChildCount()+"");
        return ll;
    }


    //
}
