package Model;

import java.util.ArrayList;
import View.Formatter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DataModel {
    //private ArrayList<Integer> stateA;
    //private ArrayList<Integer> stateB;

    private PlayerInfo player1Committed;
    private PlayerInfo player2Committed;

    private PlayerInfo player1InProgress;
    private PlayerInfo player2InProgress;

    private int currentPlayerNum;
    private ArrayList<ChangeListener> myListeners;

    private int startingNumStones;
    private Formatter selectedFormat;

    private boolean isFormatFrameVisible;
    private boolean isFormatFrameClosed;

    private boolean isPrimaryFrameVisible;
    private boolean isPrimaryFrameClosed;

    private boolean isStoneNumFrameVisible;
    private boolean isStoneNumFrameClosed;

    private String message;

    private boolean continueIsActive;
    private boolean undoIsActive;

    private enum Player {
        player1, player2
    }

    // need to figure out how to configure and add data
    // stateA and stateB
    public DataModel() {
        //stateA = new ArrayList<Integer>();
        //stateB = new ArrayList<Integer>();

        player1Committed = new PlayerInfo(1);
        player2Committed = new PlayerInfo(2);

        player1InProgress = new PlayerInfo(1);
        player2InProgress = new PlayerInfo(2);

        myListeners = new ArrayList<ChangeListener>();

        message = "default message";

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
        player1InProgress.setAllValues(startingNumStones);
        player2InProgress.setAllValues(startingNumStones);

        player1Committed.setAllValues(startingNumStones);
        player2Committed.setAllValues(startingNumStones);
    }

    public void addStoneTest(int playerNum, int holeIndex){
        if(playerNum == 1){
            player1InProgress.setHoleValue(holeIndex, player1InProgress.getHoleValue(holeIndex) + 1);
            player1InProgress.setMancalaValue(player1InProgress.getMancala() + 1);
        } else if (playerNum == 2){
            player2InProgress.setHoleValue(holeIndex, player2InProgress.getHoleValue(holeIndex) + 1);
            player2InProgress.setMancalaValue(player2InProgress.getMancala() + 1);
        }
        notifyListeners();
    }

    public InfoWrapper getPlayerInfo(){ return new InfoWrapper(player1InProgress,player2InProgress); }

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

    public String getMessage() {
        return message;
    }

    public boolean isContinueIsActive() {
        return continueIsActive;
    }

    public void setContinueIsActive(boolean continueIsActive) {
        this.continueIsActive = continueIsActive;
        notifyListeners();
    }

    public boolean isUndoIsActive() {
        return undoIsActive;
    }

    public void setUndoIsActive(boolean undoIsActive) {
        this.undoIsActive = undoIsActive;
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

    public void startTurn(int playerNum) {
        //player1.setActive(true);
        currentPlayerNum = playerNum;
        message = "player number: " + currentPlayerNum + " please choose a hole";
        if(playerNum == 1){
            player1InProgress.setActive(true);
        }else {
            player2InProgress.setActive(true);
        }
        //add check for six pits are empty
        notifyListeners();
    }

    public void userChoice(int index, int playerNum) {
        distributeStones(index, playerNum);
        message = "This is how the result would look. Would you like to continue? If not please undo.";
        continueIsActive = true;
        undoIsActive = true;
        player1InProgress.setActive(false);
        player2InProgress.setActive(false);
        notifyListeners();

    }

    public void continueTurn(){
        //update values
        player1Committed.shallowAssignment(player1InProgress);
        player2Committed.shallowAssignment(player2InProgress);
        //make continue button inert
        continueIsActive = false;
        undoIsActive = false;

        //check for last stone in mancala
        //check for stones run out in empty hole

        if (currentPlayerNum == 1){
            startTurn(2);
        } else {
            startTurn(1);
        }
    }

    public void undoTurn(){
        player1InProgress.shallowAssignment(player1Committed);
        player2InProgress.shallowAssignment(player2Committed);

        continueIsActive = false;
        undoIsActive = false;

        if(currentPlayerNum == 1){
            player1InProgress.setActive(true);
            player2InProgress.setActive(false);
        } else {
            player1InProgress.setActive(false);
            player2InProgress.setActive(true);
        }

        message = "player num: " + currentPlayerNum + "redo your turn";
        notifyListeners();
    }

    public void distributeStones(int index, int playerNum) {
        if (playerNum == 1) {
            int startingNumStones = player1InProgress.getHoleValue(index);
            player1InProgress.setHoleValue(index, 0);
            if (startingNumStones == 0) {
                return;
            }
            int counterTemp = startingNumStones;
            while (counterTemp > 0) {
                for (int i = index + 1; i < 6; i++) {
                    if(counterTemp > 0){
                        player1InProgress.setHoleValue(i, player1InProgress.getHoleValue(i) + 1);
                        counterTemp--;
                    }
                }
                if (counterTemp > 0) {
                    player1InProgress.setMancalaValue(player1InProgress.getMancala() + 1);
                    counterTemp--;
                }
                if (counterTemp > 0) {
                    for (int i = 0; i < 6; i++) {
                        if (counterTemp > 0){
                            player2InProgress.setHoleValue(i, player2InProgress.getHoleValue(i) + 1);
                            counterTemp--;
                        }
                    }
                }
                if (counterTemp > 0) {
                    index = -1;
                    //this is hella hackey but gotta do it because the first
                    //loop skips to the next hole immediately
                    //this offsets the
                }

            }

        } else if (playerNum == 2) {
            int startingNumStones = player2InProgress.getHoleValue(index);
            player2InProgress.setHoleValue(index, 0);
            if (startingNumStones == 0) {
                return;
            }
            int counterTemp = startingNumStones;
            while (counterTemp > 0) {
                for (int i = index + 1; i < 6; i++) {
                    if(counterTemp > 0){
                        player2InProgress.setHoleValue(i, player2InProgress.getHoleValue(i) + 1);
                        counterTemp--;
                    }
                }
                if (startingNumStones > 0) {
                    if(counterTemp > 0){
                        player2InProgress.setMancalaValue(player2InProgress.getMancala() + 1);
                        counterTemp--;
                    }
                }
                if (startingNumStones > 0) {
                    for (int i = 0; i < 6; i++) {
                        if(counterTemp > 0){
                            player1InProgress.setHoleValue(i, player1InProgress.getHoleValue(i) + 1);
                            counterTemp--;
                        }
                    }
                }
                if (startingNumStones > 0) {
                    index = -1;
                    //this is hella hackey but gotta do it because the first
                    //loop skips to the next hole immediately
                    //this offsets the
                }

            }
        }
    }

}