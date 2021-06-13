package main.graphics;

import main.GamePanel;
import main.states.GameStateManager;
import main.states.GameStyle;

import java.awt.*;

public class DrawText {

    protected Font font;
    public static String fontName="Times New Roman";

    protected String text;

    protected Rectangle outRectangle;
    protected Rectangle inRectangle;

    protected Color textColor;
    protected Color inColor;
    protected Color outColor;

    protected int alpha;


    protected GameStyle.PALETTE textColorStyle;
    protected GameStyle.PALETTE inColorStyle;
    protected GameStyle.PALETTE outColorStyle;

    public Rectangle getOutRectangle() {
        return outRectangle;
    }

    public Rectangle getInRectangle() {
        return inRectangle;
    }

    protected void init(String text, Rectangle outRectangle, float heightPercentages){

        this.font = new Font(fontName, Font.PLAIN, -1);
        this.text = text;
        this.outRectangle = outRectangle;
        this.inRectangle = new Rectangle(0,0,0,(int)(outRectangle.height*heightPercentages)) ;
        this.textColor = new Color(0,0,0,255);
        this.inColor = new Color(4, 52, 72,255);
        this.outColor = new Color(0,0,0,0);
        this.textColorStyle = null;
        this.inColorStyle = GameStyle.PALETTE.GROUND;
        this.outColorStyle = null;
        this.alpha=255;

        setSize(GamePanel.getGraphics2D());
    }

    public DrawText(String text, Rectangle outRectangle, float heightPercentages) {
        init(text,outRectangle,heightPercentages);
    }

    public DrawText(String text, Rectangle outRectangle, float heightPercentages, Color textColor) {
        init(text,outRectangle,heightPercentages);
        this.textColor = textColor;
    }

    public DrawText(String text, Rectangle outRectangle, float heightPercentages, GameStyle.PALETTE textColorStyle) {
        init(text,outRectangle,heightPercentages);
        this.textColorStyle = textColorStyle;
    }
    public DrawText(String text, Rectangle outRectangle, float heightPercentages, GameStyle.PALETTE textColorStyle, int alpha) {
        init(text,outRectangle,heightPercentages);
        this.textColorStyle = textColorStyle;
        this.alpha=alpha;
    }

    public DrawText(String text, Rectangle outRectangle, float heightPercentages, Color textColor, String fontName) {
        init(text,outRectangle,heightPercentages);
        this.textColor = textColor;
        this.font = new Font(fontName, Font.PLAIN, -1);
    }

    protected void setSize(Graphics g) {
        font=new Font(font.getName(),Font.PLAIN,1);
        FontMetrics metrics = g.getFontMetrics(font);

        while(inRectangle.height>metrics.getHeight()){

            font=new Font(font.getName(),Font.PLAIN,font.getSize()+1);
            metrics = g.getFontMetrics(font);
        }

        font=new Font(font.getName(),Font.PLAIN,font.getSize()-1);
        metrics = g.getFontMetrics(font);


        int x = outRectangle.x + (outRectangle.width - metrics.stringWidth(text)) / 2;
        int y = outRectangle.y + ((outRectangle.height - metrics.getHeight()) / 2) + metrics.getAscent();

        inRectangle.setSize(metrics.stringWidth(text),metrics.getHeight());

        inRectangle.setLocation(x,y-inRectangle.height);



    }

    public void setText(String text) {
        this.text = text;
    }

    public static void setFontName(String fontName) {
        DrawText.fontName = fontName;
    }

    public void update(){
        if (textColorStyle!=null){
            Color data=GameStateManager.gameStyle.get(textColorStyle);
            textColor= new Color(data.getRed(),data.getGreen(),data.getBlue(),alpha);
        }
        if (inColorStyle!=null){
            inColor= GameStateManager.gameStyle.get(inColorStyle);
        }
        if (outColorStyle!=null){
            outColor= GameStateManager.gameStyle.get(outColorStyle);
        }
    }

    public void render (Graphics g) {


//        g.setColor(Color.green);
//        g.drawRect(inRectangle.x,inRectangle.y,inRectangle.width,inRectangle.height);

        g.setColor(textColor);
        g.setFont(font);


        g.drawString(text,inRectangle.x, inRectangle.y+inRectangle.height);
    }

}
