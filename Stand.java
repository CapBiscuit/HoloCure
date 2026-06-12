import greenfoot.*;

public class Stand extends Actor
{
    int whY = 10;
    int wait;
    double rescale;
    String charName;
    boolean isFlying = true;
    public Stand(String charName, int wait, double rescale)
    {
        this.wait = wait;
        this.rescale = rescale;
        this.charName = charName;
        GreenfootImage img = new GreenfootImage("characters/" + charName + "/stand.png");
        img.scale((int)(img.getWidth()*rescale), (int)(img.getHeight()*rescale));
        setImage(img);
    }
    
    public void act()
    {
        GreenfootImage img = new GreenfootImage("characters/" + charName + "/stand.png");
        img.scale((int)(img.getWidth()*rescale), (int)(img.getHeight()*rescale));
        setImage(img);
        if (wait == 0) fly();
        else wait--;
    }
    
    public void Switch(String charName) {
        this.charName = charName;
        GreenfootImage img = new GreenfootImage("characters/" + charName + "/stand.png");
        img.scale((int)(img.getWidth()*rescale), (int)(img.getHeight()*rescale));
        setImage(img);
    }
    
    public void Rescale(int rescale) {
        this.rescale = rescale;
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
