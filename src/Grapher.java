// https://stackoverflow.com/questions/47718958/java-swing-line-graph
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Grapher extends JPanel{
    // private int[] yCoords;
    private ArrayList <Double> yCoords;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int unitX;
    private int unitY;
    private int prevX;
    private int prevY;
    private Double max;
    private Double min;
    private boolean gain;

    public Grapher(ArrayList <Double> yCoords, Double profit) {
        this.yCoords = yCoords;
        max = yCoords.stream().max(Double::compareTo).get();
        System.out.println("max: " + max);
        min = yCoords.stream().min(Double::compareTo).get();
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
        gain = profit > 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //We draw in the following 2 loops the grid so it's visible what I explained before about each "unit"
        g2d.setColor(Color.BLACK);
        for (int i = startX; i <= endX; i += unitX) {
            g2d.drawLine(i, startY, i, endY);
        }

        for (int i = startY; i <= endY; i += unitY) {
            g2d.drawLine(startX, i, endX, i);
        }

        //We draw the axis here instead of before because otherwise they would become blue colored.
        g2d.setColor(Color.BLACK);
        g2d.drawLine(startX, startY, startX, endY);
        g2d.drawLine(startX, endY, endX, endY);
        
        g2d.drawString("10", endX, endY);
        g2d.drawString("0", startX, endY);
        g2d.drawString(""+max, startX, startY);

        //We draw each of our coords in red color
        if (gain)
            g2d.setColor(Color.GREEN);
        else
            g2d.setColor(Color.RED);
        // g2d.setColor(Color.RED);
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
