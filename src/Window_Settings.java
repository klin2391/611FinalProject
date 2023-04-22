import javax.swing.*;
import java.awt.event.*;

public class Window_Settings implements ActionListener{
    private JFrame f;
    private Person p;
    private JLabel l_welcome;
    private JLabel l_password;
    private JPasswordField tf_passwordCurrent;
    private JLabel l_passwordNew;
    private JPasswordField tf_passwordNew;
    private JLabel l_passwordConfirm;
    private JPasswordField tf_passwordConfirm;

    private JButton b_changePassword;
    private JButton b_cancel;

    public Window_Settings(Person p){
            f = new JFrame("Settings");
        this.p = p;
        l_welcome = new JLabel("Welcome, " + p.getUsername() + "!");
        l_password = new JLabel("Current Password:");
        tf_passwordCurrent = new JPasswordField();
        l_passwordNew = new JLabel("New Password:");
        tf_passwordNew = new JPasswordField();
        l_passwordConfirm = new JLabel("Confirm Password:");
        tf_passwordConfirm = new JPasswordField();
        b_changePassword = new JButton("Change Password");
        b_cancel = new JButton("Cancel");

        b_changePassword.addActionListener(this);
        b_cancel.addActionListener(this);

        l_welcome.setBounds(50, 0, 200, 30);
        l_password.setBounds(50, 50, 200, 30);
        tf_passwordCurrent.setBounds(50, 100, 200, 30);
        l_passwordNew.setBounds(50, 150, 200, 30);
        tf_passwordNew.setBounds(50, 200, 200, 30);
        l_passwordConfirm.setBounds(50, 250, 200, 30);
        tf_passwordConfirm.setBounds(50, 300, 200, 30);
        b_changePassword.setBounds(50, 350, 200, 30);
        b_cancel.setBounds(50, 400, 200, 30);

        f.add(l_welcome);
        f.add(l_password);
        f.add(tf_passwordCurrent);
        f.add(l_passwordNew);
        f.add(tf_passwordNew);
        f.add(l_passwordConfirm);
        f.add(tf_passwordConfirm);
        f.add(b_changePassword);
        f.add(b_cancel);

        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_changePassword) {
            if (p.getPassword().equals(tf_passwordCurrent.getText())) {
                if (tf_passwordNew.getText().equals(tf_passwordConfirm.getText())) {
                    p.setPassword(tf_passwordNew.getText());
                    f.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(f, "Passwords do not match.");
                }
            }
            else {
                JOptionPane.showMessageDialog(f, "Incorrect password.");
            }
        }
        if (e.getSource() == b_cancel) {
            f.dispose();
        }
    }
}