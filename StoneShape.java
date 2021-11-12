import java.awt.*;

public interface StoneShape {
    public StoneShape cloneAt(int x, int y);
    public void draw(Graphics2D g2);
}
