import java.awt.*;
import java.awt.geom.Path2D;

public abstract class CloneableShape implements StoneShape{
    private Path2D.Double shapePath;
    private double xPos;
    private double yPos;
    protected int width;

    protected CloneableShape(int w, double x, double y){
        shapePath = new Path2D.Double();
        width = w; xPos = x; yPos = y;
    }


    protected void addShape(Shape s){
        shapePath.append(s,false);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.draw(shapePath);
    }

    @Override
    public void fill(Graphics2D g2) {
        g2.fill(shapePath);
    }
}
