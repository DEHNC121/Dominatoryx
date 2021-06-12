package main.states;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.util.saveLoad.DataPack;
import main.util.saveLoad.GameStyleData;
import main.util.saveLoad.SaveInfo;
import main.util.saveLoad.SaveManager;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameStyle {

    public enum PALETTE {
        BACKGROUND,
        GROUND,
        MAIN,
        FRONT,
        UPFRONT
    }

    static public String STYLE_PATH="rec/styles/GameStyleData.json";

    private final Map<PALETTE, Color> palette;

    public GameStyle(ArrayList<Color> colorList) {
        palette=new HashMap<>(PALETTE.values().length);
        int i=0;
        for (PALETTE p:PALETTE.values()){
            palette.put(p,colorList.get(i++));
        }

    }
    static public GameStyle loadStyle(){
        try {
            ArrayList<Color> colors=new ArrayList<>();
            TypeReference<GameStyleData> reference= new TypeReference<>() {};
            ObjectMapper mapper= new ObjectMapper();
            InputStream stream= new FileInputStream(new File(STYLE_PATH));
            GameStyleData info=mapper.readValue(stream,reference);
            stream.close();
            colors.add(new Color(info.backgroundRGB));
            colors.add(new Color(info.groundRGB));
            colors.add(new Color(info.mainRGB));
            colors.add(new Color(info.frontRGB));
            colors.add(new Color(info.upfrontRGB));
            return new GameStyle(colors);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    static public GameStyle loadOrDefault(){
        GameStyle out=loadStyle();
        if(out==null){
            try{
                File f=new File(STYLE_PATH);
                if(!f.exists()){
                    f.createNewFile();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            out=SettingsState.styles.get(0);
            out.saveStyle();
        }
        return out;
    }
    public void saveStyle(){
        try{
            TypeReference<GameStyleData> reference= new TypeReference<>() {};
            ObjectMapper mapper= new ObjectMapper();
            InputStream stream= new FileInputStream(new File(STYLE_PATH));
            mapper.writeValue(new File(STYLE_PATH),new GameStyleData(this));
        }catch (Exception e){
            //System.out.println("Save error");
            e.printStackTrace();
        }
    }
    public Color get(PALETTE p){
        return palette.get(p);
    }
}
