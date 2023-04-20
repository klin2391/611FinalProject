/*
 * TradeBehavior_Buy.java
 * by Kevin Lin (lin2391@bu.edu)
 * 20APR2023
 * 
 * This implements strategy pattern. Buy behavior
 */

public class TradeBehavior_Buy implements TradeBehavior {
    public String getName(){
        return "Buy";
    }
    public int execute(User u, Stock s, int numStocks){
        return u.buyStock(s, numStocks);
    }
}
