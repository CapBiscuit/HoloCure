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
        bg.scale(bg.getWidth() * 2,bg.getHeight() * 2);
        
        WORLD_WIDTH  = bg.getWidth() ;   
        WORLD_HEIGHT = bg.getHeight() ; 
        
        addObject(new UI(new GreenfootImage("characters/" + charName + "/portrait.png")),50,75);
        addObject(new EXPBAR(), 640, 20);
        
        player = new Player(charName, character);
        player.worldX = WORLD_WIDTH/2;
        player.worldY = 1400;
        
        addObject(player, getWidth()/2, getHeight()/2);
        addObject(player.attacks.get(0), 145, 115);
        spawnProps();
    }
    
    public void spawn()
    {
        TimeCountdown TIMER = getObjects(TimeCountdown.class).get(0);
        if (spawnTimer <= 0) {
            if(TIMER.min == 0 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("matsurisu"));
            if(TIMER.min == 1 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("haaton"));
            if(TIMER.min == 2 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("rosetai"));
            if(TIMER.min >= 3 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("kapumin"));
            spawnTimer = SPAWN_DELAY;
        }
    }
    
    private void spawnProps() {}
}
