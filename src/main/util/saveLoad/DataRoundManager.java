package main.util.saveLoad;

import main.util.AI;
import main.util.RoundManager;

public class DataRoundManager {
    public DataPlayer[] players=null;
    public DataAI[] dataAIS=null;
    public boolean[] isAI=null;
    public int current=0;
    public int roundCount=0;
    DataRoundManager(){ }
    static public DataRoundManager save(){
        DataRoundManager out=new DataRoundManager();
        out.current=RoundManager.currentPlayer;
        out.roundCount=RoundManager.roundCnt;
        out.players=new DataPlayer[RoundManager.players.length];
        out.isAI=new boolean[RoundManager.players.length];
        out.dataAIS=new DataAI[RoundManager.players.length];
        for(int i=0;i< out.players.length;i++){
            out.players[i]=new DataPlayer(RoundManager.players[i]);
            out.isAI[i]=(RoundManager.players[i] instanceof AI);
            if(RoundManager.players[i] instanceof AI){
                out.dataAIS[i]=DataAI.save((AI) RoundManager.players[i]);
            }
            else{
                out.dataAIS[i]=null;
            }
        }
        return out;
    }
}
