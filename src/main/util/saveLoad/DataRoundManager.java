package main.util.saveLoad;

import main.util.RoundManager;

public class DataRoundManager {
    public DataPlayer[] players=null;
    public int current=0;
    public int roundCount=0;
    DataRoundManager(){ }
    static public DataRoundManager save(){
        DataRoundManager out=new DataRoundManager();
        out.current=RoundManager.currentPlayer;
        out.roundCount=RoundManager.roundCnt;
        out.players=new DataPlayer[RoundManager.players.length];
        for(int i=0;i< out.players.length;i++){
            out.players[i]=new DataPlayer(RoundManager.players[i]);
        }
        return out;
    }
}
