/*
 * User.java
 * by Kevin Lin (lin2391@bu.edu)
 * 17ARP2023
 * 
 * This class is a tentative draft at creating a user.
 * Has name, password, username, balance and owned stonks
 * 
 * TODO: Listener for stocks when updated
 */

import java.util.HashMap;
import java.util.ArrayList;

public class User extends Person implements User_Account{
    private String firstName;
    private String lastName;
    private double balance;                                 // Cash Buying Power (realzed account value) in dollars
    private double profit;
    private HashMap <String, ArrayList<Stock>> portfolio;   // Stocks owned by user
    private String messageToUser;
    private ArrayList<Observer_User> windows;

    // Default Constructor
    public User() {
        firstName = "John";
        lastName = "Doe";
        username = "jdoe";
        password = "password";
        balance = 10000;
        profit = 0;
        portfolio = new HashMap <String, ArrayList<Stock>>();
        messageToUser = "";
        windows = new ArrayList<Observer_User>();
    }
    //for pending customers
    public User(String first, String last, String email, String username, String password){
        this(first, last, email, username, password, 0, 0);
    }
    // Constructor with values
    public User(String firstName, String lastName, String email, String username, String password, double balance,double profit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
        portfolio = new HashMap <String, ArrayList<Stock>>();
        messageToUser = "";
        windows = new ArrayList<Observer_User>();
        this.profit = profit;
    }

    // Accessor methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getBalance() {
        return balance;
    }
    public double getProfit() {
        return profit;
    }

    // Returns the value of all stocks owned
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

    public String getMessageToUser() {
        return messageToUser;
    }

    // Mutator methods
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addBalance(double deposit) {
        this.balance += deposit;
        SQL sql = new SQL();
        sql.updateBalance(this.username, (int) this.balance);
    }

    public void subtractBalance(double withdraw) {
        this.balance -= withdraw;
        SQL sql = new SQL();
        sql.updateBalance(this.username, (int) this.balance);
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

    public void removeStock(Stock stock) {
        if (portfolio.containsKey(stock.getSymbol())) {
            portfolio.get(stock.getSymbol()).remove(stock);
        }
    }

    public void setMessageToUser(String message) {
        messageToUser = message;
    }

    
    // Observer methods
    public void addWindow(Observer_User window) {
        windows.add(window);
    }

    public void removeWindow(Window_User window) {
        windows.remove(window);
    }

    public void updateStocks(ArrayList<Stock> stocks) {
        for (Stock stock : stocks) {
            if (portfolio.containsKey(stock.getSymbol())) {
                for (Stock ownedStock : portfolio.get(stock.getSymbol())) {
                    ownedStock.updatePrice(stocks);
                }
            }
        }
        updateWindows();
    }

    public void updateWindows() {
        for (Observer_User window : windows) {
            if (window != null)
                window.update(this);
        }
    }

    // Buy a stock.  Returns 1 if successful, -1 if not successful
    public int buyStock(Stock stock, int quantity) {
        if (balance >= stock.getCurrentPrice() * quantity) {
            for (int i = 0; i < quantity; i++) {
                stock.setPurchasePrice(stock.getCurrentPrice());
                addStock(stock);
            }
            subtractBalance(stock.getCurrentPrice() * quantity);
            messageToUser = "You have successfully bought " + quantity + " shares of " + stock.getName() + " (" + stock.getSymbol() + ") at $" + stock.getCurrentPrice() + " per share.";
            updateWindows();
            return 1;
        }
        else {
            messageToUser = "You do not have enough money to buy " + quantity + " shares of " + stock.getName() + " (" + stock.getSymbol() + ") at $" + stock.getCurrentPrice() + " per share.";
            return -1;
        }
    }

    // Sell a stock. Returns 1 if successful, -1 if not successful
    public int sellStock(Stock stock, int quantity) {
        if (portfolio.containsKey(stock.getSymbol())) {
            if (portfolio.get(stock.getSymbol()).size() >= quantity) {
                for (int i = 0; i < quantity; i++) {
                    portfolio.get(stock.getSymbol()).remove(0);
                    int prior = (int) this.profit;
                    profit += stock.getCurrentPrice() - stock.getPurchasePrice();
                    SQL sql = new SQL();
                    sql.updateProfit(this.username, (int) this.profit);
                    if (prior < sql.getMinToBeSuper() && this.profit >= sql.getMinToBeSuper() && !sql.isEligibleToBeSuper(this)) {
                        sql.addEligible(this);
                    }
                }
                if (portfolio.get(stock.getSymbol()).size() == 0) {
                    portfolio.remove(stock.getSymbol());
                }
                addBalance(stock.getCurrentPrice() * quantity);
                messageToUser = "You have successfully sold " + quantity + " shares of " + stock.getName() + " (" + stock.getSymbol() + ") at $" + stock.getCurrentPrice() + " per share.";
                updateWindows();
                return 1;
            }
            else {
                messageToUser = "You do not have enough shares of " + stock.getName() + " (" + stock.getSymbol() + ") to sell " + quantity + " shares.";
                return -1;
            }
        }
        else {
            messageToUser = "You do not own any shares of " + stock.getName() + " (" + stock.getSymbol() + ").";
            return -1;
        }
    }    
}
