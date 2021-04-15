package main.util;

public class RoundManager {
    static Player[] players;
    static int currentPlayer;
    static int roundCnt;
    public RoundManager(int count){
        currentPlayer=0;
        roundCnt=1;
        players=new Player[count];
        for(int i=0;i<count;i++)
            players[i]=new Player(i+1);
    }
    static public void newTurnActions(){
        //mostly for actions, that are good when iterating on WorldMap
        // - income, cleaning players from defeated, etc
        roundCnt++;
        System.out.println("New round: "+roundCnt);
    }
    static public void postTurnActions(Player pl){
        System.out.println("Ending Player "+pl.toString());
    }
    static public void preTurnActions(Player pl){
        System.out.println("Starting Player "+pl.toString());
    }
    static public void passTurn(){
        postTurnActions(players[currentPlayer]);
        do{
            currentPlayer++;
            if(currentPlayer>=players.length){
                currentPlayer=0;
                newTurnActions();
            }
            preTurnActions(players[currentPlayer]);
        }while (!players[currentPlayer].isDefeated);


    }
    public static void input(MouseHandler mouseHandler,KeyHandler keyHandler){
        players[currentPlayer].input(mouseHandler,keyHandler);
    }
    public static void update(){
        players[currentPlayer].update();
    }
}
