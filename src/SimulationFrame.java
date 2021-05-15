import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class SimulationFrame extends javax.swing.JFrame {

    /**
     * Creates new form SimulationFrame
     */
    public SimulationFrame() {
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();

        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

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

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }

    private void startSimulation() {
        try {
            int n = Integer.parseInt(jTextField1.getText());
            int m = Integer.parseInt(jTextField2.getText());
            int k = Integer.parseInt(jTextField3.getText());
            double p = Double.parseDouble(jTextField4.getText());
            if (p<=0 || p>=1) throw new IllegalArgumentException();
            simulationPanel.setSimulation(createSimulation(n, m, k, p));
            jButton1.setEnabled(false);
            repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Incorrect parameters");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Probability must be in (0;1)");
        }
    }

    private Simulation createSimulation(int n, int m, int k, double p) {
        return new Simulation(n, m, k, p);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new SimulationFrame().setVisible(true));
    }

    private SimulationPanel simulationPanel;

    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;

}