import java.awt.*;

public class Simulation {
    private Pole[][] poles;
    private int k;
    private int n;
    private int m;
    private double p;

    public Simulation(int n, int m, int k, double p) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.p = p;
        poles = new Pole[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                poles[i][j] = new Pole(k, p, i, j);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                new Thread(poles[i][j]).start();
            }
        }
    }

    public Pole[][] getPoles() {
        return poles;
    }

    public void setPoles(Pole[][] poles) {
        this.poles = poles;
    }

    public void setSpeed(int k) {
        this.k = k;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public void repaint(SimulationPanel panel, Graphics g) {
        for (int i = 0; i < poles.length; i++) {
            for (int j = 0; j < poles[i].length; j++) {
                poles[i][j].draw(panel, g);
            }
        }
    }

    public synchronized Color getNewColor(int row, int column) {
        float[] avg = new float[3];
        int count = 0;
        if (poles[(row - 1 + n) % n][column].isActive()) {
            Color color = poles[(row - 1 + n) % n][column].getColor();
            float[] rgb = color.getRGBColorComponents(null);
            for (int i = 0; i < 3; i++) {
                avg[i] += rgb[i];
            }
            count++;
        }
        if(poles[(row + 1) % n][column].isActive()) {
            Color color = poles[(row + 1) % n][column].getColor();
            float[] rgb = color.getRGBColorComponents(null);
            for (int i = 0; i < 3; i++) {
                avg[i] += rgb[i];
            }
            count++;
        }
        if(poles[row][(column - 1 + m) % m].isActive()) {
            Color color = poles[row][(column - 1 + m) % m].getColor();
            float[] rgb = color.getRGBColorComponents(null);
            for (int i = 0; i < 3; i++) {
                avg[i] += rgb[i];
            }
            count++;
        }
        if (poles[row][(column + 1) % m].isActive()) {
            Color color = poles[row][(column + 1) % m].getColor();
            float[] rgb = color.getRGBColorComponents(null);
            for (int i = 0; i < 3; i++) {
                avg[i] += rgb[i];
            }
            count++;
        }
        if (count != 0) {
            for (int i = 0; i < 3; i++) {
                avg[i] /= count;
            }
            return new Color(avg[0], avg[1], avg[2]);
        } else {
            return poles[row][column].getColor();
        }
    }

    public synchronized Color getRandomColor() {
        MyRandom random = MyRandom.getInstance();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public synchronized void toggleActive(int row, int column) {
        poles[row][column].toggleActive();
    }
}
