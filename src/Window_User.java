/*
 * Window_User.java
 * by Kevin Lin (lin2391@bu.edu)
 * 17APR2023
 * 
 * This class is a tentative draft at the user page.
 * Has functionality of depositing/withdrawing funds,
 * as well as buy/sell stocks (not implemented yet)
 * 
 * TODO Remove window from user when closed
 */

import javax.swing.*;  
import java.util.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

public class Window_User implements ActionListener, Observer_User{
    private JFrame f;                           // Frame
    private User user;                          // User profile
    private JLabel l_nameFirst;
    private JLabel l_nameLast;
    private JLabel l_cashBuyPower;
    private JLabel l_accountValue;
    private JLabel l_profit;
    private JComboBox <String> cb_stocksOwned;          //Combo box allows drop down
    private JButton b_depositWithdraw;
    private JButton b_buySell;
    private JButton b_settings;
    private JButton b_logout;
    private JPanel p_north;
    private JPanel p_center;
    private JPanel p_south;


    // Constructor that takes a user
    public Window_User(User u, int caller){         // Caller is 0 if called from login, 1 if called from manager
        this.user = u;                                      // Sets user
        register(user);
        f = new JFrame(u.getUsername());
        l_nameFirst = new JLabel("First Name: " + u.getFirstName());
        l_nameLast = new JLabel("Last Name: " + u.getLastName());
        l_cashBuyPower = new JLabel("Cash Buying Power: " + u.getBalance());
        l_accountValue = new JLabel("Account Value: " + u.getTotalValue());
        l_profit = new JLabel("Profit: " + u.getProfit());
        b_depositWithdraw= new JButton("Deposit/Withdraw Funds");
        b_buySell = new JButton("Buy/Sell Stocks");
        b_settings = new JButton("Settings");
        if (caller == 0){                                   // If called from login
            b_logout = new JButton("Logout");
        }
        else if (caller == 1){                              // If called from settings
            b_logout = new JButton("Cancel");
        }
//        b_logout = new JButton("Logout");

        cb_stocksOwned = new JComboBox <String>();
        cb_stocksOwned.addItem("Select a Stock to View");
        for (String s : u.getPortfolio().keySet()){         //Adds owned stocks to list
            cb_stocksOwned.addItem(s);
        }
        cb_stocksOwned.addActionListener(this);
        b_depositWithdraw.addActionListener(this);
        b_buySell.addActionListener(this);
        b_settings.addActionListener(this);
        b_logout.addActionListener(this);
        l_nameFirst.setBounds(50, 50, 200, 30);
        l_nameLast.setBounds(50, 100, 200, 30);
        l_cashBuyPower.setBounds(50, 150, 200, 30);
        l_accountValue.setBounds(50, 250, 200, 30);
        l_profit.setBounds(50, 300, 200, 30);
        cb_stocksOwned.setBounds(50, 200, 200, 30);
        b_depositWithdraw.setBounds(50, 300, 200, 30);
        b_buySell.setBounds(50, 350, 200, 30);
        b_settings.setBounds(50, 450, 200, 30);
        b_logout.setBounds(50, 400, 200, 30);
        f.setLayout(new BorderLayout());
        p_north = new JPanel();
        p_north.add(l_nameFirst);
        p_north.add(l_nameLast);
        p_center = new JPanel();
        p_center.add(l_cashBuyPower);
        p_center.add(l_accountValue);
        p_center.add(l_profit);
        p_center.add(cb_stocksOwned);
        p_south = new JPanel(new FlowLayout());
        if (caller == 0){                                   // If called from login
            p_south.add(b_depositWithdraw);
            p_south.add(b_buySell);
            p_south.add(b_settings);
            p_south.add(b_logout);
        }
        else if (caller == 1){                              // If called from settings
            p_south.add(b_logout);
        }



        // f.add(l_nameFirst);
        // f.add(l_nameLast);
        f.add(p_north, BorderLayout.NORTH);
        f.add(p_center, BorderLayout.CENTER);
        f.add(p_south, BorderLayout.SOUTH);
        f.setSize(800, 800);
        
        f.setVisible(true);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Implements action performed interface for user interaction
    public void actionPerformed(ActionEvent e){
        SQL sql = new SQL();
        if (e.getSource() == b_depositWithdraw){     // If button to deposit is clicked
            if (sql.checkIfUserBlacklisted(user.getUsername())){
                JOptionPane.showMessageDialog(null, "You are blacklisted from the system. Please contact an administrator.");
                return;
            }
            Window_Funds wf = new Window_Funds(user, this);
            return;
        }
        else if (e.getSource() == b_buySell){            // If button to buy is clicked
            System.out.println("Buy/Sell Stocks");
            if (sql.checkIfUserBlacklisted(user.getUsername())){
                JOptionPane.showMessageDialog(null, "You are blacklisted from the system. Please contact an administrator.");
                return;
            }
            Window_Trade wbs = new Window_Trade(user.getPortfolio(), Market.getStocks(), user);
            return;
        }
        else if (e.getSource() == b_settings){           // If button to settings is clicked
            System.out.println("Settings");
            Window_Settings ws = new Window_Settings(user);
            return;
        }
        else if (e.getSource() == b_logout){
            System.out.println("Logout");
            f.dispose();
            return;
        }


        JComboBox cb = (JComboBox)e.getSource();                                // If dropdown is changed
        String symbol = (String)cb.getSelectedItem();
        if (symbol.equals("Select a Stock to View"))                            // Do nothing
            return;
        Window_Stock ws = new Window_Stock(user.getStock(symbol),true);
    }

    // Update values on frame based on new user
    public void update(User u){
        l_nameFirst.setText("First Name: " + u.getFirstName());
        l_nameLast.setText("Last Name: " + u.getLastName());
        l_cashBuyPower.setText("Cash Buying Power: " + u.getBalance());
        l_accountValue.setText("Account Value: " + u.getTotalValue());
        
        // Only add to combo box if new stock is added
        if (u.getPortfolio().size() > cb_stocksOwned.getItemCount() - 1){
            cb_stocksOwned.addItem((String) u.getPortfolio().keySet().toArray()[u.getPortfolio().size() - 1]);
        }
        if (u.getPortfolio().size() < cb_stocksOwned.getItemCount() - 1){
            if (u.getPortfolio().size() == 0) {
                for (int i = 0; i < cb_stocksOwned.getItemCount() ; i++){
                    if (cb_stocksOwned.getItemAt(i) != "Select a Stock to View") {
                        cb_stocksOwned.removeItemAt(i);
                    }
                        
                }
            }
            else {
                for (int i = 0; i < cb_stocksOwned.getItemCount(); i++){
                    if (cb_stocksOwned.getItemAt(i) != "Select a Stock to View") {
                        if (!u.getPortfolio().containsKey(cb_stocksOwned.getItemAt(i))){
                            cb_stocksOwned.removeItemAt(i);
                        }
                    }
                }
            }
        }

    }
    public void register(User u){
        u.addWindow(this);
    }
}
