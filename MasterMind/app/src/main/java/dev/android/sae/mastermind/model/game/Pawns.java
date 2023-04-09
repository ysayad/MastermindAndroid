package dev.android.sae.mastermind.model.game;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;

import java.util.Random;

import dev.android.sae.mastermind.R;

public enum Pawns {
    RED,
    GREEN,
    BLUE,
    YELLOW,
    WHITE,
    BLACK,
    GRAY,
    EMPTY;

    private static final Random RANDOM = new Random();

    /**
     *
     * @return la couleur correspondant au pion
     */
    public int getColor() {
        switch (this) {
            case RED:
                return Color.RED;
            case GREEN:
                return Color.GREEN;
            case BLUE:
                return Color.BLUE;
            case YELLOW:
                return Color.YELLOW;
            case WHITE:
                return Color.WHITE;
            case BLACK:
                return Color.BLACK;
            case GRAY:
                return Color.GRAY;
        }
        return Color.GRAY;
    }

    /**
     *
     * @param empty_flag true si les pions vide (GRAY) sont autorisés
     * @return un pion aléatoire
     */
    public static Pawns getRandom(boolean empty_flag) {
        Pawns ret = values()[RANDOM.nextInt(values().length)];
        if (empty_flag)
            while (ret==WHITE || ret==BLACK || ret==EMPTY)
                ret = values()[RANDOM.nextInt(values().length)];
        else
            while (ret==WHITE || ret==BLACK || ret==EMPTY || ret==GRAY)
                ret = values()[RANDOM.nextInt(values().length)];
        return ret;
    }
}
