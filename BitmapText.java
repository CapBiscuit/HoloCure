import greenfoot.*;

public class BitmapText extends Actor
{
    private static BitmapFont font = new BitmapFont( "font/white_big.png", 6, 10  );

    public BitmapText(String text, int scale) {setText(text, scale);}
    
    public void setText(String text, int scale) {setImage(font.renderText(text, scale));}
}