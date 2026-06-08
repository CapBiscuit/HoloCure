import greenfoot.*;

/**
 * Stage 3
 */

public class HalloweenCastle extends Game
{
    public HalloweenCastle(String charName, int character)
    {
        music = new GreenfootSound("OST/HoloCure OST - Halloween Castle (Stage 3).mp3");
        bg = new GreenfootImage("stages/halloweencastle/background.png");
        bg.scale(bg.getWidth() * 3,bg.getHeight() * 3);
        
        addObject(new UI(new GreenfootImage("characters/" + charName + "/portrait.png")),50,75);
        
        player = new Player(charName, character);
        addObject(player, getWidth()/2, getHeight()/2);
        addObject(player.attacks.get(0), 1, 1);
    }
    
    public void World_logic()
    {
        if (spawnTimer <= 0) {
            spawn(new Takodachi());
            spawnTimer = SPAWN_DELAY;
        }
    }
}
