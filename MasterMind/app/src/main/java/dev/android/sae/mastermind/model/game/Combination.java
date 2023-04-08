package dev.android.sae.mastermind.model.game;

import androidx.annotation.NonNull;

public class Combination {
    private Pawns[] composition;
    private int index;
    public Combination() {
        this.index = 0;
        this.composition = new Pawns[] {Pawns.GRAY, Pawns.GRAY, Pawns.GRAY, Pawns.GRAY};
    }

    public void addPawn(Pawns pawn) {
        if (this.index==4)
            throw new IllegalStateException("Composition is already full");
        this.composition[this.index] = pawn;
        this.index++;
    }

    public void removePawn() {
        if (this.index==0)
            throw new IllegalStateException("Composition is already empty");
        this.index--;
        this.composition[this.index] = null;
    }

    public Combination compare(Combination combinationB) {
        Combination result = new Combination();
        Combination firstCombination = new Combination();
        Combination secondCombination = new Combination();


        for(int i = 0; i < 4; i++) {
            firstCombination.composition[i] = this.composition[i];
            secondCombination.composition[i] = combinationB.composition[i];
            if(firstCombination.composition[i] == Pawns.GRAY || secondCombination.composition[i ]== Pawns.GRAY)
                throw new IllegalStateException("Combination isn't complete !");
            else if(firstCombination.composition[i] == secondCombination.composition[i]) {
                result.addPawn(Pawns.BLACK);
                firstCombination.composition[i] = null;
                secondCombination.composition[i] = null;
            }
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(firstCombination.composition[i] == secondCombination.composition[j] && firstCombination.composition[i] != null) {
                    result.addPawn(Pawns.WHITE);
                    firstCombination.composition[i] = null;
                    secondCombination.composition[j] = null;
                }
            }
        }

        return result;

    }

    public void randomComposition() {
        for(int i = 0; i < 4; i++) {
            this.composition[i] = Pawns.getRandom();
        }
    }

    public boolean winning() {
        int score = 0;
        for(int i = 0; i < 4; i++) {
            if (this.composition[i] == Pawns.BLACK) {
                score++;
            }
        }

        return score == 4;
    }

    public Pawns[] getComposition() {
        return composition;
    }

    @NonNull
    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < 4; i++) {
            result += this.composition[i].toString()+", ";
        }
        return result;
    }
}
