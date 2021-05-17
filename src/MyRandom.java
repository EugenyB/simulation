import java.util.Random;

/**
 * MyRandom - Singleton for access from other parts of program
 * contains java.util.Random for perform operations: (nextInt, nextDouble)
 */
public class MyRandom {

    /**
     * private Random object for perform functionality
     */
    private final Random random;

    /**
     * static instance of MyRandom as specified in singleton
     */
    private static final MyRandom instance = new MyRandom();

    /**
     * Creates MyRandom as singleton
     */
    private MyRandom() {
        random = new Random();
    }

    /**
     * method for access one instance of MyRandom
     * @return
     */
    public static MyRandom getInstance() {
        return instance;
    }

    /**
     * Delegated method of Random - generates random int in [0;bound)
     * @param bound upper bound  for random
     * @return random int
     */
    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    /**
     * Delegated method of Random - generates random double in [0.0;1.0)
     * @return random double
     */
    public double nextDouble() {
        return random.nextDouble();
    }
}
