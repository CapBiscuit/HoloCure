import greenfoot.*;

/**
 * Stage 3
 */

public class HalloweenCastle extends Game
{
    double x, y, mn = 2;
    public HalloweenCastle(String charName, int character)
    {
        music = new GreenfootSound("OST/HoloCure OST - Halloween Castle (Stage 3).mp3");
        
        bg = new GreenfootImage("stages/halloweencastle/background.png");
        bg.scale(( int)(bg.getWidth() * mn), (int)(bg.getHeight() * mn));
        
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
    
    private void spawnProps()
    {
        for (int i = 0; i < 20; i++) {
            gen();
            addObject(new Prop("нешуш" + (Greenfoot.getRandomNumber(5) + 1) + ".png", "halloweencastle", x, y), 0, 0);
        }

        for (int i = 0; i < 15; i++) {
            gen();
            addObject(new Prop("преп" + (Greenfoot.getRandomNumber(9) + 1) + ".png", "halloweencastle", x, y, true), 0, 0);
        }

        for (int i = 0; i < 8; i++) {
            gen();
            addObject(new Prop("стена"+ (Greenfoot.getRandomNumber(3) + 1) + ".png", "halloweencastle" , x, y, true), 0, 0);
        }
        
        for (int i = 0; i < 25; i++) {
            gen();
            addObject(new Prop("шуш"+ (Greenfoot.getRandomNumber(3) + 1) + ".png", "halloweencastle" , x, y), 0, 0);
        }
    }
    
    private void gen(){
        x = 0;
        y = 0;
        do{
            x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
        }while (
                x < 233 * mn ||
                x > 1766 * mn ||
                y < 520 * mn ||
                y > 1547 * mn ||
                (x < 745 * mn &&
                 y < 765 * mn) ||
                (x > 1256 * mn &&
                 y < 765 * mn) ||
                 ((x - WORLD_WIDTH/2) * (x - WORLD_WIDTH/2) +
                 (y - 1400) * (y - 1400) < 150 * 150)
            );
    }
}
