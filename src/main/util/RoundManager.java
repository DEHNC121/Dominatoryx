package main.util;

import main.util.events.Event;
import main.util.map.Hexagon2D;
import main.util.map.WorldMap;
import main.util.saveLoad.DataRoundManager;

import java.util.Stack;

public class RoundManager {
    public static Player[] players;
    static public int currentPlayer;
    static public int roundCnt;
    public static Stack<Event> events;
    public RoundManager(int count, int aiNumber){
        currentPlayer=0;
        roundCnt=1;
        players=new Player[count];
        events = new Stack<>();
        for(int i=0;i<count;i++) {
            if (i < aiNumber)
                players[i] = new AI(i+1);
            else
                players[i] = new Player(i + 1);
        }
        //checkAI();
    }

    static public void newTurnActions(){
        //mostly for actions, that are good when iterating on WorldMap
        // - income, cleaning players from defeated, etc
        roundCnt++;
        System.out.println("New round: "+roundCnt);
        int defeatedPlayers=getDefeatedPlayers();
        if(defeatedPlayers>0){
            Player[] newPlayers=new Player[players.length-defeatedPlayers];
            int i=0;
            for(Player pl: players){
                if(!pl.deathCheck()){
                    newPlayers[i]=pl;
                    i++;
                }
            }
            players=newPlayers;
        }
        WorldMap.generateIncome();
        WorldMap.refreshAndRegen();
    }

    static public Player getCurrentPlayer(){return players[currentPlayer];}

    static public void postTurnActions(Player pl){
        WorldMap.selectedHexagon=null;
        System.out.println("Ending Player "+pl.toString());
    }

    static public void preTurnActions(Player pl){
        pl.refreshAndRegenUnit();
        System.out.println("Starting Player "+pl.toString());
    }

    static public void passTurn(){
        events.clear();
        postTurnActions(players[currentPlayer]);
        do{
            currentPlayer++;
            if(currentPlayer>=players.length){
                currentPlayer=0;
                newTurnActions();
            }
            preTurnActions(players[currentPlayer]);
        }while (players[currentPlayer].deathCheck());

        checkAI();
    }

    public static void checkAI () {
        System.out.println("siema");
        if (players[currentPlayer] instanceof AI) {
            System.out.println("AI");
            ((AI) players[currentPlayer]).play();
        }
    }


    public static void goBack() {
        if (!events.isEmpty()) {
            events.pop().goBack();
        }
    }
    public void buyUnit(Hexagon2D hexagon2D,int id){
        players[currentPlayer].buyUnit(hexagon2D,id);
    }

    public static int getDefeatedPlayers(){
        int i=0;
        for(Player pl:players){
            if(pl.deathCheck())
                i++;
        }
        return i;
    }
    public static void input(MouseHandler mouseHandler,KeyHandler keyHandler){
        players[currentPlayer].input(mouseHandler,keyHandler);
    }
    public static void update(){

        players[currentPlayer].update();
    }
    static public Player getFromColour(int col){
        for(Player pl:players){
            if(pl.getColour()==col)
                return pl;
        }
        return null;
    }
    static public void loadData(DataRoundManager drm){
        players=new Player[drm.players.length];
        currentPlayer=drm.current;
        roundCnt=drm.roundCount;
        for(int i=0;i<players.length;i++){
            players[i]= new Player(drm.players[i]);
        }
        events=new Stack<>();
    }
}
