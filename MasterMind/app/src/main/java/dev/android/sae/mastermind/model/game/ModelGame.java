package dev.android.sae.mastermind.model.game;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import dev.android.sae.mastermind.R;

public class ModelGame {
    private final Combination[] proposals;
    private final Combination defendant;
    private final LinearLayout[] rows;
    private final LinearLayout[] resRows;
    private int colorCount;
    private int proposalIndex;
    private final LinearLayout buttonBox;


    /**
     * Constructeur pour le mode solo
     * @param rows les lignes de la proposition
     * @param resRows les lignes des résultats
     * @param buttonBox la boite de boutons
     */
    public ModelGame(LinearLayout[] rows, LinearLayout[] resRows, LinearLayout buttonBox, boolean empty_flag){
        this.proposals = new Combination[10];
        for (int i = 0; i < 10; i++) {
            this.proposals[i] = new Combination();
        }
        this.colorCount = 0;
        this.proposalIndex = 0;
        this.rows = rows;
        this.resRows = resRows;
        this.defendant = new Combination();
        this.defendant.randomComposition(empty_flag);
        this.buttonBox = buttonBox;
    }

    /**
     * Construteur pour le mode hotseat
     * @param rows les lignes de la proposition
     * @param resRows les lignes des résultats
     * @param buttonBox la boite de boutons
     * @param defendant la combinaison secrète du défenseur
     */
    public ModelGame(LinearLayout[] rows, LinearLayout[] resRows, LinearLayout buttonBox, Combination defendant){
        this.proposals = new Combination[10];
        for (int i = 0; i < 10; i++) {
            this.proposals[i] = new Combination();
        }
        this.colorCount = 0;
        this.proposalIndex = 0;
        this.rows = rows;
        this.resRows = resRows;
        this.defendant = defendant;
        this.buttonBox = buttonBox;
    }

    /**
     * Constructeur pour le model defenseur
     * @param row la ligne de la proposition
     */
    public ModelGame(LinearLayout row) {
        this.rows = new LinearLayout[1];
        this.rows[0] = row;
        this.resRows = null;
        this.buttonBox = null;
        this.defendant = null;
        this.proposals = null;
        this.proposalIndex = 0;
        this.colorCount = 0;

    }

    /**
     *
     * @return la proposition de la ligne
     */
    public Combination getProposal() {
        return this.proposals[this.proposalIndex];
    }

    /**
     *
     * @return l'index de la ligne de la proposition
     */
    public int getProposalIndex() {
        return this.proposalIndex;
    }

    /**
     *
     * @param proposalIndex l'index de la ligne de la proposition
     */
    public void setProposalIndex(int proposalIndex) {
        this.proposalIndex = proposalIndex;
    }

    /**
     *
     * @return le nombre de pions de la ligne de proposition
     */
    public int getColorCount() {
        return this.colorCount;
    }

    /**
     *
     * @param colorCount le nombre de pions de la ligne de proposition
     */
    public void setColorCount(int colorCount) {
        this.colorCount = colorCount;
    }

    /**
     *
     * @return le premier pion non rempli de la ligne de proposition
     */
    public View getPawn() {
        return this.rows[this.proposalIndex].getChildAt(this.colorCount);
    }

    /**
     *
     * @return la combinaison secrète du défenseur
     */
    public Combination getDefendant() {
        return this.defendant;
    }

    /**
     *
     * @param index l'index de la ligne de la proposition
     * @return la vue de la ligne du résultat la proposition
     */
    public View getResPawn(int index) {
        return this.resRows[this.proposalIndex].getChildAt(index);
    }

    /**
     *
     * @return le table layout contenant le message de victoire et la combinaison gagnante
     */
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

    /**
     *
     * @return le table layout contenant le message de défaite et la combinaison gagnante
     */
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

    /**
     *
     * @param ctx le contexte de l'application
     * @return le linear layout contenant la combinaison gagnante
     */
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

        return ll;
    }
}
