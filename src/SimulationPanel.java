import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel for simulation
 */
public class SimulationPanel extends JPanel {

    /**
     * private field for access from static getter
     */
    private static SimulationPanel panel;

    /**
     * Reference to Simulation object
     */
    private Simulation simulation;

    /**
     * Creates panel for simulation
     */
    public SimulationPanel() {
        panel = this;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                processMouseClick(e);
            }
        });
    }

    /**
     * Process mouse click - finds row & column of pole for toggle activity
     * and causes simulation for toggle activity of found pole
     * @param e - mouse click parameter
     */
    private void processMouseClick(MouseEvent e) {
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

    /**
     * Method for access Simulation panel from other parts of program
     * @return object of simulation panel, stored in static field
     */
    public static SimulationPanel getPanel() {
        return panel;
    }

    /**
     * Overridden method for preferred size of panel
     * @return start (preferred) size of panel
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    /**
     * Sets simulation object
     * @param simulation new simulation object
     */
    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Gets stored simulation object
     * @return
     */
    public Simulation getSimulation() {
        return simulation;
    }

    /**
     * Repaints panel
     * @param g graphic context
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (simulation != null) {
            simulation.repaint(this, g);
        }
    }
}
