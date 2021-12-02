package View;

import Model.DataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HoleButton extends JButton {

    private int width;
    private int height;
    private Formatter myFormat;
    private DataModel myModel;
    private HoleIcon icon;

    private int index;
    private int playerNum;

    public HoleButton(int w, int h, Formatter f, DataModel dm, int i, int pNum){
        width = w; height = h; myFormat = f; myModel = dm; index = i; playerNum = pNum;
        setPreferredSize(new Dimension(width,height));
        icon = new HoleIcon(width, height);
        this.setIcon(icon);
        //this.addActionListener(new HoleButtonListener());

    }

    public void setNumStones(int n){
        icon.setNumStones(n);
    }
    public void setMyFormat(Formatter f){ myFormat = f; }
    public void setIsActive(boolean flag){
        removeAllActionListeners();
        if (flag){
            this.addActionListener(new HoleButtonListener());
        }
    }

    private void removeAllActionListeners(){
        ActionListener[] tempArray = this.getActionListeners();
        if(tempArray != null) {
            for (int i = 0; i < tempArray.length; i++) {
                this.removeActionListener(tempArray[i]);
            }
        }
    }

    private class HoleIcon extends StoneHolder implements Icon{

        private int iconWidth;
        private int iconHeight;


        public HoleIcon(int w, int h){
            super(0);
            iconWidth = w; iconHeight = h;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            //paints the number of stones on top of the button
            Graphics2D g2 = (Graphics2D) g;
            drawStones(g2,getIconWidth(),getIconHeight(),numStones,HoleButton.this.myFormat);
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

    private class HoleButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //myModel.addStoneTest(playerNum, index);
            myModel.userChoice(index,playerNum);
        }
    }

}
