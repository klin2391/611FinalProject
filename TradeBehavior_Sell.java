/*
 * TradeBehavior_Sell.java
 * by Kevin Lin (lin2391@bu.edu)
 * 20APR2023
 * 
 * This implements strategy pattern. Sell behavior
 */

public class TradeBehavior_Sell implements TradeBehavior {
    public String getName(){
        return "Sell";
    }
    public int execute(User u, Stock s, int numStocks){
        return u.sellStock(s, numStocks);
    }
}
