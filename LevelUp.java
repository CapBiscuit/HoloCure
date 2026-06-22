import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class LevelUp extends Actor
{
    
    boolean keyCooldown = true;
    
    public void act()
    {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        update();
        
    }
    
    public void update()
    {
        setImage(new GreenfootImage("menu/pause_1.png"));
    }
}
