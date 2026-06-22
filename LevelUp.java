import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class LevelUp extends Actor
{
    
    boolean keyCooldown = true;
    boolean isSetUp = false;
    ArrayList<Boolean> is_Upgrade = new ArrayList<Boolean>();
    ArrayList<Integer> weaponInd = new ArrayList<Integer>();
    
    
    public void act()
    {
        Game game = (Game)getWorld();
        Player player = (Player) game.getObjects(Player.class).get(0);
        update(game.selectedLevelup);
        if (!isSetUp) {
            int attacks_in = player.attacks.size();
            if (attacks_in < 6) {
                
            }
        }
    }
    
    public void update(int sLU)
    {
        GreenfootImage bg = new GreenfootImage("menu/UpgradeScreen" + sLU + ".png");
        setImage(bg);
    }
}
