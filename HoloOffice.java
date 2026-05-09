import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Stage 2
 */

public class HoloOffice extends Game
{
    public HoloOffice(String charName)
    {
        player = new Player(charName);
        addObject(player, getWidth()/2, getHeight()/2);
        
        setBackground("stages/holooffice/background.png");
        
        addObject(new UI(new GreenfootImage("characters/" + charName + "/portrait.png")),50,75);
        
        addObject(new EXPbar(),630,25);
        addObject(new HPbar(),300,50);
        
        music = new GreenfootSound("HoloCure OST - Holo Office (Stage 2).mp3");
        
    }
    
    public void act() {
        music.playLoop();
        spawnTimer--;
        if (spawnTimer <= 0) {
            spawn(new Chumbud());
            spawnTimer = SPAWN_DELAY;
        }
    }
    
    
}
