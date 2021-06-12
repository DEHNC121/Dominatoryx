package main.util.saveLoad;

import main.states.GameStyle;

public class GameStyleData {
    public int upfrontRGB;
    public int frontRGB;
    public int mainRGB;
    public int groundRGB;
    public int backgroundRGB;
    public GameStyleData(){};
    public GameStyleData(GameStyle gs){
        upfrontRGB=gs.get(GameStyle.PALETTE.UPFRONT).getRGB();
        frontRGB=gs.get(GameStyle.PALETTE.FRONT).getRGB();
        mainRGB=gs.get(GameStyle.PALETTE.MAIN).getRGB();
        groundRGB=gs.get(GameStyle.PALETTE.GROUND).getRGB();
        backgroundRGB=gs.get(GameStyle.PALETTE.BACKGROUND).getRGB();
    }
}
