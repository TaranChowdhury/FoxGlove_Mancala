package View;

import StoneDisplay.StoneShape;
import View.Formatter;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class StoneHolder {

    protected int numStones;
    protected StoneHolder(int i){
        numStones = i;
    }
    public void setNumStones(int i){ numStones = i; }

    protected void drawStones(Graphics2D g2, int iconWidth, int iconHeight, int numStones, Formatter myFormat){

        Stroke defaultStroke = g2.getStroke();

        //draw the background boxes
        Rectangle2D.Double background = new Rectangle2D.Double(0,0,iconWidth,iconHeight);
        g2.setColor(myFormat.getColor());
        g2.fill(background);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(7.0f));
        g2.draw(background);
        g2.setStroke(defaultStroke);

        //draw the stones inside the box
        for(int i = 0; i < numStones; i++){
            //decided to make it work with just circular stones first
            //will let the shape of the stone be determined by the formatter later

            double stoneWidth = myFormat.getStoneWidth();
            //algorithmically calculate the x position based on the width of the icon and width of the stone
            double tempX = iconWidth /(double) 5 + (i%2)* ((double)3*iconWidth/(double)5 - stoneWidth);
            double tempY = ( Math.floor(i/2) / Math.ceil( numStones /(double)2) )* (iconHeight - 2 * stoneWidth) + stoneWidth;

            //Ellipse2D.Double tempStone = new Ellipse2D.Double(tempX, tempY,stoneWidth,stoneWidth);
            StoneShape tempStone = myFormat.getShape().cloneAt(tempX,tempY);
            //g2.fill(tempStone);
            tempStone.fill(g2);
            g2.setColor(Color.RED);
            //g2.draw(tempStone);
            tempStone.draw(g2);
            g2.setColor(Color.BLACK);
        }
    }
}
