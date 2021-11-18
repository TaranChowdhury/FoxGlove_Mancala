import java.awt.geom.Path2D;

public class ConcreteStar extends CloneableShape{


    public ConcreteStar(int width, double x, double y) {
        super(width, x, y);
        Path2D.Double tempPath = new Path2D.Double();
        tempPath.moveTo(x + (double)width/(double)2,y + 0);
        tempPath.lineTo(x + width,y+ width);
        tempPath.lineTo(x + 0, y+ (double) width - (double)width*Math.tan(0.628319));
        tempPath.lineTo(x + width,y+ (double) width - (double)width*Math.tan(0.628319));
        tempPath.lineTo(x + 0,y+ width);
        super.addShape(tempPath);
    }

    @Override
    public StoneShape cloneAt(double x, double y) {
        return new ConcreteStar(this.width,x,y);
    }
}
