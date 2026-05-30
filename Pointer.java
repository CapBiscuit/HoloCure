import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Pointer extends Actor
{
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null) return;
        int rotation = (int) Math.toDegrees(Math.atan2(mouse.getY() - getWorld().getHeight()/2, mouse.getX() - getWorld().getWidth()/2)); 
        setRotation(rotation);
        setImage(new GreenfootImage("UI/Pointer.png"));
    }
}
