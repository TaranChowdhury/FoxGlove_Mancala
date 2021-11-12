import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PrimaryFrameTester {

    public static void main(String[] args){

        DataModel temp = new DataModel();
        Formatter myFormatter = new Formatter(Color.BLUE,new Rectangle2D.Double(),30);

        PrimaryFrame myPF = new PrimaryFrame(temp,myFormatter);

        myPF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myPF.setVisible(true);
        myPF.pack();

    }


}
