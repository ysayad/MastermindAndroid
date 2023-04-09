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
    GRAY;

    private static final Random RANDOM = new Random();

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

    public static Pawns getRandom() {
        Pawns ret = values()[RANDOM.nextInt(values().length)];
        while (ret==GRAY || ret==WHITE || ret==BLACK)
            ret = values()[RANDOM.nextInt(values().length)];
        return ret;
    }
}
