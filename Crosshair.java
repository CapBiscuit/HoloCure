import greenfoot.*;

public class Crosshair extends Actor 
{
    public void act() 
    {
        GreenfootImage img = new GreenfootImage("UI/Crosshair.png");
        img.scale(50,50);
        setImage(img);
        if (Greenfoot.getMouseInfo() != null) {
            int mouseX = Greenfoot.getMouseInfo().getX();
            int mouseY = Greenfoot.getMouseInfo().getY();
            setLocation(mouseX, mouseY);
        }
    }    
}
