package main.util;

import main.util.events.Event;
import main.util.map.Hexagon2D;
import main.util.map.WorldMap;

import java.util.Stack;

public class RoundManager {
    public static Player[] players;
    static int currentPlayer;
    static int roundCnt;
    public static Stack<Event> events;
    public RoundManager(int count){
        currentPlayer=0;
        roundCnt=1;
        players=new Player[count];
        events = new Stack<>();
        for(int i=0;i<count;i++)
            players[i]=new Player(i+1);
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
}
