/*
 * Window_Stock_Single.java
 * by Kevin Lin (lin2391@bu.edu)
 * 17APR2023
 * 
 * This windows that displays a single stock 
 * owned by a user. Can show the name, current price, purchase price, and profit
 */

import javax.swing.*;

public class Window_Stock_Single {
    private JFrame f;
    private Stock stock;
    private JLabel l_name;
    private JLabel l_currentPrice;
    private JLabel l_purchasePrice;
    private JLabel l_profit;

    // Default Constructor
    public Window_Stock_Single(Stock stock){
        this.stock = stock;
        f = new JFrame(stock.getSymbol());
        l_name= new JLabel("Name: " + stock.getName());
        l_currentPrice = new JLabel("Current Price: " + stock.getCurrentPrice());
        l_purchasePrice = new JLabel("Purchase Price: " + stock.getPurchasePrice());
        l_profit = new JLabel("Profit: " + (stock.getCurrentPrice() - stock.getPurchasePrice()));

        l_name.setBounds(50, 50, 200, 30);
        l_currentPrice.setBounds(50, 100, 200, 30);
        l_purchasePrice.setBounds(50, 150, 200, 30);
        l_profit.setBounds(50, 200, 200, 30);
        f.add(l_name);
        f.add(l_currentPrice);
        f.add(l_purchasePrice);
        f.add(l_profit);
        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
