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
}
