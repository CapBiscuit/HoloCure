import greenfoot.*;

/**
 * Enemy type the only difference is texture
 */

public class Chumbud extends Enemy
{
    
    public Chumbud()
    {
        frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/chumbud.png"), 3,1,0,3,2);
        setImage(frames[0]);
    }
    
    public void act()
    {
        moveTowardsPlayer();
        animate();
        hitPlayer();
    }
}
