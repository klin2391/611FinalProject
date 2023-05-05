/*
 * Window_Root.java
 * by Kevin Lin (lin2391@bu.edu)
 * 21APR2023
 *
 * This class is the home page of the program.
 * It allows the user to login or create an account.
 */
import java.net.URL;
import javax.swing.ImageIcon;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Window_Root extends JFrame implements ActionListener  {
    private JFrame f;                           // Frame
    private JPanel p;
    private User user;                          // User profile
    private JLabel l_welcome;
    private JLabel background;
    private JButton b_createAccount;
    private JButton b_login;
    private JPanel p_north;
    private JPanel p_center;
    private JPanel p_south;
//    private Manager m;


    // Constructor
    public Window_Root(){
        f = new JFrame("Welcome to Stock Simulator!");
        l_welcome = new JLabel("Welcome to Stock Simulator!");
        b_createAccount = new JButton("Create Account");
        b_login = new JButton("Login");
        p = new JPanel();
        p_north = new JPanel();
        p_center = new JPanel();
        p_south = new JPanel();
        p.setBorder(new EmptyBorder(10, 10, 10, 10));



//        background = new JLabel();
//        URL resource = this.getClass().getResource("/background2.jpg");
//        ImageIcon icon = new ImageIcon(resource);
//        background.setIcon(icon);
//        background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
//        System.out.println(icon.getIconWidth());
//        System.out.println(icon.getIconHeight());
//        f.getContentPane().add(background);
//        l_welcome.setBounds(50, 50, 200, 30);
//        b_createAccount.setBounds(50, 100, 200, 30);
//        b_login.setBounds(50, 150, 200, 30);


        b_createAccount.addActionListener(this);
        b_login.addActionListener(this);

        p_north.add(l_welcome);
        p_center.add(b_createAccount);
        p_center.add(b_login);

        p.setLayout(new BorderLayout(100,100));
        p.add(p_north, BorderLayout.NORTH);
        p.add(p_center, BorderLayout.CENTER);
        p.add(p_south, BorderLayout.SOUTH);

//        f.setLayout(new BorderLayout());
        f.add(p);
        f.setSize(500, 500); //476
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SQL sql = new SQL();
//        m = sql.getManager("admin");

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == b_createAccount){
            Window w = new Window("Stock Market", 800, 600);
            Window_Apply wa = new Window_Apply(w);
            w.update(wa);
//            f.dispose();
        }
        else if (e.getSource() == b_login){

            //new Window_Login();

            new Window_Login();

//            f.dispose();
        }
    }
}
