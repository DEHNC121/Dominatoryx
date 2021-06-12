package main.graphics;

import main.GamePanel;

import java.awt.*;

public class NewDrawText {

    protected Font font;
    public static String fontName="Times New Roman";

    protected String text;

    protected Rectangle outRectangle;
    protected Rectangle inRectangle;

    protected Color textColor;
    protected Color inColor;
    protected Color outColor;

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

        setSize(GamePanel.getGraphics2D());
    }

    public NewDrawText(String text, Rectangle outRectangle,float heightPercentages) {
        init(text,outRectangle,heightPercentages);
    }

    public NewDrawText(String text, Rectangle outRectangle,float heightPercentages, Color textColor) {
        init(text,outRectangle,heightPercentages);
        this.textColor = textColor;
    }

    public NewDrawText( String text,Rectangle outRectangle,float heightPercentages, Color textColor, String fontName) {
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

    public static void setFontName(String fontName) {
        NewDrawText.fontName = fontName;
    }


    public void render (Graphics g) {


//        g.setColor(Color.green);
//        g.drawRect(inRectangle.x,inRectangle.y,inRectangle.width,inRectangle.height);

        g.setColor(textColor);
        g.setFont(font);


        g.drawString(text,inRectangle.x, inRectangle.y+inRectangle.height);
    }

}
