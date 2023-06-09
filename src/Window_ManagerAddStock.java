/*
 * Window_ManagerAddStock.java
 * by Ryan
 * 23APR2023
 *
 * This is a window to add a stock to market
 */
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window_ManagerAddStock extends JPanel implements ActionListener {
//    private JFrame f;                           // Frame
    private Window w;
    private JLabel l_addStock;
    private JTextField tf_stockName;
    private JTextField tf_stockPrice;
    private JTextField tf_stockSymbol;
    private JButton b_back;
    private JButton b_add;
    private Manager manager;
    private String stockName;
    private double stockPrice;
    private String stockSymbol;
    private SQL sql;
    private JPanel p;

    // Constructor
    public Window_ManagerAddStock(Window w){
        this.manager = Manager.getInstance();
//        f = new JFrame("Login");
        this.w = w;
        p = new JPanel();
        l_addStock = new JLabel("Add stock!");
        tf_stockName = new JTextField("Stock Name");
        tf_stockSymbol = new JTextField("Stock Symbol");
        tf_stockPrice = new JTextField("Price");
        b_add = new JButton("Add");
        b_back = new JButton("Back");

        l_addStock.setBounds(50, 50, 200, 30);
        tf_stockName.setBounds(50, 100, 200, 30);
        tf_stockSymbol.setBounds(50, 150, 200, 30);
        tf_stockPrice.setBounds(50, 200, 200, 30);
        b_add.setBounds(50, 250, 300, 30);
        b_back.setBounds(50, 300, 300, 30);

        b_add.addActionListener(this);
        b_back.addActionListener(this);
        p.add(l_addStock);
        p.add(tf_stockName);
        p.add(tf_stockSymbol);
        p.add(tf_stockPrice);
        p.add(b_add);
        p.add(b_back);
        p.setSize(500, 500);
        p.setLayout(null);
        p.setVisible(true);
        this.add(p);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        sql = new SQL();
    }

    // Action Listener
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b_add){     // if the login button is pressed
            stockName = tf_stockName.getText();
            try{
                stockPrice = Double.valueOf(tf_stockPrice.getText());
            }
            catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(w.getFrame(), "Please enter a valid price.");
                return;
            }
            stockSymbol = tf_stockSymbol.getText();
            if (manager.addStock(stockName, stockPrice, stockSymbol) < 0){
                JOptionPane.showMessageDialog(w.getFrame(), "Stock Exists.");
                return;
            }
            JOptionPane.showMessageDialog(w.getFrame(), "Added the stock successfully!");
            w.update(new Window_Manager(Manager.getInstance(), w));
            w.setTitle("Manager");
        }
        else if(e.getSource() == b_back){
            w.update(new Window_Manager(Manager.getInstance(), w));
            w.setTitle("Manager");
        }
    }

}
