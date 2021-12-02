package StoneDisplay;

import java.awt.*;

public interface StoneShape {
    public StoneShape cloneAt(double x, double y);
    public void draw(Graphics2D g2);
    public void fill(Graphics2D g2);
}
