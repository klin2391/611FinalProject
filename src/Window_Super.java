import java.awt.event.*;
import javax.swing.*;

public class Window_Super implements ActionListener{
    private JFrame f;
    private JLabel l_welcome;
    private JButton b_stocks;
    private JButton b_options;
    private JButton b_logout;
    private User user;
    private SQL sql;

    // Constructor
    public Window_Super(User user){
        this.user = user;
        f = new JFrame("Super User" + user.getUsername());
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
        f.add(l_welcome);
        f.add(b_stocks);
        f.add(b_options);
        f.add(b_logout);
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
    }

    // Action Listener
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b_stocks){
            new Window_User(user, 0);
            f.dispose();
        }
        else if(e.getSource() == b_options){
            JOptionPane.showMessageDialog(null, "Option Trading has not yet been implemented");
            f.dispose();
        }
        else if(e.getSource() == b_logout){
            new Window_Login();
            f.dispose();
        }
    }
}
