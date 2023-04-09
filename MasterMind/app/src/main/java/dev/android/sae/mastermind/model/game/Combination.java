package dev.android.sae.mastermind.model.game;

import android.util.Log;

import androidx.annotation.NonNull;

public class Combination {
    private final Pawns[] composition;
    private int index;
    private boolean empty_flag;

    /**
     * Constructeur de la classe
     */
    public Combination() {
        this.index = 0;
        this.composition = new Pawns[] {Pawns.EMPTY, Pawns.EMPTY, Pawns.EMPTY, Pawns.EMPTY};

    }

    public Combination(boolean empty_flag) {
        this.index = 0;
        this.composition = new Pawns[] {Pawns.EMPTY, Pawns.EMPTY, Pawns.EMPTY, Pawns.EMPTY};
        this.empty_flag = empty_flag;
    }

    /**
     * Ajoute un pion à la combinaison
     * @param pawn
     */
    public void addPawn(Pawns pawn) {
        if (this.index==4)
            return;
        this.composition[this.index] = pawn;
        this.index++;
    }

    /**
     * Retire un pion de la combinaison
     */
    public void removePawn() {
        if (this.index==0)
            return;
        this.index--;
        this.composition[this.index] = null;
    }

    /**
     * @param combinationB la combinaison avec laquelle comparer
     * @return la combinaison résultant de la comparaison
     */
    public Combination compare(Combination combinationB) {
        Combination result = new Combination();
        Combination firstCombination = new Combination();
        Combination secondCombination = new Combination();


        for(int i = 0; i < 4; i++) {
            firstCombination.composition[i] = this.composition[i];
            secondCombination.composition[i] = combinationB.composition[i];
            // On ne peut pas comparer une combinaison incomplète
            if (firstCombination.composition[i] == Pawns.EMPTY || secondCombination.composition[i] == Pawns.EMPTY)
                throw new IllegalArgumentException("Combination must be full");
            // On compare si les pions sont identiques
            if(firstCombination.composition[i] == secondCombination.composition[i]) {
                result.addPawn(Pawns.BLACK);
                firstCombination.composition[i] = null;
                secondCombination.composition[i] = null;
            }
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                // On compare si les pions sont présents dans la combinaison
                if(firstCombination.composition[i] == secondCombination.composition[j] && firstCombination.composition[i] != null) {
                    result.addPawn(Pawns.WHITE);
                    firstCombination.composition[i] = null;
                    secondCombination.composition[j] = null;
                }
            }
        }

        return result;

    }

    /**
     * Rempli la combinaison de manière aléatoire
     */
    public void randomComposition(boolean empty_flag) {
        for(int i = 0; i < 4; i++) {
            this.composition[i] = Pawns.getRandom(empty_flag);
        }
    }

    /**
     * @return true si la combinaison est gagnante
     */
    public boolean winning() {
        int score = 0;
        for(int i = 0; i < 4; i++) {
            if (this.composition[i] == Pawns.BLACK) {
                score++;
            }
        }

        return score == 4;
    }

    /**
     *
     * @return la composition de la combinaison
     */
    public Pawns[] getComposition() {
        return composition;
    }

    /**
     *
     * @param combination la string à convertir
     * @return la combinaison correspondante
     */
    public static Combination stringToCombination(String combination) {
        Combination result = new Combination();
        String[] split = combination.split(",");
        for(int i = 0; i < 4; i++) {
            result.composition[i] = Pawns.valueOf(split[i].trim());
        }
        return result;
    }

    /**
     *
     * @return la combinaison sous forme de string
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            result.append(this.composition[i].toString()).append(", ");
        }
        return result.toString();
    }
}
