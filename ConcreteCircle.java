import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class ConcreteCircle extends CloneableShape{
    public ConcreteCircle(int width, double x, double y){
        super(width,x,y);
        super.addShape(new Ellipse2D.Double(x,y,width,width));
    }

    @Override
    public StoneShape cloneAt(double x, double y) {
        return new ConcreteCircle(this.width,x,y);
    }
}
