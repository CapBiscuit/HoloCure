import greenfoot.*;

public class Stand extends Actor
{
    int whY = 10;
    int wait;
    float rescale;
    boolean isFlying = true;
    public Stand(String charName, int wait, float rescale)
    {
        this.wait = wait;
        this.rescale = rescale;
        GreenfootImage img = new GreenfootImage("characters/" + charName + "/stand.png");
        img.scale((int)(img.getWidth()*rescale), (int)(img.getHeight()*rescale));
        setImage(img);
    }
    
    public void act()
    {
        if (wait == 0) fly();
        else wait--;
    }
    
    public void Switch(String charName) {
        GreenfootImage img = new GreenfootImage("characters/" + charName + "/stand.png");
        img.scale((int)(img.getWidth()*rescale), (int)(img.getHeight()*rescale));
        setImage(img);
    }
    
    public void fly() {
        if (isFlying) {setLocation(getX(), getY()+1); whY = whY+1;}
        else {setLocation(getX(), getY()-1); whY = whY-1;}
        if (whY == 80) isFlying = false;
        if (whY == 0) isFlying = true;
    }
}
