/*
 * Market.java
 * by Kevin Lin (lin2391@bu.edu)
 * 20APR2023
 * 
 * This class will be used to represent the stock market
 * It will contain a list of stocks and a list of users
 * as well as a manager
 * 
 * TODO should have a manager class and potentially implement listner
 */

import java.util.*;

public class Market {
    private ArrayList<Stock> stocks;
    private ArrayList<User> users;

    // Default Constructor
    public Market() {
        stocks = new ArrayList<Stock>();
        users = new ArrayList<User>();
    }

    // Constructor with values
    public Market(ArrayList<Stock> stocks, ArrayList<User> users) {
        this();
        if (stocks != null) {
            this.stocks = stocks;
        }
        if (users != null) {
            this.users = users;
        }
    }

    // Accessor methods
    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public void addUser(User user) {
        user.updateStocks(stocks);
        users.add(user);
    }

    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void updateStocks() {
        for (User user : users) {
            user.updateStocks(stocks);
        }
    }
}
