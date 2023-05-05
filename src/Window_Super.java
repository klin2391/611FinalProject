/*
 * Window_Super.java
 * by Kevin Lin (lin2391@bu.edu)
 * 23APR2023
 *
 * This is a window for supers to see after login
 */

import java.awt.event.*;
import javax.swing.*;

public class Window_Super extends JPanel implements ActionListener{
//    private JFrame f;
    private Window w;
    private JLabel l_welcome;
    private JButton b_stocks;
    private JButton b_options;
    private JButton b_logout;
    private User user;
    private Manager m;

    // Constructor
    public Window_Super(User user, Window w){
        this.user = user;
        this.m = m;
//        f = new JFrame("Super User" + user.getUsername());
        this.w = w;
        l_welcome = new JLabel("Welcome " + user.getUsername() + "!");
        b_stocks = new JButton("Stocks");
        b_options = new JButton("Options");
        b_logout = new JButton("Logout");

        l_welcome.setBounds(50, 50, 200, 30);
        b_stocks.setBounds(50, 100, 200, 30);
        b_options.setBounds(50, 150, 200, 30);
        b_logout.setBounds(50, 200, 200, 30);

        b_stocks.addActionListener(this);
        b_options.addActionListener(this);
        b_logout.addActionListener(this);

        this.add(l_welcome);
        this.add(b_stocks);
        this.add(b_options);
        this.add(b_logout);

        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
    }

    // Action Listener
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b_stocks){
            w.update(new Window_User(user, 0, w));
            w.setTitle(user.getUsername());
        }
        else if(e.getSource() == b_options){
            JOptionPane.showMessageDialog(w.getFrame(), "Option Trading has not yet been implemented");
//            f.dispose();
        }
        else if(e.getSource() == b_logout){
            w.dispose();
        }
    }
}
