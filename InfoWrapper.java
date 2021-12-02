package Model;

public class InfoWrapper {
    private PlayerInfo p1;
    private PlayerInfo p2;

    public InfoWrapper(PlayerInfo p1, PlayerInfo p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public PlayerInfo getP1(){ return p1; }
    public PlayerInfo getP2(){ return p2; }
}

