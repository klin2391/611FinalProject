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

public class Window_Apply extends JPanel implements ActionListener {
//    private JFrame f;                           // Frame
    private Window w;
    private JLabel l_applyNow;
    private JTextField tf_firstName;
    private JTextField tf_lastName;
    private JTextField tf_email;
    private JTextField tf_username;
    private JPasswordField pf_password;
    private JPasswordField pf_confirmPassword;
    private JButton b_apply;
    private JButton b_close;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private SQL sql;


    // Constructor
    public Window_Apply(Window w) {
        this.w = w;
//        f = new JFrame("Apply Now");
        l_applyNow = new JLabel("Apply Now");
        tf_firstName = new JTextField("First Name");
        tf_lastName = new JTextField("Last Name");
        tf_email = new JTextField("Email");
        tf_username = new JTextField("Username");
        pf_password = new JPasswordField("Password");
        pf_confirmPassword = new JPasswordField("Password");
        b_apply = new JButton("Apply");
        b_close = new JButton("Close");

        l_applyNow.setBounds(50, 50, 200, 30);
        tf_firstName.setBounds(50, 100, 200, 30);
        tf_lastName.setBounds(50, 150, 200, 30);
        tf_email.setBounds(50, 200, 200, 30);
        tf_username.setBounds(50, 250, 200, 30);
        pf_password.setBounds(50, 300, 200, 30);
        pf_confirmPassword.setBounds(50, 350, 200, 30);
        b_apply.setBounds(50, 400, 200, 30);
        b_apply.addActionListener(this);
        b_close.setBounds(50, 450, 200, 30);
        b_close.addActionListener(this);

        this.add(l_applyNow);
        this.add(tf_firstName);
        this.add(tf_lastName);
        this.add(tf_email);
        this.add(tf_username);
        this.add(pf_password);
        this.add(pf_confirmPassword);
        this.add(b_apply);
        this.add(b_close);
        this.setSize(600, 600);
        this.setLayout(null);
        this.setVisible(true);

        sql = new SQL();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Apply")) {
            firstName = tf_firstName.getText();
            lastName = tf_lastName.getText();
            email = tf_email.getText();
            username = tf_username.getText();
            password = pf_password.getText();
            confirmPassword = pf_confirmPassword.getText();
            if (password.equals(confirmPassword)) { // Matches
                if (sql.customerExists(username)) { // Username already exists
                    JOptionPane.showMessageDialog(w.getFrame(), "Username already exists.");
                    return;
                }
                sql.insertPending(sql.getNextID("PendingCustomers"), firstName, lastName, email, username, password);
                JOptionPane.showMessageDialog(w.getFrame(), "Application created successfully! Check back soon");
//                f.dispose();
                w.dispose();

            }
            else {
                JOptionPane.showMessageDialog(w.getFrame(), "Passwords do not match.");
            }
        }
        else if (e.getActionCommand().equals("Close")) {
            new Window_Root();
            w.dispose();
//            f.dispose();
        }
    }
}
