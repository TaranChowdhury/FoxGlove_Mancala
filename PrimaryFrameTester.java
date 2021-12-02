import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PrimaryFrameTester {

    public static void main(String[] args){

        DataModel temp = new DataModel();
        //Formatter myFormatter = new Formatter(Color.BLUE,new Rectangle2D.Double(),30);
        Formatter stoneShapeFormatter = new Formatter(Color.BLUE, new ConcreteCircle(30,0,0),30);
        Formatter starShapeFormatter = new Formatter(Color.GREEN, new ConcreteStar(30,0,0),30);

        PrimaryFrame myPF = new PrimaryFrame(temp,stoneShapeFormatter);
        //PrimaryFrame myPF = new PrimaryFrame(temp,starShapeFormatter);

        myPF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myPF.setVisible(true);
        myPF.pack();

    }


}
