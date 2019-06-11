package ch.heig.utils;
import java.util.Random;

/**
 * The type Rand.
 */
public class Rand {

    private static Random random;

    private static synchronized Random getInstance() {
        if (random == null)
            random = new Random();
        return random;
    }

    /**
     * Gets random bool.
     *
     * @return the random bool
     */
    public static boolean getRandomBool() {
        return getInstance().nextBoolean();
    }

    /**
     * Gets random int.
     *
     * @param min the min
     * @param max the max
     * @return the random int
     */
    public static int getRandomInt(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }
}
