import java.awt.*;

/**
 * Simulation class
 * Contains poles, performs simulation process
 */
public class Simulation {
    private final Pole[][] poles;
    private final int n;
    private final int m;

    /**
     * Creates Simulation
     * @param n number of rows in simulation
     * @param m number of column in simulation
     * @param k delay / speed of simulation
     * @param p probability for change color of pole
     */
    public Simulation(int n, int m, int k, double p) {
        this.n = n;
        this.m = m;
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

    /**
     * Gets array of poles for simulation
     * @return
     */
    public Pole[][] getPoles() {
        return poles;
    }

    /**
     * gets number of rows in simulation
     * @return number of rows
     */
    public int getN() {
        return n;
    }

    /**
     * gets number of columns in simulation
     * @return number of columns
     */
    public int getM() {
        return m;
    }

    /**
     * Repaints simulation
     * @param panel panel for repaint this simulation
     * @param g graphic context for repaint
     */
    public void repaint(SimulationPanel panel, Graphics g) {
        for (Pole[] pole : poles) {
            for (Pole value : pole) {
                value.draw(panel, g);
            }
        }
    }

    /**
     * Main part of simulation - calculates average color of neighbours
     * @param row row of pole
     * @param column column of pole
     * @return new color for pole object
     */
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

    /**
     * Gets random color for pole
     * @return generated random new color
     */
    public synchronized Color getRandomColor() {
        MyRandom random = MyRandom.getInstance();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    /**
     * Toggles active state of pole as thread
     * @param row row of pole
     * @param column column of pole
     */
    public synchronized void toggleActive(int row, int column) {
        poles[row][column].toggleActive();
    }
}
