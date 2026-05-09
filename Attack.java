import greenfoot.*;


public class Attack extends Actor
{
    // Animation
    GreenfootImage[] frames = new GreenfootImage[6];
    int frameIndex = 0;
    int animationDelay = 0;
    int animationInterval = 5;
    int rotation;
    
    // Stats
    int range = 150;

    public Attack(String charName, int rotation)
    {
        frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/" + charName + "/attack.png"), 6,1,0,6,1);
        setImage(frames[0]);
        this.rotation = rotation;
    }

    public void act()
    {
        
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        if (enemy != null) enemy.death();
        
        animationDelay--;
        if (animationDelay <= 0) {
            animationDelay = animationInterval;
            frameIndex++;
            if (frameIndex < frames.length) setImage(frames[frameIndex]);
            else getWorld().removeObject(this);
            setRotation(rotation);
        }
    }
}