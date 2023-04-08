package dev.android.sae.mastermind.model.game;

import java.util.Random;

public enum Pawns {
    RED,
    GREEN,
    BLUE,
    YELLOW,
    WHITE,
    BLACK,
    GRAY;

    private static final Random RANDOM = new Random();

    public static Pawns getRandom() {
        Pawns ret = values()[RANDOM.nextInt(values().length)];
        while (ret==GRAY)
            ret = values()[RANDOM.nextInt(values().length)];
        return ret;
    }
}
