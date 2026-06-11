import greenfoot.*;

public class Pause extends Actor
{
    public static int selectedPause = 1;
    public void act()
    {
        update();
    }
    public void update()
    {
        setImage(new GreenfootImage("menu/pause_" + selectedPause + ".png"));
    }
}
