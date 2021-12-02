package Model;

import java.util.ArrayList;
import View.Formatter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DataModel {
    //private ArrayList<Integer> stateA;
    //private ArrayList<Integer> stateB;

    private PlayerInfo player1;
    private PlayerInfo player2;

    private Boolean turnOver;
    private ArrayList<ChangeListener> myListeners;

    private int startingNumStones;
    private Formatter selectedFormat;

    private boolean isFormatFrameVisible;
    private boolean isFormatFrameClosed;

    private boolean isPrimaryFrameVisible;
    private boolean isPrimaryFrameClosed;

    private boolean isStoneNumFrameVisible;
    private boolean isStoneNumFrameClosed;

    private enum currentPlayer {
        player1, player2;
    }

    // need to figure out how to configure and add data
    // stateA and stateB
    public DataModel() {
        //stateA = new ArrayList<Integer>();
        //stateB = new ArrayList<Integer>();

        player1 = new PlayerInfo(1);
        player2 = new PlayerInfo(2);

        turnOver = false;
        myListeners = new ArrayList<ChangeListener>();


        isFormatFrameVisible = true;
        isFormatFrameClosed = false;

        isPrimaryFrameVisible = false;
        isPrimaryFrameClosed = false;

        isStoneNumFrameVisible = false;
        isStoneNumFrameClosed = false;
    }

    public void setSelectedFormat(Formatter sF){
        selectedFormat = sF;
    }

    public Formatter getSelectedFormat(){return selectedFormat; }

    public int getStartingNumStones() { return startingNumStones; }
    public void setStartingNumStones(int startingNum){
        this.startingNumStones = startingNum;
        player1.setAllValues(startingNumStones);
        player2.setAllValues(startingNumStones);
    }

    public void addStoneTest(int playerNum, int holeIndex){
        if(playerNum == 1){
            player1.setHoleValue(holeIndex, player1.getHoleValue(holeIndex) + 1);
            player1.setMancalaValue(player1.getMancala() + 1);
        } else if (playerNum == 2){
            player2.setHoleValue(holeIndex, player2.getHoleValue(holeIndex) + 1);
            player2.setMancalaValue(player2.getMancala() + 1);
        }
        notifyListeners();
    }

    public InfoWrapper getPlayerInfo(){ return new InfoWrapper(player1,player2); }

    public boolean isFormatFrameVisible() {return isFormatFrameVisible;}
    public void setFormatFrameVisible(boolean formatFrameVisible) {isFormatFrameVisible = formatFrameVisible;}
    public boolean isFormatFrameClosed() {return isFormatFrameClosed;}
    public void setFormatFrameClosed(boolean formatFrameClosed) {
        isFormatFrameClosed = formatFrameClosed;
        notifyListeners();
    }

    public boolean isPrimaryFrameVisible() {return isPrimaryFrameVisible;}
    public void setPrimaryFrameVisible(boolean primaryFrameVisible) {
        isPrimaryFrameVisible = primaryFrameVisible;
        //values of the two player info objects have already been initialized
        //state changed in primary frame will use accessors in datamodel to get
        //the values associated with each hole
        notifyListeners();
    }
    public boolean isPrimaryFrameClosed() {return isPrimaryFrameClosed;}
    public void setPrimaryFrameClosed(boolean primaryFrameClosed) {
        isPrimaryFrameClosed = primaryFrameClosed;
        notifyListeners();
    }

    public boolean isStoneNumFrameVisible() {return isStoneNumFrameVisible;}
    public void setStoneNumFrameVisible(boolean stoneNumFrameVisible) {
        isStoneNumFrameVisible = stoneNumFrameVisible;
        notifyListeners();
    }
    public boolean isStoneNumFrameClosed() {return isStoneNumFrameClosed;}
    public void setStoneNumFrameClosed(boolean stoneNumFrameClosed) {
        isStoneNumFrameClosed = stoneNumFrameClosed;
        notifyListeners();
    }

    /**
     * Attach a listener to the Model
     *
     * @param cl the listener
     */
    public void attach(ChangeListener cl) {
        myListeners.add(cl);
    }

    private void notifyListeners(){
        for(ChangeListener cl : myListeners){
            cl.stateChanged(new ChangeEvent(this));
        }
    }


}
