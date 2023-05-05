/*
 * Window_ManagerRemoveStock.java
 * by Ryan
 * 23APR2023
 *
 * This is a window to remove a stock from market
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window_ManagerRemoveStock extends JPanel implements ActionListener {
//    private JFrame f;
    private Window w;
    private Manager m;
    private JComboBox<String> cb_stocks;
    private JButton b_remove;
    private JButton b_cancel;
    private String selectedStock;
    private SQL sql;

    // Constructor
    public Window_ManagerRemoveStock(Window w){
//        f = new JFrame("Remove Stock");
        this.w = w;
        this.m = Manager.getInstance();
        cb_stocks = new JComboBox<String>();
        for (int i = 0; i < m.getAvailableStocks().size(); i++){
            cb_stocks.addItem(m.getAvailableStocks().get(i).getName());
        }
        selectedStock = (String)cb_stocks.getSelectedItem();

        b_remove = new JButton("Remove");
        b_cancel = new JButton("Cancel");

        cb_stocks.addActionListener(this);
        b_remove.addActionListener(this);
        b_cancel.addActionListener(this);

        cb_stocks.setBounds(50, 50, 200, 30);
        b_remove.setBounds(50, 100, 200, 30);
        b_cancel.setBounds(50, 200, 200, 30);

        this.add(cb_stocks);
        this.add(b_remove);
        this.add(b_cancel);

        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        sql = new SQL();
    }

    // Updates the combo box fields
    public void updateComboBox(){
        cb_stocks.removeAllItems();
        for (int i = 0; i < m.getAvailableStocks().size(); i++){            // adds the remaining stocks
            cb_stocks.addItem(m.getAvailableStocks().get(i).getName());
        }
    }

    // Action Listener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_remove) {
//            sql.setStockUnavailable(selectedStock);
            m.removeStock(selectedStock);
            JOptionPane.showMessageDialog(w.getFrame(), "Remove "+selectedStock+ " from market successfully!");
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
