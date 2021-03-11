package main.states;

import main.graphics.Font;
import main.GamePanel;
import main.graphics.Sprite;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.Vector2f;

import java.awt.*;

public class PlayState extends GameState
{
    private Font font;
    private static Font staticFont;
    //private static TileManager tm;
    private static GameStateManager gsm;

    private static String currentMessage;
    public static boolean do_id=false;

    //public static Camera cam;

    public static Vector2f map;


//    public static TileManager getTm() {
//        return tm;
//    }
//
//    public static void setTm(TileManager tm) {
//        PlayState.tm = tm;
//    }

    public static Font getStaticFont() {
        return staticFont;
    }

    public PlayState (GameStateManager gsm)
    {
        super(gsm);

        this.gsm=gsm;
        Vector2f.setWorldVar(map.x, map.y);
        //System.out.print(map.x+" "+map.y);

//        cam = new Camera (new AABB(new Vector2f (), GamePanel.width, GamePanel.height));
        //cam = new Camera (new AABB (new Vector2f (), 900, 600));


//        MessageBlock.count=0;
//
//        tm = new TileManager ("tile/Map.xml", cam);
        font = new Font ("font/font.png", 10, 10);
        staticFont=new Font ("font/font.png", 10, 10);
//        message=new Message();
        currentMessage=null;
    }



    @Override
    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
//        cam.update ();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
    }
    private int plus=0;
    private int keys=0;
    private int gold=0;
    private int add=0;
    private int i=0;
    private int j=0;
    @Override
    public void render(Graphics2D g)
    {
//        tm.render(g);
        StringBuilder sb=new StringBuilder();

//        cam.render (g);
        if(currentMessage!=null) print(currentMessage,g);
        currentMessage=null;
    }
    public static void notify(String s){
        currentMessage=s;
    }
    public static void print(String s, Graphics2D g){
        Sprite.drawArray(g, staticFont, s, new Vector2f(GamePanel.width/2-(s.length()*10) , GamePanel.height-50), 25, 35, 20, 0);
    }
    public static void pause(){
//        gsm.set(new PauseState(gsm, map, tm, player));
    }
}

