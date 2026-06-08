import greenfoot.*;

public class Stand extends Actor
{
    int whY = 10;
    int wait;
    boolean isFlying = true;
    public Stand(String charName, int wait)
    {
        this.wait = wait;
        GreenfootImage img = new GreenfootImage("characters/" + charName + "/stand.png");
        img.scale(img.getWidth()*2,img.getHeight()*2);
        setImage(img);
    }
    
    public void act()
    {
        if (wait == 0) fly();
        else wait--;
    }
    
    public void fly() {
        if (isFlying) {setLocation(getX(), getY()+1); whY = whY+1;}
        else {setLocation(getX(), getY()-1); whY = whY-1;}
        if (whY == 80) isFlying = false;
        if (whY == 0) isFlying = true;
    }
}
