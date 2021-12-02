import Model.DataModel;
import StoneDisplay.StoneShape;
import View.Formatter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import View.FormatButton;


public class FormatFrame extends JFrame implements ChangeListener {

    private Formatter format1;
    private Formatter format2;
    private DataModel myModel;

    public FormatFrame(Formatter f1, Formatter f2, DataModel mDM){
        format1 = f1; format2 = f2; myModel = mDM;
        setLayout(new BorderLayout());

        JLabel textLabel = new JLabel("Please click on one of the format boxes");
        this.add(textLabel, BorderLayout.NORTH);

        JPanel formatPanel = new JPanel();
        formatPanel.setLayout(new FlowLayout());

        FormatButton fb1 = new FormatButton(format1,myModel);
        FormatButton fb2 = new FormatButton(format2,myModel);

        formatPanel.add(fb1);
        formatPanel.add(fb2);

        this.add(formatPanel,BorderLayout.SOUTH);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.setVisible(myModel.isFormatFrameVisible());
        this.repaint();

        if (myModel.isFormatFrameClosed()){
            this.dispose();
        }
    }
}

