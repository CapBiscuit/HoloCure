import greenfoot.*;

/**
 * Stage 1
 */

public class GrassyPlains extends Game
{
    public GrassyPlains(String charName, int character)
    {
        player = new Player(charName, character);
        addObject(player, getWidth()/2, getHeight()/2);
        
        bg = new GreenfootImage("stages/grassyplains/background.png");//o_0
        bg.scale(bg.getWidth() * 3,bg.getHeight() * 3);
        
        addObject(new UI(new GreenfootImage("characters/" + charName + "/portrait.png")),50,75);
        
        
        music = new GreenfootSound("HoloCure OST - Grassy Plains (Stage 1).mp3");
    }
    public void World_logic(){//o_0
        if (spawnTimer <= 0){
            spawn(new Deadbeat());
            spawnTimer = SPAWN_DELAY;
        }
    }
}
