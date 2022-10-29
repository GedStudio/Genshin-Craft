package net.deechael.genshin.api.player;

import java.util.Arrays;

public final class AdventureRanks {

    public final static int LEVEL_1 = 0;
    public final static int LEVEL_2 = 375;
    public final static int LEVEL_3 = 875;
    public final static int LEVEL_4 = 1500;
    public final static int LEVEL_5 = 2225;
    public final static int LEVEL_6 = 3075;
    public final static int LEVEL_7 = 4025;
    public final static int LEVEL_8 = 5100;
    public final static int LEVEL_9 = 6300;
    public final static int LEVEL_10 = 7600;
    public final static int LEVEL_11 = 9025;
    public final static int LEVEL_12 = 10550;
    public final static int LEVEL_13 = 12200;
    public final static int LEVEL_14 = 13975;
    public final static int LEVEL_15 = 15850;
    public final static int LEVEL_16 = 17850;
    public final static int LEVEL_17 = 20225;
    public final static int LEVEL_18 = 22725;
    public final static int LEVEL_19 = 25350;
    public final static int LEVEL_20 = 28125;
    public final static int LEVEL_21 = 30950;
    public final static int LEVEL_22 = 34375;
    public final static int LEVEL_23 = 38100;
    public final static int LEVEL_24 = 42100;
    public final static int LEVEL_25 = 46400;
    public final static int LEVEL_26 = 50975;
    public final static int LEVEL_27 = 55850;
    public final static int LEVEL_28 = 51000;
    public final static int LEVEL_29 = 66450;
    public final static int LEVEL_30 = 72175;
    public final static int LEVEL_31 = 78200;
    public final static int LEVEL_32 = 84500;
    public final static int LEVEL_33 = 91100;
    public final static int LEVEL_34 = 98000;
    public final static int LEVEL_35 = 105175;
    public final static int LEVEL_36 = 112650;
    public final static int LEVEL_37 = 120400;
    public final static int LEVEL_38 = 128450;
    public final static int LEVEL_39 = 136775;
    public final static int LEVEL_40 = 145400;
    public final static int LEVEL_41 = 155950;
    public final static int LEVEL_42 = 167475;
    public final static int LEVEL_43 = 179950;
    public final static int LEVEL_44 = 193400;
    public final static int LEVEL_45 = 207800;
    public final static int LEVEL_46 = 223150;
    public final static int LEVEL_47 = 239475;
    public final static int LEVEL_48 = 256750;
    public final static int LEVEL_49 = 275000;
    public final static int LEVEL_50 = 294000;
    public final static int LEVEL_51 = 320600;
    public final static int LEVEL_52 = 349400;
    public final static int LEVEL_53 = 380600;
    public final static int LEVEL_54 = 414200;
    public final static int LEVEL_55 = 450200;
    public final static int LEVEL_56 = 682550;
    public final static int LEVEL_57 = 941500;
    public final static int LEVEL_58 = 1227250;
    public final static int LEVEL_59 = 1540075;
    public final static int LEVEL_60 = 1880200;

    private final static int[] LEVELS = new int[] {
            LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5,
            LEVEL_6, LEVEL_7, LEVEL_8, LEVEL_9, LEVEL_10,
            LEVEL_11, LEVEL_12, LEVEL_13, LEVEL_14, LEVEL_15,
            LEVEL_16, LEVEL_17, LEVEL_18, LEVEL_19, LEVEL_20,
            LEVEL_21, LEVEL_22, LEVEL_23, LEVEL_24, LEVEL_25,
            LEVEL_26, LEVEL_27, LEVEL_28, LEVEL_29, LEVEL_30,
            LEVEL_31, LEVEL_32, LEVEL_33, LEVEL_34, LEVEL_35,
            LEVEL_36, LEVEL_37, LEVEL_38, LEVEL_39, LEVEL_40,
            LEVEL_41, LEVEL_42, LEVEL_43, LEVEL_44, LEVEL_45,
            LEVEL_46, LEVEL_47, LEVEL_48, LEVEL_49, LEVEL_50,
            LEVEL_51, LEVEL_52, LEVEL_53, LEVEL_54, LEVEL_55,
            LEVEL_56, LEVEL_57, LEVEL_58, LEVEL_59, LEVEL_60
    };

    public static int getLevel(int adventure_exp) {
        for (int i = 0; i < LEVELS.length; i++) {
            if (adventure_exp < LEVELS[i])
                return i;
        }
        return LEVEL_60;
    }

    public static int getWorldLevel(int adventure_exp) {
        int level = getLevel(adventure_exp);
        if (level <= 19)
            return 0;
        if (level <= 24)
            return 1;
        if (level <= 29)
            return 2;
        if (level <= 34)
            return 3;
        if (level <= 39)
            return 4;
        if (level <= 44)
            return 5;
        if (level <= 49)
            return 6;
        if (level <= 54)
            return 7;
        return 8;
    }

    public static int[] getLevels() {
        return Arrays.copyOf(LEVELS, LEVELS.length);
    }

    private AdventureRanks() {}

}
