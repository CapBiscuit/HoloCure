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
        addObject(new EXPBAR(), 640, 20);
        
        player = new Player(charName, character);
        addObject(player, getWidth()/2, getHeight()/2);
        addObject(player.attacks.get(0), 145, 115);
    }
    
    public void spawn()
    {
        if (spawnTimer <= 0) {
            spawn(new Deadbeat());
            spawnTimer = SPAWN_DELAY;
        }
    }
}