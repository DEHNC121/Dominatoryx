package main.states;

import main.GamePanel;
import main.graphics.DrawButton;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.playStateGUI.Button;
import main.util.saveLoad.DataPack;
import main.util.saveLoad.SaveManager;

import java.awt.*;
import java.io.File;


public class LoadState extends GameState {
    int SLOT_WIDTH=(int)(GamePanel.width*0.3);
    int SLOT_HEIGHT= (int)(GamePanel.height*0.15);
    int SLOT_Y=(int)(GamePanel.height*0.45);
    int SLOT_X=(int)(GamePanel.width*0.35);
    String PATH="saves/slot";
    String DATA_PACK="/DataPack.json";
    int mouseX=0;
    int mouseY=0;
    int isClicked=0;
    Way way;
    DrawButton[] slots=new DrawButton[3];
    public enum Way{LOAD,SAVE}
    public LoadState(GameStateManager gsm, Way way){
        super(gsm);
        this.way=way;
        for(int i=0;i<3;i++){
            slots[i]=new DrawButton("Slot "+(i+1),new Rectangle(SLOT_X,SLOT_Y+i*SLOT_HEIGHT,
                    SLOT_WIDTH,SLOT_HEIGHT),SLOT_HEIGHT);
            new File(PATH+i).mkdirs();
            try{
                new File(PATH+i+DATA_PACK).createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {
        for(int i=0;i<3;i++){
            if(slots[i].mouseClick){
                if(way== Way.LOAD){
                    DataPack pack= SaveManager.load(PATH+i+DATA_PACK);
                    if(pack==null)
                        return;
                    gsm.setNew(GameStateManager.STATES.PLAY,new PlayState(gsm,pack));
                }
                else{
                    SaveManager.save(PATH+i+DATA_PACK);
                }
                slots[i].mouseClick=false;
            }
        }
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        for(int i=0;i<3;i++){
            slots[i].input(mouse);
        }
    }

    @Override
    public void render(Graphics2D g) {
        for(int i=0;i<3;i++){
            slots[i].render(g);
        }
    }
}
