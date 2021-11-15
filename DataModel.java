import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DataModel {
	private ArrayList<Integer> stateA;
	private ArrayList<Integer> stateB;
	private Boolean turnOver;
	private ArrayList<ChangeListener> boardReference;

	private enum currentPlayer {
		player1, player2;
	}

	// need to figure out how to configure and add data
	// stateA and stateB
	public DataModel() {
		stateA = new ArrayList<Integer>();
		stateB = new ArrayList<Integer>();
		turnOver = false;
		boardReference = new ArrayList<ChangeListener>();
	}

	/**
	 * Attach a listener to the Model
	 * 
	 * @param cL the listener
	 */
	public void attach(ChangeListener cl) {
		boardReference.add(cl);
	}

	/**
	 * Constructs a DataModel object
	 * 
	 * @return the data in an ArrayList
	 */
	public ArrayList<Integer> getData() {
		return (ArrayList<Integer>) (stateB.clone());
	}

	/**
	 * Add data to the model
	 * 
	 * @param s the String to be added
	 */
	public void update() {
		// Code to update data.
		// still need to work on this.
		for (ChangeListener listener : boardReference) {
			listener.stateChanged(new ChangeEvent(this));
		}
	}

}
