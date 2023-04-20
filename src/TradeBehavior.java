/*
 * TradeBehavior.java
 * by Kevin Lin (lin2391@bu.edu)
 * 20APR2023
 * 
 * This interface implements strategy pattern.
 * Default trade behavior
 */

public interface TradeBehavior {
    public String getName();
    public int execute(User u, Stock s, int numStocks);
}
