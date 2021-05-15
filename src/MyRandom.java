import java.util.Random;

public class MyRandom {
    private final Random random;

    private static final MyRandom instance = new MyRandom();

    private MyRandom() {
        random = new Random();
    }

    public static MyRandom getInstance() {
        return instance;
    }


    public int nextInt() {
        return random.nextInt();
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public double nextDouble() {
        return random.nextDouble();
    }
}
