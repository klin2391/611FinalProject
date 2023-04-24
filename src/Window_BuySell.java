/*
 * Window_BuySell.java
 * by Kevin Lin (lin2391@bu.edu)
 * 19APR2023
 * 
 * This is a window for buying and selling stocks
 */

 import javax.swing.*;  
 import java.util.*;
 import java.awt.BorderLayout;
 import java.awt.event.*;

 public class Window_BuySell implements ActionListener{
    private JFrame f;
    private Stock selectedStock = null;
    private JComboBox <String> cb_stocks;
    private JLabel l_currentVal;
    private JLabel l_numOwned;
    private JTextField tf_numStocks;
    private JButton b_action;
    private JButton b_cancel;
    private User user;
    private TradeBehavior action;

    // Default Constructor
    public Window_BuySell(ArrayList <Stock> s, User u, TradeBehavior a){
        this.user = u;
        this.action = a;        // buy or sell
        f = new JFrame(a.getName());
        cb_stocks = new JComboBox <String>();
        cb_stocks.addItem("Select a Stock to " + a.getName().toLowerCase());
        if (a.getName() == "Sell"){
            for (int i = 0; i < u.getPortfolio().keySet().toArray().length; i++)
                cb_stocks.addItem((String) u.getPortfolio().keySet().toArray()[i]);
        }
        else if (a.getName() == "Buy"){
            for (int i = 0; i < s.size(); i++){
                cb_stocks.addItem(s.get(i).getSymbol());
            }
        }
        l_currentVal = new JLabel("Current Value: -" );
        l_numOwned = new JLabel("Number Owned: -");
        tf_numStocks = new JTextField();
        b_action = new JButton(action.getName());
        b_cancel = new JButton("Cancel");
        b_action.addActionListener(this);
        b_cancel.addActionListener(this);
        cb_stocks.addActionListener(this);
        
        cb_stocks.setBounds(50, 50, 200, 30);
        l_currentVal.setBounds(50, 100, 200, 30);
        l_numOwned.setBounds(50, 150, 200, 30);
        tf_numStocks.setBounds(50, 200, 200, 30);
        b_action.setBounds(50, 250, 200, 30);
        b_cancel.setBounds(50, 300, 200, 30);
        f.add(cb_stocks);
        f.add(l_currentVal);
        f.add(l_numOwned);
        f.add(tf_numStocks);
        f.add(b_action);
        f.add(b_cancel);
        
        f.setSize(800, 800);
        f.setLayout(null);
        f.setVisible(true);
        
    }
    // Action Listener for the JComboBox
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == b_action ){
            if (tf_numStocks.getText().isEmpty()|| selectedStock == null){
                JOptionPane.showMessageDialog(f, "Please select a stock and enter a number to " + action.getName().toLowerCase());
                return;
            }
            int numStocks = Integer.parseInt(tf_numStocks.getText());
            if (action.execute(user, selectedStock, numStocks) < 0){        // fail
                JOptionPane.showMessageDialog(f, "Insufficient Resources!");
                return;
            }
            f.dispose();
        }
        else if (e.getSource() == b_cancel){
            f.dispose();
        }
        else{
            JComboBox cb = (JComboBox)e.getSource();
            String symbol = (String)cb.getSelectedItem();
            updatePage(symbol);
        }
    }

    // Updates the page when a new stock is selected
    public void updatePage(String symbol) {
        if (symbol.equals("Select a Stock to " + action.getName().toLowerCase())){
            l_currentVal.setText("Current Value: -");
            l_numOwned.setText("Number Owned: -");
            selectedStock = null;
            return;
        }
        for (int i = 0; i < Market.getStocks().size(); i++){
            if (Market.getStocks().get(i).getSymbol().equals(symbol)){
                selectedStock = Market.getStocks().get(i);
                l_currentVal.setText("Current Value: " + selectedStock.getCurrentPrice());
                user.getPortfolio().forEach((k, v) -> {
                    if (v.get(0).getSymbol().equals(symbol)){
                        l_numOwned.setText("Number Owned: " + v.size());
                    }
                });
            }
        }
    }
}
