import greenfoot.*;


public class Attack extends Actor
{
    // Animation
    GreenfootImage[] frames;
    int frameIndex = 0;
    int animationDelay = 0;
    int animationInterval = 5;
    int rotation;
    
    // Stats
    int range = 150;

    public Attack(String charName, int rotation)
    {
        
        switch (charName) {
            case "suisei": 
                frames = new GreenfootImage[6];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/suisei/attack.png"), 6,1,0,6,1);
                break;
            case "gura":
                frames = new GreenfootImage[4];
                frames = SpriteSheetHandler.splitSheetVertical(new GreenfootImage("characters/gura/attack.png"), 1,4,0,4,1);
                break;
            case "ina": 
                frames = new GreenfootImage[12];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/ina/attack.png"), 12,1,0,12,1);
                break;
            case "kiara": 
                frames = new GreenfootImage[8];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/kiara/attack.png"), 8,1,0,8,1);
                break;
            case "mori":
                frames = new GreenfootImage[10];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/mori/attack.png"), 10,1,0,10,1);
                break;
            case "filian":
                frames = new GreenfootImage[6];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/filian/attack.png"), 6,1,0,6,1);
                break;
            case "neuro":
                frames = new GreenfootImage[6];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/neuro/attack.png"), 6,1,0,6,1);
                break;
            case "cecilia":
                frames = new GreenfootImage[6];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/cecilia/attack.png"), 6,1,0,6,1);
                break;
        }
        
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
