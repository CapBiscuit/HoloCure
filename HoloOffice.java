import greenfoot.*;

/**
 * Stage 2
 */

public class HoloOffice extends Game
{
    public HoloOffice(String charName, int character)
    {
        music = new GreenfootSound("OST/HoloCure OST - Holo Office (Stage 2).mp3");
        
        bg = new GreenfootImage("stages/holooffice/background.png");
        bg.scale(bg.getWidth() * 2,bg.getHeight() * 2);
        
        WORLD_WIDTH  = bg.getWidth() ;   
        WORLD_HEIGHT = bg.getHeight() ;
        
        addObject(new UI(new GreenfootImage("characters/" + charName + "/portrait.png")),50,75);
        addObject(new EXPBAR(), 640, 20);
        
        player = new Player(charName, character);
        player.worldX = WORLD_WIDTH/2;
        player.worldY = WORLD_HEIGHT/2;
        
        addObject(player, getWidth()/2, getHeight()/2);
        addObject(player.attacks.get(0), 145, 115);
        spawnProps();
    }
    
    public void spawn()
    {
        TimeCountdown TIMER = getObjects(TimeCountdown.class).get(0);
        ;
        if (spawnTimer <= 0) {
            if(TIMER.min == 0 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("sukonbu"));
            if(TIMER.min == 1 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("miofa"));
            if(TIMER.min == 2 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("onigiriya"));
            if(TIMER.min >= 3 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("koronesuki"));
            spawnTimer = SPAWN_DELAY;
        }
    }
    
    private void spawnProps()
    {
        
    }
}
