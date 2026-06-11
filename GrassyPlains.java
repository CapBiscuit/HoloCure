import greenfoot.*;

/**
 * Stage 1
 */

public class GrassyPlains extends Game
{
    public GrassyPlains(String charName, int character)
    {
        music = new GreenfootSound("OST/HoloCure OST - Grassy Plains (Stage 1).mp3");
        bg = new GreenfootImage("stages/grassyplains/background.png");
        bg.scale(bg.getWidth() * 3,bg.getHeight() * 3);
        
        addObject(new UI(new GreenfootImage("characters/" + charName + "/portrait.png")),50,75);
        
        player = new Player(charName, character);
        addObject(player, getWidth()/2, getHeight()/2);
        addObject(player.attacks.get(0), 1, 1);
    }
    
    public void spawn()
    {
        if (spawnTimer <= 0) {
            spawn(new Deadbeat());
            spawnTimer = SPAWN_DELAY;
        }
    }
}