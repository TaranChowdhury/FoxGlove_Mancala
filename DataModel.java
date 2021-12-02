package Model;

import java.util.ArrayList;
import View.Formatter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DataModel {
	// private ArrayList<Integer> stateA;
	// private ArrayList<Integer> stateB;

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

	private String message;

	private boolean continueIsActive;
	private boolean undoIsActive;

	private enum currentPlayer {
		player1, player2;
	}

	// need to figure out how to configure and add data
	// stateA and stateB
	public DataModel() {
		// stateA = new ArrayList<Integer>();
		// stateB = new ArrayList<Integer>();

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

		continueIsActive = false;
		undoIsActive = false;
	}

	public void setSelectedFormat(Formatter sF) {
		selectedFormat = sF;
	}

	public Formatter getSelectedFormat() {
		return selectedFormat;
	}

	public int getStartingNumStones() {
		return startingNumStones;
	}

	public void setStartingNumStones(int startingNum) {
		this.startingNumStones = startingNum;
		player1.setAllValues(startingNumStones);
		player2.setAllValues(startingNumStones);
	}

	public void addStoneTest(int playerNum, int holeIndex) {
		if (playerNum == 1) {
			player1.setHoleValue(holeIndex, player1.getHoleValue(holeIndex) + 1);
			player1.setMancalaValue(player1.getMancala() + 1);
		} else if (playerNum == 2) {
			player2.setHoleValue(holeIndex, player2.getHoleValue(holeIndex) + 1);
			player2.setMancalaValue(player2.getMancala() + 1);
		}
		notifyListeners();
	}

	public InfoWrapper getPlayerInfo() {
		return new InfoWrapper(player1, player2);
	}

	public boolean isFormatFrameVisible() {
		return isFormatFrameVisible;
	}

	public void setFormatFrameVisible(boolean formatFrameVisible) {
		isFormatFrameVisible = formatFrameVisible;
	}

	public boolean isFormatFrameClosed() {
		return isFormatFrameClosed;
	}

	public void setFormatFrameClosed(boolean formatFrameClosed) {
		isFormatFrameClosed = formatFrameClosed;
		notifyListeners();
	}

	public boolean isPrimaryFrameVisible() {
		return isPrimaryFrameVisible;
	}

	public void setPrimaryFrameVisible(boolean primaryFrameVisible) {
		isPrimaryFrameVisible = primaryFrameVisible;
		// values of the two player info objects have already been initialized
		// state changed in primary frame will use accessors in datamodel to get
		// the values associated with each hole
		notifyListeners();
	}

	public boolean isPrimaryFrameClosed() {
		return isPrimaryFrameClosed;
	}

	public void setPrimaryFrameClosed(boolean primaryFrameClosed) {
		isPrimaryFrameClosed = primaryFrameClosed;
		notifyListeners();
	}

	public boolean isStoneNumFrameVisible() {
		return isStoneNumFrameVisible;
	}

	public void setStoneNumFrameVisible(boolean stoneNumFrameVisible) {
		isStoneNumFrameVisible = stoneNumFrameVisible;
		notifyListeners();
	}

	public boolean isStoneNumFrameClosed() {
		return isStoneNumFrameClosed;
	}

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

	private void notifyListeners() {
		for (ChangeListener cl : myListeners) {
			cl.stateChanged(new ChangeEvent(this));
		}
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

	public void UserChoice(int index, int playerNum) {
		distributeStones(index, playerNum);
		message = "This is how the result would look. Would you like to continue? If not please undo.";
		continueIsActive = true;
		undoIsActive = false;
		notifyListeners();

	}

	public void distributeStones(int index, int playerNum) {
		if (playerNum == 1) {
			int startingNumStones = player1.getHoleValue(index);
			player1.setHoleValue(index, 0);
			if (startingNumStones == 0) {
				return;
			}
			while (startingNumStones != 0) {
				for (int i = index + 1; i < 6; i++) {
					player1.setHoleValue(i, player1.getHoleValue(i) + 1);
					startingNumStones--;
				}
				if (startingNumStones != 0) {
					player1.setMancalaValue(player1.getMancala() + 1);
					startingNumStones--;
				}
				if (startingNumStones != 0) {
					for (int i = 0; i < 6; i++) {
						player2.setHoleValue(i, player2.getHoleValue(i) + 1);
						startingNumStones--;
					}
				}
				if (startingNumStones != 0) {
					index = 0;
				}

			}

		} else if (playerNum == 2) {
			int startingNumStones = player2.getHoleValue(index);
			player2.setHoleValue(index, 0);
			if (startingNumStones == 0) {
				return;
			}
			while (startingNumStones != 0) {
				for (int i = index + 1; i < 6; i++) {
					player2.setHoleValue(i, player2.getHoleValue(i) + 1);
					startingNumStones--;
				}
				if (startingNumStones != 0) {
					player2.setMancalaValue(player2.getMancala() + 1);
					startingNumStones--;
				}
				if (startingNumStones != 0) {
					for (int i = 0; i < 6; i++) {
						player1.setHoleValue(i, player1.getHoleValue(i) + 1);
						startingNumStones--;
					}
				}
				if (startingNumStones != 0) {
					index = 0;
				}

			}
		}
	}

}
