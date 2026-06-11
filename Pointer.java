import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Pointer extends Actor
{
    boolean stop = false;
    
    public void act()
    {
        if (!stop) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse == null) return;
            int rotation = (int) Math.toDegrees(Math.atan2(mouse.getY() - getWorld().getHeight()/2, mouse.getX() - getWorld().getWidth()/2)); 
            setRotation(rotation);
            setImage(new GreenfootImage("UI/Pointer.png"));
        }
    }
}
