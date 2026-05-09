import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Stage 3
 */

public class HalloweenCastle extends Game
{
    public HalloweenCastle(String charName)
    {
        player = new Player(charName);
        addObject(player, getWidth()/2, getHeight()/2);
        
        setBackground("stages/halloweencastle/background.png");
        
        addObject(new UI(new GreenfootImage("characters/" + charName + "/portrait.png")),50,75);
        
        addObject(new EXPbar(),630,25);
        addObject(new HPbar(),300,50);
        
        music = new GreenfootSound("HoloCure OST - Halloween Castle (Stage 3).mp3");
    }
    
    public void act() {
        music.playLoop();
        spawnTimer--;
        if (spawnTimer <= 0) {
            spawn(new Takodachi());
            spawnTimer = SPAWN_DELAY;
        }
    }
}
