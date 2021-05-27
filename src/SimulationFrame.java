import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends javax.swing.JFrame {

    /**
     * Creates new form SimulationFrame
     */
    public SimulationFrame() {
        initComponents();
    }

    /**
     * Creates content for SimulationFrame
     */
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jTextField1.setColumns(5);
        jPanel1.add(jTextField1);

        jTextField2.setColumns(5);
        jPanel1.add(jTextField2);

        jTextField3.setColumns(5);
        jPanel1.add(jTextField3);

        jTextField4.setColumns(5);
        jPanel1.add(jTextField4);


        jButton1.setText("Start");
        jButton1.addActionListener(evt -> startSimulation());
        jPanel1.add(jButton1);

        getContentPane().add(jPanel1, BorderLayout.NORTH);

        simulationPanel = new SimulationPanel();
        getContentPane().add(simulationPanel);

        pack();
    }

    /**
     * Starts simulation according to parameters.
     * If one or more parameters is incorrect - shows message about it
     */
    private void startSimulation() {
        try {
            int n = Integer.parseInt(jTextField1.getText());
            if (n <= 0 || n > 100) {
                throw new IllegalArgumentException("Number of rows must be in [1;100]");
            }
            int m = Integer.parseInt(jTextField2.getText());
            if (m <= 0 || m > 100) {
                throw new IllegalArgumentException("Number of columns must be in [1;100]");
            }
            int k = Integer.parseInt(jTextField3.getText());
            if (k <= 100) {
                throw new IllegalArgumentException("Delay must be more than 100ms");
            }
            double p = Double.parseDouble(jTextField4.getText());
            if (p<=0 || p>=1) throw new IllegalArgumentException("Probability must be in (0;1)");
            simulationPanel.setSimulation(createSimulation(n, m, k, p));
            jButton1.setEnabled(false);
            repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Incorrect parameters");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    /**
     * Creates simulation with set of parameters
     * @param n number of rows
     * @param m number of columns
     * @param k simulation delay (speed)
     * @param p simulaton probability
     * @return created Simulation
     */
    private Simulation createSimulation(int n, int m, int k, double p) {
        return new Simulation(n, m, k, p);
    }

    /**
     * Main method for start program
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new SimulationFrame().setVisible(true));
    }

    /**
     * Panel for simulation
     */
    private SimulationPanel simulationPanel;

    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;

}