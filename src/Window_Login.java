/*
 * Window_Login.java
 * by Kevin Lin (lin2391@bu.edu)
 * 21APR2023
 *
 * This is a window to login to existing account
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Window_Login implements ActionListener{
    private JFrame f;                           // Frame
    private JLabel l_welcome;
    private JTextField tf_username;
    private JPasswordField pf_password;
    private JButton b_login;
    private JButton b_forgot;

    private String username;
    private String password;
    private User user;

    // Constructor
    public Window_Login(){
        f = new JFrame("Login");
        l_welcome = new JLabel("Welcome Back!");
        tf_username = new JTextField("Username");
        pf_password = new JPasswordField("Password");
        b_login = new JButton("Login");
        b_forgot = new JButton("Forgot Password");

        l_welcome.setBounds(50, 50, 200, 30);
        tf_username.setBounds(50, 100, 200, 30);
        pf_password.setBounds(50, 150, 200, 30);
        b_login.setBounds(50, 200, 200, 30);
        b_forgot.setBounds(50, 250, 200, 30);
        b_login.addActionListener(this);
        b_forgot.addActionListener(this);
        f.add(l_welcome);
        f.add(tf_username);
        f.add(pf_password);
        f.add(b_login);
        f.add(b_forgot);
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
    }

    // Action Listener

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b_login){
            username = tf_username.getText();
            password = pf_password.getText();
            // VERIFY ACCOUNT
            if(true /* TODO if account exists */){
                // TODO recover user
                new Window_User(user);
                f.dispose();
            }
            else{
                JOptionPane.showMessageDialog(f, "Incorrect Username or Password");
            }
        }
        else if(e.getSource() == b_forgot){
            f.dispose();
            new Window_Forgot();
        }
    }

}
