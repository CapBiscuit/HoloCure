import greenfoot.*;

/**
 * Enemy type the only difference is texture
 */

public class Takodachi extends Enemy
{
    
    public Takodachi()
    {
        frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/takodachi.png"), 3,1,0,3,2);
        setImage(frames[0]);
    }
    
    public void act()
    {
        moveTowardsPlayer();
        animate();
        hitPlayer();
    }
}
