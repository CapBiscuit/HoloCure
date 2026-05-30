import greenfoot.*;

public class Crosshair extends Actor 
{
    int rotation = 1;
    public void act() 
    {
        setImage("UI/Crosshair.png");
        if (Greenfoot.getMouseInfo() != null) {
            int mouseX = Greenfoot.getMouseInfo().getX();
            int mouseY = Greenfoot.getMouseInfo().getY();
            setLocation(mouseX, mouseY);
        }
        setRotation(rotation++);
    }    
}
