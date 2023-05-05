/*
 * Window_ManagerUpdateStock.java
 * by Ryan
 * 23APR2023
 *
 * This is a window to update stock prices
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window_ManagerUpdateStock extends JPanel implements ActionListener {
//    private JFrame f;
    private Window w;
    private Manager m;
    private JComboBox<String> cb_stocks;
    private JButton b_update;
    private JTextField tf_stockPrice;
    private JButton b_updateAll;
    private JButton b_cancel;
    private double stockPrice;
    private String selectedStock;
    private SQL sql;

    // Constructor
    public Window_ManagerUpdateStock(Window w){
//        f = new JFrame("Update Stock Price");
        this.w = w;
        this.m = Manager.getInstance();
        cb_stocks = new JComboBox<String>();
        
        for (int i = 0; i < m.getAvailableStocks().size(); i++){
            cb_stocks.addItem(m.getAvailableStocks().get(i).getName());
        }
        selectedStock = (String)cb_stocks.getSelectedItem();
        
        b_update = new JButton("Update");
        tf_stockPrice = new JTextField("Stock Price");
        b_updateAll = new JButton("Update All");
        b_cancel = new JButton("Cancel");

        cb_stocks.addActionListener(this);
        b_update.addActionListener(this);
        b_updateAll.addActionListener(this);
        b_cancel.addActionListener(this);

        cb_stocks.setBounds(50, 50, 200, 30);
        tf_stockPrice.setBounds(50, 100, 200, 30);
        b_update.setBounds(50, 150, 200, 30);
        b_updateAll.setBounds(50, 250, 200, 30);
        b_cancel.setBounds(50, 300, 200, 30);

        this.add(cb_stocks);
        this.add(tf_stockPrice);
        this.add(b_update);
        this.add(b_updateAll);
        this.add(b_cancel);

        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        sql = new SQL();
    }

    // Updates the combo box fields
    public void updateComboBox(){
        cb_stocks.removeAllItems();
        for (int i = 0; i < m.getAvailableStocks().size(); i++){            // adds the remaining pending users
            cb_stocks.addItem(m.getAvailableStocks().get(i).getName());
        }
    }

    // Action Listener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_update) {
            try {
                stockPrice = Double.parseDouble(tf_stockPrice.getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(w.getFrame(), "Please enter a valid price!");
                return;
            }
            m.updateStock(selectedStock,stockPrice);
            JOptionPane.showMessageDialog(w.getFrame(), "Update "+selectedStock+ " price to " + String.valueOf(stockPrice) + " successfully!");
            tf_stockPrice.setText("Stock Price");
            updateComboBox();
        }
        else if (e.getSource() == b_updateAll) {
            m.randomUpdateAll();
            JOptionPane.showMessageDialog(w.getFrame(), "Update price of all stocks successfully!");
            updateComboBox();
        }
        else if (e.getSource() == b_cancel) {
            w.update(new Window_Manager(Manager.getInstance(), w));
            w.setTitle("Manager");
        }
        else{
            JComboBox cb = (JComboBox)e.getSource();                                // If dropdown is changed
            selectedStock = (String)cb.getSelectedItem();
        }
    }

}
