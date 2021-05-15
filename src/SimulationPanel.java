import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel {

    private static SimulationPanel panel;

    private Simulation simulation;

    public SimulationPanel() {
        panel = this;
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
