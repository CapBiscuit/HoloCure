import greenfoot.*;

/**
 * creates an image of UI;
 */

public class UI extends Actor
{
    public UI(GreenfootImage partImg)
    {
        partImg.scale(partImg.getWidth() * 2, partImg.getHeight()*2);
        setImage(partImg);
    }
}