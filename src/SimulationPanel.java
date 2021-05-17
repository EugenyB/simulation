import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimulationPanel extends JPanel {

    private static SimulationPanel panel;

    private Simulation simulation;

    public SimulationPanel() {
        panel = this;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int width = getWidth();
                int height = getHeight();
                int m = simulation.getM(); // columns
                int n = simulation.getN(); // rows

                int size = Integer.min(width / m,height / n);

                int row = y / size;
                int column = x / size;
                Pole[][] poles = simulation.getPoles();
                if (row < poles.length && row >=0 && column < poles[0].length && column >= 0) {
                    simulation.toggleActive(row,column);
                }
            }
        });
    }

    public static SimulationPanel getPanel() {
        return panel;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (simulation != null) {
            simulation.repaint(this, g);
        }
    }
}
