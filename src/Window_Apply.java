/*
 * Window_Apply.java
 * by Kevin Lin (lin2391@bu.edu)
 * 21APR2023
 *
 * This is a window to apply to create an account
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window_Apply implements ActionListener {
    private JFrame f;                           // Frame
    private JLabel l_applyNow;
    private JTextField tf_firstName;
    private JTextField tf_lastName;
    private JTextField tf_email;
    private JTextField tf_username;
    private JPasswordField pf_password;
    private JPasswordField pf_confirmPassword;
    private JButton b_apply;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;


    // Constructor
    public Window_Apply() {
        f = new JFrame("Apply Now");
        l_applyNow = new JLabel("Apply Now");
        tf_firstName = new JTextField("First Name");
        tf_lastName = new JTextField("Last Name");
        tf_email = new JTextField("Email");
        tf_username = new JTextField("Username");
        pf_password = new JPasswordField("Password");
        pf_confirmPassword = new JPasswordField("Password");
        b_apply = new JButton("Apply");

        l_applyNow.setBounds(50, 50, 200, 30);
        tf_firstName.setBounds(50, 100, 200, 30);
        tf_lastName.setBounds(50, 150, 200, 30);
        tf_email.setBounds(50, 200, 200, 30);
        tf_username.setBounds(50, 250, 200, 30);
        pf_password.setBounds(50, 300, 200, 30);
        pf_confirmPassword.setBounds(50, 350, 200, 30);
        b_apply.setBounds(50, 400, 200, 30);
        b_apply.addActionListener(this);
        f.add(l_applyNow);
        f.add(tf_firstName);
        f.add(tf_lastName);
        f.add(tf_email);
        f.add(tf_username);
        f.add(pf_password);
        f.add(pf_confirmPassword);
        f.add(b_apply);
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Apply")) {
            firstName = tf_firstName.getText();
            lastName = tf_lastName.getText();
            email = tf_email.getText();
            username = tf_username.getText();
            password = pf_password.getText();
            confirmPassword = pf_confirmPassword.getText();
            if (password.equals(confirmPassword)) {
                // TODO: if (username is not taken) {
                SQL sql = new SQL();
                if (sql.customerExists(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists.");
                    return;
                }
                sql.insertPending(sql.getNextID("PendingCustomers"), firstName, lastName, email, username, password);
                // TODO: Create user and add to database for pending users
                JOptionPane.showMessageDialog(null, "Application created successfully! Check back soon");
                f.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Passwords do not match.");
            }
        }
    }


}
