import greenfoot.*;

public class Prop extends World_objects
{
    private boolean isSolid = false; 
    public GreenfootImage img;

    public Prop(String imageName, String stage, double worldX, double worldY, boolean solid)
    {
        this.isSolid = solid;
        
        img = new GreenfootImage("stages/" + stage + "/props/" + imageName);
        img.scale((int)(img.getWidth() * 1.75), (int)(img.getHeight() * 1.75));
        setImage(img);
        
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public Prop(String imageName, String stage, double worldX, double worldY)
    {
        this(imageName, stage, worldX, worldY, false);
    }

    public boolean isSolid(){return isSolid;}

    public void act()
    {
        if (getX() < 5 || getX() > 1275 || getY() < 5 || getY() > 715)
        { setImage(limpid); return; } 
        setImage(img);
    }
}