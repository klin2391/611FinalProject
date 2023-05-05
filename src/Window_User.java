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
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Window_User extends JPanel implements ActionListener, Observer_User{
//    private JFrame f;                           // Frame
    private Window w;
    private User user;                          // User profile
    private JLabel l_nameFirst;
    private JLabel l_nameLast;
    private JLabel l_cashBuyPower;
    private JLabel l_accountValue;
    private JLabel l_profit;
    private JLabel l_viewStocks;
    private JComboBox <String> cb_stocksOwned;          //Combo box allows drop down
    private JButton b_depositWithdraw;
    private JButton b_buySell;
    private JButton b_settings;
    private JButton b_logout;
    private JPanel p_north;
    private JPanel p_center;
    private JPanel p_south;


    // Constructor that takes a user
    public Window_User(User u, int caller, Window w){         // Caller is 0 if called from login, 1 if called from manager
        this.user = u;                                      // Sets user
        register(user);
//        f = new JFrame(u.getUsername());
        this.w = w;
        l_nameFirst = new JLabel("First Name: " + u.getFirstName());
        l_nameLast = new JLabel("Last Name: " + u.getLastName());
        l_cashBuyPower = new JLabel("Cash Buying Power: " + u.getBalance());
        l_accountValue = new JLabel("Account Value: " + u.getTotalValue());
        l_profit = new JLabel("Profit: " + u.getProfit());
        l_viewStocks = new JLabel("View Stocks Below");
        b_depositWithdraw = new JButton("Deposit/Withdraw Funds");
        b_buySell = new JButton("Buy/Sell Stocks");
        b_settings = new JButton("Settings");
        if (caller == 0) {
            b_logout = new JButton("Logout");
        } else if (caller == 1) {
            b_logout = new JButton("Cancel");
        }


        JPanel p_stocks = new JPanel(new GridLayout(2, 1));
        cb_stocksOwned = new JComboBox<String>();
        for (String s : u.getPortfolio().keySet()) {
            cb_stocksOwned.addItem(s);
        }

        cb_stocksOwned.addActionListener(this);
        b_depositWithdraw.addActionListener(this);
        b_buySell.addActionListener(this);
        b_settings.addActionListener(this);
        b_logout.addActionListener(this);
        b_depositWithdraw.setPreferredSize(new Dimension(200, 50));
        b_buySell.setPreferredSize(new Dimension(150, 50));
        b_settings.setPreferredSize(new Dimension(100, 50));
        b_logout.setPreferredSize(new Dimension(100, 50));

        Font font = new Font("Monospaced", Font.BOLD, 18);

        l_nameFirst.setHorizontalAlignment(JLabel.CENTER);
        l_nameFirst.setFont(font);
        l_nameLast.setHorizontalAlignment(JLabel.CENTER);
        l_nameLast.setFont(font);
        l_cashBuyPower.setHorizontalAlignment(JLabel.CENTER);
        l_cashBuyPower.setFont(font);
        l_accountValue.setHorizontalAlignment(JLabel.CENTER);
        l_accountValue.setFont(font);
        l_profit.setHorizontalAlignment(JLabel.CENTER);
        l_profit.setFont(font);
        l_viewStocks.setHorizontalAlignment(JLabel.CENTER);
        l_viewStocks.setFont(font);

        // Create GridLayout with 3 rows and 2 columns for p_center panel
        p_center = new JPanel(new GridLayout(3, 2, 5, 5));
        p_center.add(l_cashBuyPower);
        p_center.add(l_viewStocks);

        p_center.add(l_accountValue);
        JPanel p_combo = new JPanel();
        p_center.add(p_combo);
        cb_stocksOwned.setPreferredSize(new Dimension(200, 50));
        p_combo.add(cb_stocksOwned);

        cb_stocksOwned.setSize(1, 1);
        p_center.add(l_profit);
        p_center.add(new JLabel()); // Empty label for formatting
        f.setLayout(new BorderLayout());
        p_north = new JPanel(new GridLayout(2, 1));
        p_north.add(l_nameFirst);
        p_north.add(l_nameLast);
        p_south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        if (caller == 0) {
            p_south.add(b_depositWithdraw);
            p_south.add(b_buySell);
            p_south.add(b_settings);
            p_south.add(b_logout);
        } else if (caller == 1) {
            p_south.add(b_logout);
        }

        System.out.println(caller);
        this.add(p_north, BorderLayout.NORTH);
        this.add(p_center, BorderLayout.CENTER);
        this.add(p_south, BorderLayout.SOUTH);
        this.setSize(600, 600);
        this.setVisible(true);
    }

    // Implements action performed interface for user interaction
    public void actionPerformed(ActionEvent e){
        SQL sql = new SQL();
        if (e.getSource() == b_depositWithdraw){     // If button to deposit is clicked
            if (sql.checkIfUserBlacklisted(user.getUsername())){
                JOptionPane.showMessageDialog(w.getFrame(), "You are blacklisted from the system. Please contact an administrator.");
                return;
            }
            w.update(new Window_Funds(user, this,w));
            w.setTitle("Deposit/Withdraw Funds");
            return;
        }
        else if (e.getSource() == b_buySell){            // If button to buy is clicked
            if (sql.checkIfUserBlacklisted(user.getUsername())){
                JOptionPane.showMessageDialog(w.getFrame(), "You are blacklisted from the system. Please contact an administrator.");
                return;
            }
            if (!sql.checkIfStocks()){
                JOptionPane.showMessageDialog(w.getFrame(), "There are no stocks in the system. Please contact an administrator.");
                return;
            }
            w.update(new Window_Trade(user.getPortfolio(), sql.getAllAvailableStocks(), user, w));
            w.setTitle("Trade Stocks");
            return;
        }
        else if (e.getSource() == b_settings){           // If button to settings is clicked
            new Window_Settings(user);
            return;
        }
        else if (e.getSource() == b_logout){             // If button to logout is clicked
            w.dispose();
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
        user = u;
        l_nameFirst.setText("First Name: " + u.getFirstName());
        l_nameLast.setText("Last Name: " + u.getLastName());
        l_cashBuyPower.setText("Cash Buying Power: " + u.getBalance());
        l_accountValue.setText("Account Value: " + u.getTotalValue());
        l_profit.setText("Profit: " + u.getProfit());
        if (u.getPortfolio().size() > cb_stocksOwned.getItemCount() - 1){       // If new stock is added
            cb_stocksOwned.addItem((String) u.getPortfolio().keySet().toArray()[0]);
        }
        if (u.getPortfolio().size() < cb_stocksOwned.getItemCount() - 1){       // If stock is removed
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
