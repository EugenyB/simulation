import java.awt.*;

/**
 * Pole - cell for simulation
 */
public class Pole implements Runnable {
    /**
     * current color of pole
     */
    private Color color;

    /**
     * delay for simulation
     */
    private final int delay;

    /**
     * row of pole
     */
    private final int row;

    /**
     * column of pole
     */
    private final int column;

    /**
     * probability of color change for simulation
     */
    private final double p;

    /**
     * state of pole's activity. If false - not changing color and not implies color change for neighbours
     */
    private boolean active;

    /**
     * Creates Pole
     * @param k delay / speed
     * @param p probability for change color
     * @param row row of pole
     * @param column column of pole
     */
    public Pole(int k, double p, int row, int column) {
        MyRandom random = MyRandom.getInstance();
        double v = random.nextDouble() + 0.5;
        delay = (int) (k * v);
        this.row = row;
        this.column = column;
        this.p = p;
        color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        active = true;
    }

    /**
     * Paints pole in panel of simulation
     * @param panel simulaion panel
     * @param graphics graphic context
     */
    public void draw(SimulationPanel panel, Graphics graphics) {
        int width = panel.getWidth();
        int height = panel.getHeight();
        int m = panel.getSimulation().getM(); // columns
        int n = panel.getSimulation().getN(); // rows

        int y = height / n;
        int x = width / m;

        int size = Integer.min(x,y);

        int top = size * row;
        int left = size * column;
        graphics.setColor(color);
        graphics.fillRect(left,top, size, size);
    }

    /**
     * Main method for running in thread
     */
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(delay);
                MyRandom random = MyRandom.getInstance();
                double v = random.nextDouble();
                Simulation simulation = SimulationPanel.getPanel().getSimulation();
                if (simulation==null || !active) continue;
                if (v<=p) {
                    color = simulation.getRandomColor();
                } else {
                    color = simulation.getNewColor(row, column);
                }
                SimulationPanel.getPanel().repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets color of pole
     * @return color of pole
     */
    public Color getColor() {
        return color;
    }

    /**
     * checks if pole is active
     * @return true if pole is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * toggles active state of pole
     */
    public void toggleActive() {
        active = !active;
    }
}
