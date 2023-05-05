/*
 * Stock.java
 * by Kevin Lin (lin2391@bu.edu)
 * 215APR2023
 * 
 * This class is a tentative draft at creating a stock object.
 * Stocks have a name, symbol, and price (includes current and history)
 */

import java.util.ArrayList;

 public class Stock{
    private String name;
    private String symbol;
    private Value price;                // Tracks current and past prices of stocks
    private double purchasePrice;
    private boolean owned;
    private SQL sql;
    
    // Default Constructor
    public Stock(){
        this.name = "Test";
        this.symbol = "TST";
        this.price = new Value();
        this.purchasePrice = 0;
        this.owned = false;
    }

    // Constructor with values
    public Stock(String name, String symbol, Value price){
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.owned = false;
    }

     public Stock(String name, String symbol, double currentPrice){
         this.name = name;
         this.symbol = symbol;
         this.price = new Value();
         setPrice(currentPrice);
         this.owned = false;
     }

     public Stock(String symbol){
        sql = new SQL();
        this.name = sql.getStockName(symbol);
        this.symbol = symbol;
        this.price = new Value(symbol);
        this.owned = false;
     }

     public Stock(String symbol, double purchasePrice){
        this(symbol);
        this.purchasePrice = purchasePrice;
        this.owned = true;
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
    public void setName(String name){
        this.name = name;
    }

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public void setPrice(Value price){
        this.price = price;
    }

    public void setPurchasePrice(double purchasePrice){
        this.purchasePrice = purchasePrice;
    }

    public void setOwned(boolean owned){
        this.owned = owned;
    }


    // Updates current price of stock
    public void updatePrice(ArrayList<Stock> stocks){
        for (int i = 0; i < stocks.size(); i++){
            if (stocks.get(i).getSymbol().equals(this.symbol)){
                setPrice(stocks.get(i).getCurrentPrice());
            }
        }
    }

    public void setPrice(double newPrice){
        price.setCurrent(newPrice);
    }
 }
