/*
 * User.java
 * by Kevin Lin (lin2391@bu.edu)
 * 17ARP2023
 * 
 * This class is a tentative draft at creating a user.
 * Has name, password, username, balance and owned stonks
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.sql.*;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private double balance;             // Cash Buying Power (realized account value) in dollars
    private HashMap <String, ArrayList<Stock>> portfolio;

    // Default Constructor
    public User() {
        firstName = "John";
        lastName = "Doe";
        username = "jdoe";
        password = "password";
        balance = 10000;
        portfolio = new HashMap <String, ArrayList<Stock>>();
    }

    // Constructor with values
    public User(String firstName, String lastName, String username, String password, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.balance = balance;
        portfolio = new HashMap <String, ArrayList<Stock>>();
    }

    // Accessor methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public double getStockValue() {
        double value = 0;
        for (String symbol : portfolio.keySet()) {
            for (Stock stock : portfolio.get(symbol)) {
                value += stock.getCurrentPrice();
            }
        }
        return value;
    }

    public double getTotalValue() {
        return balance + getStockValue();
    }

    public HashMap <String, ArrayList<Stock>> getPortfolio() {
        return portfolio;
    }

    public ArrayList<Stock> getStock(String symbol) {
        return portfolio.get(symbol);
    }

    // Mutator methods
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addBalance(double deposit) {
        this.balance += deposit;
    }

    public void subtractBalance(double withdraw) {
        this.balance -= withdraw;
    }

    public void addStock(Stock stock) {
        if (portfolio.containsKey(stock.getSymbol())) {     // If owned, adds to appropriate arraylist
            portfolio.get(stock.getSymbol()).add(stock);
        }
        else {
            ArrayList<Stock> stocks = new ArrayList<Stock>(); // Otherwise, create new arraylist
            stocks.add(stock);
            portfolio.put(stock.getSymbol(), stocks);
        }   
    }

    public static void main(String[] args) {
        SQL sql = new SQL();
        sql.insertCustomer("Spongebob", "Squarepants", "SBOB", "SQUARE", 10000);
    }
}
