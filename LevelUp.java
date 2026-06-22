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
        Game game = (Game)getWorld();
        GreenfootImage bg = new GreenfootImage("menu/UpgradeScreen" + game.selectedLevelup + ".png");
        setImage(bg);
    }
}
