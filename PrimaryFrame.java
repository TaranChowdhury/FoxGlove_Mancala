import javax.swing.*;
import java.awt.*;

public class PrimaryFrame extends JFrame {

    public static final int BOARD_WIDTH = 1000;
    public static final int BOARD_HEIGHT = 250;

    private DataModel myData;
    private Formatter myFormat;

    public PrimaryFrame(DataModel model, Formatter format){

        myData = model;
        myFormat = format;

        //create the right and left mancala labels and icons
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new BorderLayout());

        JLabel leftLabel = new JLabel();
        JLabel rightLabel = new JLabel();

        MancalaIcon leftMancala = new MancalaIcon(BOARD_WIDTH/8,BOARD_HEIGHT,myFormat);
        MancalaIcon rightMancala = new MancalaIcon(BOARD_WIDTH/8,BOARD_HEIGHT,myFormat);

        leftLabel.setIcon(leftMancala);
        rightLabel.setIcon(rightMancala);

        boardPanel.add(leftLabel,BorderLayout.WEST);
        boardPanel.add(rightLabel,BorderLayout.EAST);

        //create the center buttons/ holes
        JPanel holePanel = new JPanel();
        holePanel.setLayout(new GridLayout(2,6));
        holePanel.setBackground(format.getColor());

        for(int i = 0; i < 12; i++){
            //JButton tempHoleButton = new JButton(String.valueOf(i));
            HoleButton tempHoleButton = new HoleButton(BOARD_WIDTH/8,BOARD_HEIGHT/2, myFormat);
            tempHoleButton.setNumStones(i);
            holePanel.add(tempHoleButton);
        }

        boardPanel.add(holePanel,BorderLayout.CENTER);



        this.add(boardPanel);

        this.setResizable(false);




        leftMancala.setNumStones(1);
        rightMancala.setNumStones(20);

    }



}
