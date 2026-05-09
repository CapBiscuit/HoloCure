import greenfoot.*;

public class EXP extends World_objects
{
    public EXP()
    {
        GreenfootImage img = new GreenfootImage("misc/EXP.png");
        img.scale(30, 30);
        setImage(img);
    }

    public void act()
    {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        if (isTouching(Player.class)) {
            player.increaseExp(1);
            getWorld().removeObject(this);
        }
    }
}