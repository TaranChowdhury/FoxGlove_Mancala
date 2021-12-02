import Model.DataModel;
import View.Formatter;
import View.MancalaIcon;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoneNumFrame extends JFrame implements ChangeListener{

    private Formatter myFormat;
    private DataModel myData;
    private MancalaIcon stoneNumDisplay;
    private JLabel displayLabel;
    private int sliderValue;

    private static final int ICON_WIDTH = 250;

    public StoneNumFrame(Formatter mF, DataModel mD){
        myFormat = mF; myData = mD;
        stoneNumDisplay = new MancalaIcon(ICON_WIDTH,ICON_WIDTH,myFormat);
        stoneNumDisplay.setNumStones(4);
        sliderValue = 4;

        displayLabel = new JLabel();
        displayLabel.setIcon(stoneNumDisplay);

        setLayout(new BorderLayout());
        setResizable(false);

        JSlider stoneNumSlider = new JSlider(JSlider.HORIZONTAL,2,5,4);
        stoneNumSlider.setMajorTickSpacing(1);
        stoneNumSlider.setPaintLabels(true);
        stoneNumSlider.setPaintTicks(true);
        stoneNumSlider.addChangeListener(new mySliderListener());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(new JLabel("Please select the number of stones"));
        JButton chooseButton = new JButton("choose");
        chooseButton.addActionListener(new ChooseButtonListener());
        topPanel.add(chooseButton);

        add(topPanel,BorderLayout.NORTH);
        add(displayLabel,BorderLayout.CENTER);
        add(stoneNumSlider,BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.pack();


    }

    private void propagateChanges(){
        stoneNumDisplay.setMyFormatter(myFormat);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        myFormat = myData.getSelectedFormat();
        this.setVisible(myData.isStoneNumFrameVisible());
        propagateChanges();
        this.repaint();

        if(myData.isStoneNumFrameClosed()){
            this.dispose();
        }
    }

    private class mySliderListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider tempS = (JSlider) e.getSource();
            if(!tempS.getValueIsAdjusting()){
                StoneNumFrame.this.sliderValue = tempS.getValue();
                StoneNumFrame.this.stoneNumDisplay.setNumStones(tempS.getValue());
                StoneNumFrame.this.displayLabel.repaint();
            }



        }
    }

    private class ChooseButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //StoneNumFrame.this.setVisible(false);
            //myData.setNumStones(StoneNumFrame.this.sliderValue);
            myData.setStartingNumStones(sliderValue);
            myData.setStoneNumFrameVisible(false);
            myData.setStoneNumFrameClosed(true);
            myData.setPrimaryFrameVisible(true);
        }
    }
}

