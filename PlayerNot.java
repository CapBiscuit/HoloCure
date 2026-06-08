import greenfoot.*;

public class PlayerNot extends Actor
{
    GreenfootImage[] standSets = new GreenfootImage[3];
    GreenfootImage[] moveSets = new GreenfootImage[6];
    int character;
    String charName;
    
    // Animation
    int frameCurrent = 0;
    int animationDelay = 0;
    int animationInterval = 10;
    
    public PlayerNot()
    {
        this.charName = charName;
        standSets = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/amelia/amelia.png"), 6,2,0,3,2);
        moveSets  = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/amelia/amelia.png"), 6,2,1,6,2);
        setImage(standSets[0]);
    }
    
    public void act()
    {
        update();
    }
    
    public void Switch(String charName) {
        standSets = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/" + charName + "/" + charName + ".png"), 6,2,0,3,2);
        moveSets  = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/" + charName + "/" + charName + ".png"), 6,2,1,6,2);
    }
    
    public void update()
    {
        animationDelay--;
        if (animationDelay <= 0) {
            animationDelay = animationInterval;            
            frameCurrent++;
            if (frameCurrent == 9) frameCurrent = 0;
            if (frameCurrent < 6) setImage(moveSets[frameCurrent]);
            else setImage(standSets[frameCurrent-6]);
        }
    }
}
