import greenfoot.*;

public class EXP extends World_objects

    
{
    GreenfootImage img = new GreenfootImage("misc/EXP.png");
    GreenfootImage limpid = new GreenfootImage("limpid.png");
    public EXP()
    {
        img.scale(30, 30);
        setImage(img);
    }

    public void act()
    {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        if (isTouching(Player.class)) {
            player.increaseExp(1);
            getWorld().removeObject(this);
            return;
        }
        if (getX() < 5 || getX() > 1275 || getY() < 5 || getY() > 715) //o_0//
        { setImage(limpid); } else { setImage(img); }
        
    }
}