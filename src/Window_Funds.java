/*
 * Window_Fund.java
 * by Kevin Lin (lin2391@bu.edu)
 * 17APR2023
 * 
 * This is a window that opens when user
 * wants to deposit or withdraw funds to account.
 * Assume this is allowed and account number is approved
 */

import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class Window_Funds{
    private JFrame f;
    private int amount;
    private User user;
    private ArrayList <Window_User> windows;    // Windows to update
    private JLabel l_prompt;
    private JTextField tf_amount;
    private JButton b_Deposit;
    private JButton b_Withdraw;
    private JButton b_Cancel;

    // Default Constructor
    public Window_Funds(User u, Window_User wu) {
        this.user = u;
        windows = new ArrayList <Window_User>();
        windows.add(wu);
        f = new JFrame("Deposit/Withdraw Funds");
        l_prompt = new JLabel("Enter Amount: ");
        tf_amount= new JTextField();
        b_Deposit= new JButton("Deposit");
        b_Withdraw = new JButton("Withdraw");
        b_Cancel = new JButton("Cancel");
        l_prompt.setBounds(50, 50, 200, 30);
        tf_amount.setBounds(50, 100, 200, 30);
        b_Deposit.setBounds(50, 150, 200, 30);
        b_Withdraw.setBounds(50, 200, 200, 30);
        b_Cancel.setBounds(50, 250, 200, 30);
        f.add(l_prompt);
        f.add(tf_amount);
        f.add(b_Deposit);
        f.add(b_Withdraw);
        f.add(b_Cancel);
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
        b_Deposit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                amount = Integer.parseInt(tf_amount.getText());
                u.addBalance(amount);
                updateWindows(u);
                f.dispose();
            }
        });
        b_Withdraw.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                amount = Integer.parseInt(tf_amount.getText());
                if (u.getBalance() < amount){
                    Window_Error we = new Window_Error("Insufficient Funds!");
                }
                else{
                    u.subtractBalance(amount);
                    updateWindows(u);
                    f.dispose();
                }
                
            }
        });
        b_Cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
            }
        });
    }

    // Registers a Window to be a listener
    public void addWindow(Window_User w){
        windows.add(w);
    }

    // Updates all listener Windows
    public void updateWindows(User u){
        for (Window_User w : windows){
            w.update(u);
        }
    }
}
