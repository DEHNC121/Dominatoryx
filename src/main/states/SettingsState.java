package main.states;

import main.GamePanel;
import main.graphics.DrawButton;
import main.graphics.NamedScrollField;
import main.graphics.DrawText;
import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static main.states.GameMapSize.*;

public class SettingsState extends GameState{

    public static Map<GameMapSize,Point> allowedNumbers;
    public static ArrayList<String> sizes;

    private NamedScrollField namedScrollField;
    static public ArrayList<GameStyle> styles=new ArrayList<>();
    static {
        styles.add(new GameStyle(new ArrayList<>(Arrays.asList(
                new Color(2, 23, 52),
                new Color(4, 52, 72),
                new Color(14, 101, 98),
                new Color(202, 180, 129),
                new Color(230, 221, 205)
        ))));
        styles.add(new GameStyle(new ArrayList<>(Arrays.asList(
                new Color(62, 32, 79),
                new Color(90, 69, 101),
                new Color(206, 201, 214),
                new Color(226, 219, 233),
                new Color(188, 174, 204)
        ))));
        styles.add(new GameStyle(new ArrayList<>(Arrays.asList(
                new Color(46, 109, 92),
                new Color(61, 144, 121),
                new Color(71, 169, 142),
                new Color(83, 199, 167),
                new Color(94, 225, 189)
        ))));
    }

    ArrayList<DrawText> textFields;

    void init(){
        allowedNumbers = new HashMap<>();
        sizes = new ArrayList<>(Arrays.asList("Default","Purple","Forest"));
    }

    public SettingsState (GameStateManager gsm) {
        super(gsm);
        init();
        textFields=new ArrayList<>();
        DrawText.setFontName("OpenSans");
        DrawText temp;

        textFields.add(
                new DrawButton("BACK",
                        new Rectangle((int)(GamePanel.width*0.01), 0, (int) (GamePanel.width*0.21),(int)(GamePanel.height*0.161)),
                        1f,
                        GameStyle.PALETTE.UPFRONT,0.22f));

        textFields.add(
                new DrawButton("SAVE",
                        new Rectangle(0, (int) (GamePanel.height*0.79), (int) GamePanel.width, (int)(GamePanel.height*0.2)),
                        1f,
                        GameStyle.PALETTE.UPFRONT,0.4f));

        namedScrollField=new NamedScrollField("Style:",
                new Rectangle(0, (int) (GamePanel.height * 0.175f),(int) (GamePanel.width*0.8),(int) (GamePanel.height * 0.2f)),
                sizes,
                GameStyle.PALETTE.FRONT,
                GameStyle.PALETTE.UPFRONT,
                3,
                1f,
                3);

    }

    public GameStyle mapSize (String s) {
        return switch (s) {
            case "Default" -> styles.get(0);
            case "Purple" -> styles.get(1);
            case "Forest" -> styles.get(2);
            default -> styles.get(0);
        };
    }


    void updateState(GameStyle gameStyle){
        gsm.setStyle(gameStyle);
    }

    @Override
    public void update() {
        namedScrollField.update();
        updateState(mapSize(namedScrollField.getFieldState()));

        for (int i=0;i<textFields.size();i++){

            if (((DrawButton)textFields.get(i)).mouseClick==2) {
                switch (i) {
                    case 0 -> {gsm.setStyle(GameStyle.loadOrDefault()); gsm.set(GameStateManager.STATES.MENU);}
                    case 1 ->  {gsm.saveStyle(); gsm.set(GameStateManager.STATES.MENU);}
                    default -> System.out.println("No function added");
                }

                ((DrawButton)textFields.get(i)).mouseClick=-1;
            }
        }

        for (DrawText dt:textFields){
            dt.update();
        }
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        namedScrollField.input(mouse,key);

        for (DrawText dt:textFields){
            if (dt instanceof DrawButton){
                ((DrawButton)dt).input(mouse);
            }
        }
        mouse.setRotation(0);
    }

    @Override
    public void render(Graphics2D g) {
        namedScrollField.render(g);
        for (DrawText dt:textFields){
            dt.render(g);
        }
    }

}
