import greenfoot.*;

/**
 * Enemy type the only difference is texture
 */

public class Deadbeat extends Enemy
{
    
    public Deadbeat()
    {
        frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/dead_beat.png"), 3,1,0,3,2);
        setImage(frames[0]);
    }
    
    public void act()
    {
        moveTowardsPlayer();
        animate();
        hitPlayer();
    }
}
