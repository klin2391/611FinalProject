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

public class User extends Person implements User_Account, Observer_Stock{
    private String firstName;
    private String lastName;
    private double balance;                                 // Cash Buying Power (realzed account value) in dollars
    private double profit;
    private double unrealizedProfit;
    private HashMap <String, ArrayList<Stock>> portfolio;   // Stocks owned by user
    private String messageToUser;
    private ArrayList<Observer_User> windows;
    private SQL sql;

    // Default Constructor
    public User() {
        firstName = "John";
        lastName = "Doe";
        username = "jdoe";
        password = "password";
        balance = 10000;
        profit = 0;
        unrealizedProfit = 0;
        portfolio = new HashMap <String, ArrayList<Stock>>();
        messageToUser = "";
        windows = new ArrayList<Observer_User>();
        sql = new SQL();
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
        sql = new SQL();
    }

    public void setUnrealizedProfit(){ //calculate the unrealized profit when needed
        double purchasePrice = 0;
        for (String symbol : portfolio.keySet()) { // calculate the purchase price for all stocks a user has
            for (Stock stock : portfolio.get(symbol)) {
                purchasePrice += stock.getPurchasePrice();
            }
        }
        this.unrealizedProfit = getStockValue() - purchasePrice;
    }


    public void setPortfolio(HashMap <String, ArrayList<Stock>> portfolio){
        this.portfolio = portfolio;
    }

    public double getUnrealizedProfit(){
        return this.unrealizedProfit;
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
        sql.updateBalance(this.username, (int) this.balance);       // updates db
    }

    public void subtractBalance(double withdraw) {
        this.balance -= withdraw;
        sql.updateBalance(this.username, (int) this.balance);       // updates db
    }

    public void addStock(Stock stock) {
        sql.insertOwnedStock(stock, this);
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
        System.out.println("DBG User.updateStocks()");
        for (Stock stock : stocks) {
            if (portfolio.containsKey(stock.getSymbol())) {
                System.out.println("DBG User.updateStocks() - " + stock.getSymbol());
                System.out.println("DBG User.updateStocks() - " + portfolio.get(stock.getSymbol()).size());
                System.out.println("DBG User.updateStocks() - " + stock.getHistory());
                for (Stock ownedStock : portfolio.get(stock.getSymbol())) {
                    ownedStock.updatePrice(stocks);
                    System.out.println(ownedStock.getHistory());
                }
            }
        }
        updateWindows();
    }

    public void update(){
        // DO the actual updating
        updateStocks(sql.getAllStocks());
        setUnrealizedProfit();
        updateWindows();
    }

    public void register(Manager m){
        m.addObs(this);
    }

    public void updateWindows() {
        for (Observer_User window : windows) {
            if (window != null)
                window.update(this);
        }
    }

    // Buy a stock.  Returns 1 if successful, -1 if not successful
//    public int buyStock(Stock stock, int quantity) {
//        if (balance >= stock.getCurrentPrice() * quantity) {
//            for (int i = 0; i < quantity; i++) {
//                stock.setPurchasePrice(stock.getCurrentPrice());
//                addStock(stock);
//            }
//            subtractBalance(stock.getCurrentPrice() * quantity);
//            messageToUser = "You have successfully bought " + quantity + " shares of " + stock.getName() + " (" + stock.getSymbol() + ") at $" + stock.getCurrentPrice() + " per share.";
//            updateWindows();
//            return 1;
//        }
//        else {
//            messageToUser = "You do not have enough money to buy " + quantity + " shares of " + stock.getName() + " (" + stock.getSymbol() + ") at $" + stock.getCurrentPrice() + " per share.";
//            return -1;
//        }
//    }

        public int buyStock(Stock stock, int quantity) {
            if (balance >= stock.getCurrentPrice() * quantity) {
//                for (int i = 0; i < quantity; i++) {
//                    stock.setPurchasePrice(stock.getCurrentPrice());
//                    addStock(stock);
//                }
                double totalCostStock = sql.getStockTotalCost(stock.getSymbol(), this);
                System.out.println("totalCostStock: " + totalCostStock);
                for (int i = 0; i < quantity; i++){
                    stock.setPurchasePrice(stock.getCurrentPrice());
                    addStock(stock);
                    totalCostStock += stock.getCurrentPrice();
                    System.out.println("totalCostStock: " + totalCostStock);
                }
                sql.updateStockAvgCost(stock.getSymbol(), this);
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
        if (portfolio.containsKey(stock.getSymbol())) {     // if they own
            if (portfolio.get(stock.getSymbol()).size() >= quantity) {  // if they own enough
                for (int i = 0; i < quantity; i++) {
                    int prior = (int) this.profit;
                    profit += stock.getCurrentPrice() - portfolio.get(stock.getSymbol()).get(0).getPurchasePrice();
                    portfolio.get(stock.getSymbol()).remove(0);
                    System.out.println(profit);
                    sql.updateProfit(this.username, (int) this.profit);                 // updates db
                    System.out.println("db updated");
                    sql.removeStock(stock, this);
                    if (prior < sql.getMinToBeSuper() && this.profit >= sql.getMinToBeSuper() && !sql.isEligibleToBeSuper(this)) {      // Checks to see if just crossed threshold
                        sql.addEligible(this);
                    }
                }
                if (portfolio.get(stock.getSymbol()).size() == 0) { // if they sold last one
                    portfolio.remove(stock.getSymbol());
                }
                addBalance(stock.getCurrentPrice() * quantity);
                messageToUser = "You have successfully sold " + quantity + " shares of " + stock.getName() + " (" + stock.getSymbol() + ") at $" + stock.getCurrentPrice() + " per share.";
                updateWindows();
                return 1;       //success
            }
            else {
                messageToUser = "You do not have enough shares of " + stock.getName() + " (" + stock.getSymbol() + ") to sell " + quantity + " shares.";
                return -1;      // not enough shares
            }
        }
        else {
            messageToUser = "You do not own any shares of " + stock.getName() + " (" + stock.getSymbol() + ").";
            return -1;          // not owned
        }
    }    
}
