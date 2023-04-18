/*
 * Stock.java
 * by Kevin Lin (lin2391@bu.edu)
 * 215APR2023
 * 
 * This class is a tentative draft at creating a stock object
 */

import java.util.ArrayList;

 public class Stock{
    private String name;
    private String symbol;
    private Value price;                // Tracks current and past prices of stocks
    private double purchasePrice;
    private boolean owned;
    
    // Default Constructor
    public Stock(){
        this.name = "Test";
        this.symbol = "TST";
        this.price = new Value();
        this.purchasePrice = 0;
        this.owned = false;
    }

    // Constructor with values
    public Stock(String name, String symbol, Value price, double purchasePrice){
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.purchasePrice = purchasePrice;
        this.owned = false;
    }

    // Accessor methods
    public String getName(){
        return name;
    }

    public Value getPrice(){
        return price;
    }

    public String getSymbol(){
        return symbol;
    }

    public double getCurrentPrice(){
        return price.getCurrent();
    }

    public double getPurchasePrice(){
        return purchasePrice;
    }

    public boolean getOwned(){
        return owned;
    }

    public ArrayList<Double> getHistory(){
        return price.getHistory();
    }

    // Mutator methods

 }