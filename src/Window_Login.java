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
public class Window_Login implements ActionListener {
    // IF I WANT TO MAKE IT ALL ONE WINDOW, EXTENDS JPANEL
    private JFrame f;                           // Frame
//    private JPanel p;                           // Panel
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
//        p = new JPanel();
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
                if (sql.isSuperAccount(username)){
                    new Window_Super(user);
                    f.dispose();
                }
                else{
                    new Window_User(user, 0);
                    f.dispose();
                }
            }
            else if (sql.verifyManagerAccount(username, password)){
                Manager m = sql.getManager(username);
                new Window_Manager(m);
                f.dispose();
            }
            else{
                JOptionPane.showMessageDialog(f, "Incorrect Username or Password");
            }
        }
        else if(e.getSource() == b_forgot){
//            f.dispose();
            new Window_Forgot();
        }
    }

}
