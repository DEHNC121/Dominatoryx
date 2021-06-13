package main.util.saveLoad;

import main.util.AI;

public class DataAI {
    public int nOfHexagons;
    public boolean attackMode;
    public int attackModeRounds;
    public boolean returnFlag;
    public DataAI(){}
    static public DataAI save(AI ai){
        DataAI out=new DataAI();
        out.nOfHexagons=ai.nOfHexagons;
        out.attackMode=ai.attackMode;
        out.attackModeRounds=ai.attackModeRounds;
        out.returnFlag=ai.returnFlag;
        return out;
    }
}
