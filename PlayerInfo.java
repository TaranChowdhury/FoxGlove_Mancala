package Model;

import java.util.ArrayList;

public class PlayerInfo {

    private ArrayList<Integer> row;
    private Integer mancala;
    private boolean isActive;
    private int playerNumber;

    public PlayerInfo(int pNum){
        playerNumber = pNum;
        row = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            row.add(i);
        }
        mancala = 0;
        isActive = false;
    }

    public Integer getMancala(){
        return mancala;
    }

    public Integer getHoleValue(int index){
        return row.get(index);
    }

    public boolean getIsActive(){
        return isActive;
    }

    public int getPlayerNumber(){ return playerNumber; }

    public void setHoleValue(int index, Integer value){
        row.set(index,value);
    }

    public void setMancalaValue(Integer value){
        mancala = value;
    }

    public void setActive(boolean flag){
        isActive = flag;
    }

    public void setAllValues(Integer startingValue){
        for(int i = 0; i < 6; i++){
            row.set(i,startingValue);
        }
    }

    public void shallowAssignment(PlayerInfo pOther){
        for(int i = 0; i < 6; i++){
            row.set(i,pOther.getHoleValue(i));
        }
        this.mancala = pOther.getMancala();
        this.isActive = pOther.getIsActive();
        this.playerNumber = pOther.getPlayerNumber();
    }


}

