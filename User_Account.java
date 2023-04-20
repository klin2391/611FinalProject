/*
 * User_Account.java
 * by Kevin Lin (lin2391@bu.edu)
 * 20APR2023
 * 
 * Interface for a user account.
 * Will allow for multiple types of accounts
 */

public interface User_Account {
    public void addBalance(double deposit);
    public void subtractBalance(double withdraw);
    public double getBalance();
    public double getStockValue();
    public double getTotalValue();
    public int buyStock(Stock stock, int quantity);
    public int sellStock(Stock stock, int quantity);
}
