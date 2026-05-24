import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EXP_empty here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EXP_empty extends Actor
{
    /**
     * Act - do whatever the EXP_empty wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act()
    {
        update();
    }
    
    
    void update() {
        Player p = getWorld().getObjects(Player.class).get(0);
        GreenfootImage img = getImage();
        int width = (int)(1280.0 * (1.0 - (p.Exp / (float)p.EXP_CAP)));
        img.scale(width, 28);
        setImage(img);
        setLocation(1280 - (width / 2), 14);
    }
}
