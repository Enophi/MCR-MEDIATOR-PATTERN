package ch.heig.utils;
import java.util.Random;

public class Rand {

    private static Random random;

    private static synchronized Random getInstance() {
        if (random == null)
            random = new Random();
        return random;
    }

    public static boolean getRandomBool() {
        return getInstance().nextBoolean();
    }

    public static int getRandomInt(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }
}
