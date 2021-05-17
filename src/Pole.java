import java.awt.*;

public class Pole implements Runnable {
    private Color color;
    private final int delay;
    private final int row;
    private final int column;
    private final double p;
    private boolean active;

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
                    // todo change color with neighbours
                    color = simulation.getNewColor(row, column);
                }
                SimulationPanel.getPanel().repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Color getColor() {
        return color;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void toggleActive() {
        active = !active;
    }
}
