/*
 * Grapher.java
 * by Kevin Lin (lin2391@bu.edu)
 * 20APR2023
 * 
 * This class used to create a graph of the stock's history
 * Adapted from https://stackoverflow.com/questions/47718958/java-swing-line-graph
 */


import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Grapher extends JPanel{
    private ArrayList <Double> yCoords;         // The prices of the stock
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int unitX;
    private int unitY;
    private int prevX;
    private int prevY;
    private Double max;
    private boolean gain;

    // Constructor
    public Grapher(ArrayList <Double> yCoords, Double profit, boolean owned) {
        this.yCoords = yCoords;
        max = yCoords.stream().max(Double::compareTo).get();        // We find the max value in the arraylist
        int maxInt = (int) (max / 10);
        max = (maxInt + 1) * 10.0;
        startX = 100;
        startY = 100;
        endX = 400;
        endY = 400;
        unitX = (endX - startX) / 10;
        unitY = (int) ((endY - startY) / max);
        prevX = startX;
        prevY = (int) (endY - (yCoords.get(0) * unitY));
        if (owned){
            gain = profit >= 0;
        }
        else{
            gain = yCoords.get(yCoords.size()-1) > yCoords.get(yCoords.size()-2);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        for (int i = startX; i <= endX; i += unitX) {       // We draw the grid here
            g2d.drawLine(i, startY, i, endY);
        }

        for (int i = startY; i <= endY; i += unitY) {       // We draw the grid here
            g2d.drawLine(startX, i, endX, i);
        }

        
        g2d.setColor(Color.BLACK);
        g2d.drawLine(startX, startY, startX, endY);         // We draw the axes here
        g2d.drawLine(startX, endY, endX, endY);
        
        g2d.drawString("present", endX, endY);               // We draw the labels here
        g2d.drawString(""+max, startX, startY);

        
        if (gain)                                          // gain is positive
            g2d.setColor(Color.GREEN);
        else
            g2d.setColor(Color.RED);
        for (int i = yCoords.size()-10; i < yCoords.size(); i++) {
            if (i < 1)
                continue;
            g2d.drawLine(prevX, prevY, prevX += unitX, prevY = (int) (endY - (yCoords.get(i) * unitY)));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(endX + 100, endY + 100);
    }
}
