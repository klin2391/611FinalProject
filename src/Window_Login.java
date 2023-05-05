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
    private JLabel l_welcome;
    private JTextField tf_username;
    private JPasswordField pf_password;
    private JButton b_login;
    private JButton b_forgot;
    private JButton b_back;
    private String username;
    private String password;
    private User user;
    private SQL sql;
    private JPanel p;

    // Constructor
    public Window_Login(){
        f = new JFrame("Login");
        p = new JPanel();
        l_welcome = new JLabel("Welcome Back!");
        tf_username = new JTextField("Username");
        pf_password = new JPasswordField("Password");
        b_login = new JButton("Login");
        b_forgot = new JButton("Forgot Password");
        b_back = new JButton("Back");

        l_welcome.setBounds(50, 50, 200, 30);
        tf_username.setBounds(50, 100, 200, 30);
        pf_password.setBounds(50, 150, 200, 30);
        b_login.setBounds(50, 200, 200, 30);
        b_forgot.setBounds(50, 250, 200, 30);
        b_login.addActionListener(this);
        b_forgot.addActionListener(this);
        b_back.setBounds(50, 300, 200, 30);
        b_back.addActionListener(this);
        p.add(l_welcome);
        p.add(tf_username);
        p.add(pf_password);
        p.add(b_login);
        p.add(b_forgot);
        p.add(b_back);
        p.setSize(600, 600);
        p.setLayout(null);
        p.setVisible(true);
        f.add(p);
        f.setSize(600, 600);
        f.setLayout(null);
        f.setVisible(true);
        sql = new SQL();
    }

    // Action Listener
    public void actionPerformed(ActionEvent e){
        // if the login button is pressed
        if(e.getSource() == b_login){
            // get the username and password
            username = tf_username.getText();
            password = pf_password.getText();
            // VERIFY ACCOUNT
            SQL sql = new SQL();
            // query the database and check if the username and password match and exist in the database
            if(sql.verifyCustomerAccount(username, password)){
                user = sql.getUser(username);
                if (sql.isSuperAccount(username)){          // IF SUPER ACCOUNT
                    new Window_Super(user);
                    f.dispose();
                }
                else{                                       // IF NORMAL ACCOUNT
                    new Window_User(user, 0);
                    f.dispose();
                }
            }
            else if (sql.verifyManagerAccount(username, password)){     // IF manager
                Manager m = sql.getManager(username);
                new Window_Manager(m);
                f.dispose();
            }
            else{
                JOptionPane.showMessageDialog(f, "Incorrect Username or Password");
            }
        }
        else if(e.getSource() == b_forgot){
            p.setVisible(false);
            f.remove(p);
            p = new Window_Forgot();
            f.add(p);
        }
        else if(e.getSource() == b_back){
            new Window_Root();
            f.dispose();
        }
    }

}
