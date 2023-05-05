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

public class Window_Funds extends JPanel{
//    private JFrame f;
    Window w;
    private int amount;
    private User user;
    private ArrayList <Window_User> windows;    // Windows to update
    private JLabel l_prompt;
    private JTextField tf_amount;
    private JButton b_deposit;
    private JButton b_withdraw;
    private JButton b_cancel;

    // Default Constructor
    public Window_Funds(User u, Window_User wu, Window w) {
        this.user = u;
        windows = new ArrayList <Window_User>();
        windows.add(wu);
//        f = new JFrame("Deposit/Withdraw Funds");
        this.w = w;
        l_prompt = new JLabel("Enter Amount: ");
        tf_amount= new JTextField();
        b_deposit= new JButton("Deposit");
        b_withdraw = new JButton("Withdraw");
        b_cancel = new JButton("Cancel");
        l_prompt.setBounds(50, 50, 200, 30);
        tf_amount.setBounds(50, 100, 200, 30);
        b_deposit.setBounds(50, 150, 200, 30);
        b_withdraw.setBounds(50, 200, 200, 30);
        b_cancel.setBounds(50, 250, 200, 30);
        this.add(l_prompt);
        this.add(tf_amount);
        this.add(b_deposit);
        this.add(b_withdraw);
        this.add(b_cancel);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        b_deposit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (tf_amount.getText().isEmpty()){
                    ;
                }
                else{
                    amount = Integer.parseInt(tf_amount.getText());
                    u.addBalance(amount);
                    updateWindows(u);
                    w.update(new Window_User(u, 0, w));
                    w.setTitle(u.getUsername());
                }
            }
        });
        b_withdraw.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (tf_amount.getText().isEmpty()){
                    ;
                }
                else{
                    amount = Integer.parseInt(tf_amount.getText());
                    if (u.getBalance() < amount){
//                        Window_Alert we = new Window_Alert("Insufficient Funds!", false);
                        JOptionPane.showMessageDialog(w.getFrame(), "Insufficient Funds!");
                    }
                    else{
                        u.subtractBalance(amount);
                        updateWindows(u);
                        w.update(new Window_User(u, 0, w));
                        w.setTitle(u.getUsername());
                    }
                }
            }
        });
        b_cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                w.update(new Window_User(user, 0, w));
                w.setTitle(user.getUsername());
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
