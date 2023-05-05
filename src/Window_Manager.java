/*
 * Window_Manager.java
 * by Kevin Lin (lin2391@bu.edu)
 * 23APR2023
 *
 * This is a window for manager to do stuff
 */

import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class Window_Manager extends JPanel implements ActionListener{
//    private JFrame f;
    private Window w;
    private Manager m;
    private JLabel l_welcome;
    private JButton b_approve;
    private JButton b_viewUsers;
    private JButton b_addStock;
    private JButton b_removeStock;
    private JButton b_updateStockPrice;
    private JButton b_trackProfit;
    private JButton b_settings;
    private JButton b_logout;

    // Constructor
    public Window_Manager(Manager m, Window w){
//        f = new JFrame("Manager");
        this.w = w;
        this.m = m;
        l_welcome = new JLabel("Welcome, " + m.getUsername() + "!");
        b_approve = new JButton("Approve/Reject");
        b_viewUsers = new JButton("View Users");
        b_addStock = new JButton("Add Stock");
        b_removeStock = new JButton("Remove Stock");
        b_updateStockPrice = new JButton("Update Stock Price");
        b_trackProfit = new JButton("Track Profit");
        b_settings = new JButton("Settings");
        b_logout = new JButton("Logout");

        l_welcome.setBounds(50, 0, 200, 30);
        b_approve.setBounds(50, 50, 200, 30);
        b_viewUsers.setBounds(50, 100, 200, 30);
        b_addStock.setBounds(50, 150, 200, 30);
        b_removeStock.setBounds(50, 200, 200, 30);
        b_updateStockPrice.setBounds(50, 250, 200, 30);
        b_trackProfit.setBounds(50, 300, 200, 30);
        b_settings.setBounds(50, 350, 200, 30);
        b_logout.setBounds(50, 400, 200, 30);

        b_approve.addActionListener(this);
        b_viewUsers.addActionListener(this);
        b_addStock.addActionListener(this);
        b_removeStock.addActionListener(this);
        b_updateStockPrice.addActionListener(this);
        b_trackProfit.addActionListener(this);
        b_settings.addActionListener(this);
        b_logout.addActionListener(this);

        this.add(l_welcome);
        this.add(b_approve);
        this.add(b_viewUsers);
        this.add(b_addStock);
        this.add(b_removeStock);
        this.add(b_updateStockPrice);
        this.add(b_trackProfit);
        this.add(b_settings);
        this.add(b_logout);

        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_approve) {
            w.update(new Window_ManagerApprove(w));
            w.setTitle("Approve/Reject Users");
        }
        else if (e.getSource() == b_viewUsers) {
            w.update(new Window_ManagerUsers(w));
            w.setTitle("View Users");
        }
        else if (e.getSource() == b_addStock) {
            w.update(new Window_ManagerAddStock(w));
            w.setTitle("Add Stock");
        }
        else if (e.getSource() == b_removeStock) {
            w.update(new Window_ManagerRemoveStock(w));
            w.setTitle("Remove Stock");
        }
        else if (e.getSource() == b_updateStockPrice) {
            w.update(new Window_ManagerUpdateStock(w));
            w.setTitle("Update Stock Price");
        }
        else if (e.getSource() == b_trackProfit) { // show the table of profit of all users
            new Window_ManagerProfitTable();
        }
        else if (e.getSource() == b_settings) {
            new Window_Settings(m);
        }
        else if (e.getSource() == b_logout) {
            w.dispose();
        }
    }
}
