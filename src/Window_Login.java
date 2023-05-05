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
public class Window_Login extends JPanel implements ActionListener {
    // IF I WANT TO MAKE IT ALL ONE WINDOW, EXTENDS JPANEL
    private JFrame f;                           // Frame
    private Window w;
    private JLabel l_welcome;
    private JTextField tf_username;
    private JPasswordField pf_password;
    private JButton b_login;
    private JButton b_forgot;
    private JButton b_close;
    private String username;
    private String password;
    private User user;
    private SQL sql;
//    private JPanel p;
//    private Manager m;

    // Constructor
    public Window_Login(Window w){
//        f = new JFrame("Login");
//        p = new JPanel();
        this.w = w;
        l_welcome = new JLabel("Welcome back!");
        tf_username = new JTextField("Username");
        pf_password = new JPasswordField("Password");
        b_login = new JButton("Login");
        b_forgot = new JButton("Forgot Password");
        b_close = new JButton("Close");

        l_welcome.setBounds(50, 50, 200, 30);
        tf_username.setBounds(50, 100, 200, 30);
        pf_password.setBounds(50, 150, 200, 30);
        b_login.setBounds(50, 200, 200, 30);
        b_forgot.setBounds(50, 250, 200, 30);
        b_login.addActionListener(this);
        b_forgot.addActionListener(this);
        b_close.setBounds(50, 300, 200, 30);
        b_close.addActionListener(this);

        this.add(l_welcome);
        this.add(tf_username);
        this.add(pf_password);
        this.add(b_login);
        this.add(b_forgot);
        this.add(b_close);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);

//        f.add(p);
//        f.setSize(500, 500);
//        f.setLayout(null);
//        f.setVisible(true);
        sql = new SQL();
//        this.m = m;
    }

    // Action Listener
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == b_login){   // if the login button is pressed

            username = tf_username.getText();   // get the username and password
            password = pf_password.getText();

            SQL sql = new SQL();
            // query the database and check if the username and password match and exist in the database
            if(sql.verifyCustomerAccount(username, password)){      // VERIFY ACCOUNT
                user = sql.getUser(username);
                user.register(Manager.getInstance());
                Manager.getInstance().register(user);       // REGISTER USER
                if (sql.isSuperAccount(username)){          // IF SUPER ACCOUNT
                    w.update(new Window_Super(user,w));
                    w.setTitle("Super User " +user.getUsername());
                }
                else{                                       // IF NORMAL ACCOUNT
                    w.update(new Window_User(user, 0, w));
                    w.setTitle(user.getUsername());
                }
            }
            else if (sql.verifyManagerAccount(username, password)){     // IF manager
                w.update(new Window_Manager(Manager.getInstance(), w));
                w.setTitle("Manager");
            }
            else{
                JOptionPane.showMessageDialog(w.getFrame(), "Incorrect Username or Password");
            }
        }
        else if(e.getSource() == b_forgot){
            w.update(new Window_Forgot(w));
            w.setTitle("Forgot Password");
        }
        else if(e.getSource() == b_close){
            w.dispose();
        }
    }
}