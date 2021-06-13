package main.states;

import main.GamePanel;
import main.graphics.*;
import main.util.KeyHandler;
import main.util.MouseHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static main.states.GameMapSize.*;

public class CreateGameState extends GameState{

    public static Map<GameMapSize,Point> allowedNumbers;
    private GameMapSize state;
    public static ArrayList<String> sizes;

    private final ArrayList<NamedScrollField> namedScrollFields;



    ArrayList<DrawText> textFields;

    void init(){
        allowedNumbers = new HashMap<>();
        sizes = new ArrayList<>(Arrays.asList("S","M","L"));
        allowedNumbers.put(LARGE,
                new Point(0,8)
        );
        allowedNumbers.put(MEDIUM,
                new Point(0,6)

        );
        allowedNumbers.put(SMALL,
                new Point(0,4)

        );
    }

    public CreateGameState (GameStateManager gsm) {
        super(gsm);
        init();
        state=MEDIUM;
        textFields=new ArrayList<>();
        namedScrollFields=new ArrayList<>();
        DrawText.setFontName("OpenSans");
        DrawText temp;

        textFields.add(
                new DrawButton("BACK",
                        new Rectangle((int)(GamePanel.width*0.01), 0, (int) (GamePanel.width*0.21),(int)(GamePanel.height*0.161)),
                        1f,
                        GameStyle.PALETTE.UPFRONT,0.22f));

        textFields.add(
                new DrawButton("PLAY",
                        new Rectangle(0, (int) (GamePanel.height*0.79), (int) GamePanel.width, (int)(GamePanel.height*0.2)),
                        1f,
                        GameStyle.PALETTE.UPFRONT,0.4f));

        namedScrollFields.add(new NamedScrollField("Map size:",
                new Rectangle(0, (int) (GamePanel.height * 0.175f),(int) (GamePanel.width),(int) (GamePanel.height * 0.2f)),
                sizes,
                GameStyle.PALETTE.FRONT,
                GameStyle.PALETTE.UPFRONT,
                3,
                1f));

        temp=new DrawText(
                "Players:",
                new Rectangle(0, (int) (GamePanel.height * 0.5f),(int) (GamePanel.width*0.5),(int) (GamePanel.height * 0.15f)),
                1f,
                GameStyle.PALETTE.FRONT
        );
        namedScrollFields.add(new NamedScrollField(
                temp,
                new NumericScrollField(
                        new Rectangle(temp.getInRectangle().x+temp.getInRectangle().width, temp.getOutRectangle().y, (int)(temp.getOutRectangle().height*0.8), temp.getOutRectangle().height),
                        null,
                        GameStyle.PALETTE.UPFRONT,
                        3

                        )
                ));

        temp=new DrawText(
                "Computers:",
                new Rectangle((int) (GamePanel.width*0.47), (int) (GamePanel.height * 0.5f),(int) (GamePanel.width*0.5),(int) (GamePanel.height * 0.15f)),
                1f,
                GameStyle.PALETTE.FRONT
        );
        namedScrollFields.add(new NamedScrollField(
                temp,
                new NumericScrollField(
                        new Rectangle(temp.getInRectangle().x+temp.getInRectangle().width, temp.getOutRectangle().y, (int)(temp.getOutRectangle().height*0.8), temp.getOutRectangle().height),
                        null,
                        GameStyle.PALETTE.UPFRONT,
                        3

                )
        ));
    }

    public GameMapSize mapSize (String s) {
        return switch (s) {
            case "S" -> SMALL;
            case "M" -> MEDIUM;
            case "L" -> LARGE;
            default -> None;
        };
    }

    void updateState(GameMapSize gameMapSize){
        for (int i=1;i<namedScrollFields.size();i++){
            if(i==2){

                ((NumericScrollField)namedScrollFields.get(i).getScrollField()).setArray(allowedNumbers.get(gameMapSize).x,
                        Math.min(allowedNumbers.get(gameMapSize).y,Integer.parseInt(namedScrollFields.get(i-1).getScrollField().getMainField())));
            }else {
                ((NumericScrollField)namedScrollFields.get(i).getScrollField()).setArray(allowedNumbers.get(gameMapSize).x+1,allowedNumbers.get(gameMapSize).y);
            }
        }
    }

    @Override
    public void update() {
        for (NamedScrollField sf : namedScrollFields) {
            sf.update();
        }
        updateState(mapSize(namedScrollFields.get(0).getFieldState()));

        for (int i=0;i<textFields.size();i++){

            if (((DrawButton)textFields.get(i)).mouseClick==2) {
                switch (i) {
                    case 0 -> gsm.set(GameStateManager.STATES.MENU);
                    case 1 ->  gsm.setNew(GameStateManager.STATES.PLAY,
                            new PlayState(gsm, mapSize(namedScrollFields.get(0).getFieldState()),
                                    Integer.parseInt(namedScrollFields.get(1).getFieldState()),
                                    Integer.parseInt(namedScrollFields.get(2).getFieldState())));
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
        for (NamedScrollField sf : namedScrollFields) {
            sf.input(mouse,key);
        }

        for (DrawText dt:textFields){
            if (dt instanceof DrawButton){
                ((DrawButton)dt).input(mouse);
            }
        }
        mouse.setRotation(0);
    }

    @Override
    public void render(Graphics2D g) {
        for (NamedScrollField sf : namedScrollFields) {
            sf.render(g);
        }
        for (DrawText dt:textFields){
            dt.render(g);
        }
    }
}
