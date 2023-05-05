/*
 * Window_Trade.java
 * by Kevin Lin (lin2391@bu.edu)
 * 18APR2023
 * 
 * This is a window for buying and selling stocks
 */

import javax.swing.*;  
import java.util.*;
import java.awt.BorderLayout;
import java.awt.event.*;


public class Window_Trade extends JPanel implements ActionListener, Observer_User, Observer_Stock{
//    private JFrame f;
    private Window w;
    private HashMap <String, ArrayList<Stock>> myStocks;
    private ArrayList <Stock> worldStocks;
    private JLabel l_browse;
    private JLabel l_owned;
    private JComboBox <String> cb_allStocks;
    private JComboBox <String> cb_stocksOwned;
    private JButton b_buy;
    private JButton b_sell;
    private JButton b_cancel;
    private User user;

    // Default Constructor
    public Window_Trade(HashMap <String, ArrayList<Stock>> mine, ArrayList <Stock> world, User u, Window w){
        this.user = u;
        register(user);
        register(Manager.getInstance());
        this.myStocks = mine;
        this.worldStocks = world;

//        f = new JFrame("Trade");
        this.w = w;
        l_browse = new JLabel("Browse Stocks");
        l_owned = new JLabel("My Stocks");
        cb_allStocks = new JComboBox <String>();
        cb_stocksOwned = new JComboBox <String>();
        b_buy = new JButton("Buy");
        b_sell = new JButton("Sell");
        b_cancel = new JButton("Cancel");
        cb_allStocks.addItem("Select a Stock");
        cb_stocksOwned.addItem("Select a Stock");
        for (int i = 0; i < worldStocks.size(); i++){
            cb_allStocks.addItem(worldStocks.get(i).getSymbol());
        }
        myStocks.forEach((k, v) -> {
            cb_stocksOwned.addItem(k);
        });

        cb_allStocks.addActionListener(this);
        cb_stocksOwned.addActionListener(this);
        b_buy.addActionListener(this);
        b_sell.addActionListener(this);
        b_cancel.addActionListener(this);

        l_browse.setBounds(50, 50, 200, 30);
        l_owned.setBounds(50, 250, 200, 30);
        cb_allStocks.setBounds(50, 100, 200, 30);
        cb_stocksOwned.setBounds(50, 300, 200, 30);
        b_buy.setBounds(50, 150, 200, 30);
        b_sell.setBounds(50, 350, 200, 30);
        b_cancel.setBounds(50, 400, 200, 30);

        this.add(l_browse);
        this.add(l_owned);
        this.add(cb_allStocks);
        this.add(cb_stocksOwned);
        this.add(b_buy);
        this.add(b_sell);
        this.add(b_cancel);

        this.setSize(600, 600);
        this.setLayout(null);
        this.setVisible(true);

    }
    

    // Action Listener for the JComboBox
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == cb_allStocks){
            if (cb_allStocks.getSelectedItem() != "Select a Stock"){
                String symbol = (String)cb_allStocks.getSelectedItem();
                for (int i = 0; i < worldStocks.size(); i++){
                    if (worldStocks.get(i).getSymbol().equals(symbol)){
                        Window_Stock wsi = new Window_Stock(new ArrayList<Stock>(Arrays.asList(worldStocks.get(i))), false);        // false means it's not owned
                    }
                }
            }
        }
        else if (e.getSource() == cb_stocksOwned){
            if (cb_stocksOwned.getSelectedItem() != "Select a Stock"){
                String symbol = (String)cb_stocksOwned.getSelectedItem();
                myStocks.forEach((k, v) -> {
                    if (k.equals(symbol)){
                        Window_Stock wsi = new Window_Stock(v, true);
                    }
                });
            }
        }
        else if (e.getSource() == b_buy){
            TradeBehavior tb = new TradeBehavior_Buy();
            w.update(new Window_BuySell(worldStocks, user, tb, w));
            w.setTitle("Buy");
        }
        else if (e.getSource() == b_sell){
            TradeBehavior tb = new TradeBehavior_Sell();
            w.update(new Window_BuySell(worldStocks, user, tb,w));
            w.setTitle("Sell");
        }
        else if (e.getSource() == b_cancel){
            w.update(new Window_User(user, 0, w));
            w.setTitle(user.getUsername());
        }
    
    }

    // Observer Pattern
    public void update(User u){
        this.user = u;
        this.myStocks = u.getPortfolio();
        cb_stocksOwned.removeAllItems();
        cb_stocksOwned.addItem("Select a Stock");
        myStocks.forEach((k, v) -> {
            cb_stocksOwned.addItem(k);
        });
    }

    public void register(User u){
        u.addWindow((Observer_User) this);
    }

    public void register(Manager m) {
        m.addObs(this);
    }

    public void update(){
        cb_allStocks.removeAllItems();
        cb_allStocks.addItem("Select a Stock");
        SQL sql = new SQL();
        for (int i = 0; i < sql.getAllAvailableStocks().size(); i++){
            cb_allStocks.addItem(sql.getAllAvailableStocks().get(i).getSymbol());
        }
    }
}
