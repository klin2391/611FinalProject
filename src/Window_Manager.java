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

public class Window_Manager implements ActionListener{
    private JFrame f;
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

    public Window_Manager(Manager m){
        f = new JFrame("Manager");
        this.m = m;
        System.out.println(m.getObs().size());
        System.out.println(this.m.getObs().size());
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

        f.add(l_welcome);
        f.add(b_approve);
        f.add(b_viewUsers);
        f.add(b_addStock);
        f.add(b_removeStock);
        f.add(b_updateStockPrice);
        f.add(b_trackProfit);
        f.add(b_settings);
        f.add(b_logout);

        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_approve) {
            new Window_ManagerApprove(m);
        }
        else if (e.getSource() == b_viewUsers) {
            new Window_ManagerUsers(m);
        }
        else if (e.getSource() == b_addStock) {
        //    System.out.println("Add Stock");
            new Window_ManagerAddStock(m);

        }
        else if (e.getSource() == b_removeStock) {
            System.out.println("Remove Stock from Market");
            new Window_ManagerRemoveStock(m);
        }
        else if (e.getSource() == b_updateStockPrice) {
            System.out.println("Update Stock Price");
            new Window_ManagerUpdateStock(m);
        }
        else if (e.getSource() == b_trackProfit) { // show the table of profit of all users
            new Window_ManagerProfitTable(m);
        }
        else if (e.getSource() == b_settings) {
            new Window_Settings(m);
        }
        else if (e.getSource() == b_logout) {
//            new Window_Root();
            f.dispose();
        }
    }

}
