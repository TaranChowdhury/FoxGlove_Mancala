import javax.swing.*;
import java.awt.*;

public class HoleButton extends JButton {

    private int width;
    private int height;
    private Formatter myFormat;
    private HoleIcon icon;

    public HoleButton(int w, int h, Formatter f){
        width = w; height = h; myFormat = f;
        setPreferredSize(new Dimension(width,height));
        icon = new HoleIcon(width, height, myFormat);
        this.setIcon(icon);

    }

    public void setNumStones(int n){
        icon.setNumStones(n);
    }

    private class HoleIcon extends StoneHolder implements Icon{

        private int iconWidth;
        private int iconHeight;
        private Formatter myFormat;

        public HoleIcon(int w, int h, Formatter f){
            super(0);
            iconWidth = w; iconHeight = h; myFormat = f;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            //paints the number of stones on top of the button
            Graphics2D g2 = (Graphics2D) g;
            drawStones(g2,getIconWidth(),getIconHeight(),numStones,myFormat);
        }

        @Override
        public int getIconWidth() {
            return iconWidth;
        }

        @Override
        public int getIconHeight() {
            return iconHeight;
        }
    }

}
