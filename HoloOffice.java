import greenfoot.*;

/**
 * Stage 2
 */

public class HoloOffice extends Game
{
    double x, y, mn = 2;
    public HoloOffice(String charName, int character)
    {
        music = new GreenfootSound("OST/HoloCure OST - Holo Office (Stage 2).mp3");
        
        bg = new GreenfootImage("stages/holooffice/background.png");
        bg.scale(( int)(bg.getWidth() * mn), (int)(bg.getHeight() * mn));
        
        WORLD_WIDTH  = bg.getWidth() ;   
        WORLD_HEIGHT = bg.getHeight() ;
        
        addObject(new UI(new GreenfootImage("characters/" + charName + "/portrait.png")),50,75);
        addObject(new EXPBAR(), 640, 20);
        
        player = new Player(charName, character);
        player.worldX = WORLD_WIDTH/2;
        player.worldY = WORLD_HEIGHT/2;
        
        addObject(player, getWidth()/2, getHeight()/2);
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
        for (int i = 0; i < 16; i++) {
            gen();
            addObject(new Prop("преп" + (Greenfoot.getRandomNumber(16) + 1) + ".png", "holooffice", x, y, true), 0, 0);
        }

        for (int i = 0; i < 12; i++) {
            gen();
            addObject(new Prop("стол"+ (Greenfoot.getRandomNumber(12) + 1) + ".png", "holooffice" , x, y, true), 0, 0);
        }
        
        for (int i = 0; i < 30; i++) {
            gen();
            addObject(new Prop("шуш"+ (Greenfoot.getRandomNumber(17) + 1) + ".png", "holooffice" , x, y), 0, 0);
        }   
    }
    private void gen(){
        x = 0;
        y = 0;
        do{
            x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
        }while (
                y < 525*mn ||
                y > 975*mn ||
                ((x - WORLD_WIDTH/2) * (x - WORLD_WIDTH/2) +
                 (y - WORLD_HEIGHT/2) * (y - WORLD_HEIGHT/2) < 150 * 150)
            );
    }
}
