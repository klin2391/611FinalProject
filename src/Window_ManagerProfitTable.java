/*
 * Window_ManagerProfitTable.java
 * by Ryan
 * 23APR2023
 *
 * This is a window to display profit of all users (real and unrealized)
 */

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.*;

public class Window_ManagerProfitTable implements Observer_Manager{// JTable of profit of all users

    private JFrame f;       // frame
    private JPanel p;
    private JTable j;       // Table

    // Constructor
    Window_ManagerProfitTable() {
        f = new JFrame();       // Frame initialization
        register(Manager.getInstance());            // Register this window to manager
        f.setTitle("Profit Report");    // Frame Title
        String[][] data = Manager.getInstance().trackProfit();  // Data to be displayed in the JTable
        String[] columnNames = {"User Name", "Realized Profit", "Unrealized Profit"};   // Column Names
        p = new JPanel();
        j = new JTable(data, columnNames);      // Initializing the JTable
        j.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(j);    // adding it to JScrollPane
        p.add(sp);
        f.add(p);
        f.setSize(500, 200);    // Frame Size
        f.setVisible(true);    // Frame Visibility
    }

    // Register this window to manager
    public void register(Manager m){
        m.registerWindow(this);
    }

    // Update the JTable
    public void update(Manager m){
        String[][] data = m.trackProfit();
        String[] columnNames = {"User Name", "Realized Profit", "Unrealized Profit"};
        JTable j1 = new JTable(data, columnNames);
        j1.setBounds(30, 40, 200, 300);
        JScrollPane sp1 = new JScrollPane(j1);
        p.setVisible(false);
        f.remove(p);
        p = new JPanel();
        p.add(sp1);
        f.add(p);
        p.setVisible(true);
    }
}
