/*
 * Window_Forgot.java
 * by Kevin Lin (lin2391@bu.edu)
 * 23APR2023
 *
 * Window for user to recover password
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Window_Forgot extends JPanel implements ActionListener{
    //    private JFrame f;                           // Frame
    private Window w;
    private JLabel l_welcome;
    private JTextField tf_username;
    private JTextField tf_email;
    private JButton b_submit;
    private JButton b_cancel;

    private String username;
    private String email;
    private SQL sql;

    // Constructor
    public Window_Forgot(Window w){
//        f = new JFrame("Forgot Password");
        this.w = w;
        l_welcome = new JLabel("Forgot Password");
        tf_username = new JTextField("Username");
        tf_email = new JTextField("Email");
        b_submit = new JButton("Submit");
        b_cancel = new JButton("Cancel");

        l_welcome.setBounds(50, 50, 200, 30);
        tf_username.setBounds(50, 100, 200, 30);
        tf_email.setBounds(50, 150, 200, 30);
        b_submit.setBounds(50, 200, 200, 30);
        b_cancel.setBounds(50, 250, 200, 30);
        b_submit.addActionListener(this);
        b_cancel.addActionListener(this);
        this.add(l_welcome);
        this.add(tf_username);
        this.add(tf_email);
        this.add(b_submit);
        this.add(b_cancel);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        sql = new SQL();
    }

    // Action Listener
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b_submit){
            username = tf_username.getText();
            email = tf_email.getText();
            if(sql.customerExists(username)){       // VERIFY ACCOUNT
                String pass = sql.recoverPassword(username, email);
                new Window_EmailNotification( "Your password is: " + pass, email + " SUBJECT: Password Recovery" );
                w.update(new Window_Login(w));
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Username or Email");
            }
        }
        else if(e.getSource() == b_cancel){
            Window_Login wl = new Window_Login(w);
            w.update(wl);
        }
    }
}