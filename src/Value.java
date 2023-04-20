/*
 * Value.java
 * by Kevin Lin (lin2391@bu.edu)
 * 17APR2023
 * 
 * This class is a tentative draft at creating a value for stocks
 * Required work invovles reading from a database
 */

import java.util.ArrayList;

public class Value {
    double current;
    ArrayList <Double> history;

    // Default Constructor
    public Value(){
        this.current = 0;
        this.history = new ArrayList <Double>();
    }

    // Constructor with values
    public Value(double current, ArrayList <Double> history){
        this.current = current;
        this.history = history;
        this.history.add(this.current);
    }

    // Accessor methods
    public double getCurrent(){
        return current;
    }

    public ArrayList <Double> getHistory(){
        return history;
    }

    // Mutator methods
    public void setCurrent(double current){
        this.history.add(this.current);
        this.current = current;
    }

    public void setHistory(ArrayList <Double> history){
        this.history = history;
    }
}
