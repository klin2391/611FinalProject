/*
 * Window_Stock.java
 * by Kevin Lin (lin2391@bu.edu)
 * 17APR2023
 * 
 * This is a window that shows all of a given 
 * type of stock owned by the user.
 */

import javax.swing.*;  
import java.util.*;
import java.awt.BorderLayout;
import java.awt.event.*;


public class Window_Stock{
    private JFrame f;
    private ArrayList <Stock> stocks;
    private JLabel l_name;
    private JLabel l_currentPrice;
    private JLabel l_profit;
    private JLabel l_numberOfShares;
    private JLabel l_totalValue;
    private JLabel l_totalCost;
    private JPanel p_graph;
    private JPanel p_info;
    
    // Default Constructor
    public Window_Stock(ArrayList <Stock> s, boolean isOwned){
        this.stocks = s;
        f = new JFrame(stocks.get(0).getSymbol());
        l_name = new JLabel("Name: " + stocks.get(0).getName());
        l_currentPrice = new JLabel("Current Price: " + stocks.get(0).getCurrentPrice());
        Double totalCost = 0.0;
        for (int i = 0; i < stocks.size(); i++){                    // Calculates the total cost of all stocks
            totalCost += stocks.get(i).getPurchasePrice();
        }
        Double profit = stocks.get(0).getCurrentPrice()*stocks.size() - totalCost;
        l_profit = new JLabel("Profit: " + profit);
        l_numberOfShares = new JLabel("Number of Shares: " + stocks.size());
        l_totalValue = new JLabel("Total Value: " + stocks.get(0).getCurrentPrice()*stocks.size());
        l_totalCost = new JLabel("Total Cost: " + totalCost);
        p_graph = new Grapher(stocks.get(0).getHistory(), profit, isOwned);

        l_name.setBounds(50, 50, 200, 30);
        l_currentPrice.setBounds(50, 100, 200, 30);
        l_totalCost.setBounds(50, 150, 200, 30);
        p_graph.setBounds(50, 300, 500, 30);
        l_profit.setBounds(50, 550, 200, 30);
        l_numberOfShares.setBounds(50, 600, 200, 30);
        l_totalValue.setBounds(50, 650, 200, 30);

        p_info = new JPanel();
        p_info.add(l_name);
        p_info.add(l_currentPrice);

        if (isOwned){   // If the stocks are owned, then show the total cost and profit
            p_info.add(l_profit);
            p_info.add(l_numberOfShares);
            p_info.add(l_totalCost);
        }

        f.setLayout(new BorderLayout());
        f.add(p_info, BorderLayout.NORTH);
        f.add(p_graph, BorderLayout.CENTER);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(true);
        f.setVisible(true);
    }
}
