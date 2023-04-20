/*
 * Window_User.java
 * by Kevin Lin (lin2391@bu.edu)
 * 17APR2023
 * 
 * This class is a tentative draft at the user page.
 * Has functionality of depositing/withdrawing funds,
 * as well as buy/sell stocks (not implemented yet)
 */

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

public class Window_User implements ActionListener{
    private JFrame f;                           // Frame
    private User user;                          // User profile
    JLabel l_nameFirst;
    JLabel l_nameLast;
    JLabel l_cashBuyPower;
    JLabel l_accountValue;
    JComboBox <String> cb_stocksOwned;          //Combo box allows drop down
    JButton b_DepositWithdraw;
    JButton b_BuySell;
    JButton b_Logout;
    JPanel p_North;
    JPanel p_Center;
    JPanel p_South;


    // Constructor that takes a user
    public Window_User(User u){
        this.user = u;                                      // Sets user
        f = new JFrame(u.getUsername());
        l_nameFirst = new JLabel("First Name: " + u.getFirstName());
        l_nameLast = new JLabel("Last Name: " + u.getLastName());
        l_cashBuyPower = new JLabel("Cash Buying Power: " + u.getBalance());
        l_accountValue = new JLabel("Account Value: " + u.getTotalValue());
        b_DepositWithdraw= new JButton("Deposit/Withdraw Funds");
        b_BuySell = new JButton("Buy/Sell Stocks");
        b_Logout = new JButton("Logout");
        cb_stocksOwned = new JComboBox <String>();
        cb_stocksOwned.addItem("Select a Stock to View");
        for (String s : u.getPortfolio().keySet()){         //Adds owned stocks to list
            cb_stocksOwned.addItem(s);
        }
        cb_stocksOwned.addActionListener(this);
        b_DepositWithdraw.addActionListener(this);
        l_nameFirst.setBounds(50, 50, 200, 30);
        l_nameLast.setBounds(50, 100, 200, 30);
        l_cashBuyPower.setBounds(50, 150, 200, 30);
        l_accountValue.setBounds(50, 250, 200, 30);
        cb_stocksOwned.setBounds(50, 200, 200, 30);
        b_DepositWithdraw.setBounds(50, 300, 200, 30);
        b_BuySell.setBounds(50, 350, 200, 30);
        b_Logout.setBounds(50, 400, 200, 30);
        f.setLayout(new BorderLayout());
        p_North = new JPanel();
        p_North.add(l_nameFirst);
        p_North.add(l_nameLast);
        p_Center = new JPanel();
        p_Center.add(l_cashBuyPower);
        p_Center.add(l_accountValue);
        p_Center.add(cb_stocksOwned);
        p_South = new JPanel(new FlowLayout());
        p_South.add(b_DepositWithdraw);
        p_South.add(b_BuySell);
        p_South.add(b_Logout);

        // f.add(l_nameFirst);
        // f.add(l_nameLast);
        f.add(p_North, BorderLayout.NORTH);
        f.add(p_Center, BorderLayout.CENTER);
        f.add(p_South, BorderLayout.SOUTH);
        f.setSize(800, 800);
        
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Implements action performed interface for user interaction
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("Deposit/Withdraw Funds")){     // If button to deposit is clicked
            Window_Funds wf = new Window_Funds(user, this);
            return;
        }

        JComboBox cb = (JComboBox)e.getSource();                                // If dropdown is changed
        String symbol = (String)cb.getSelectedItem();
        if (symbol.equals("Select a Stock to View"))                            // Do nothing
            return;
        Window_Stock ws = new Window_Stock(user.getStock(symbol));
    }

    // Update values on frame based on new user
    public void update(User u){
        l_nameFirst.setText("First Name: " + u.getFirstName());
        l_nameLast.setText("Last Name: " + u.getLastName());
        l_cashBuyPower.setText("Cash Buying Power: " + u.getBalance());
        l_accountValue.setText("Account Value: " + u.getTotalValue());
    }
    
}
