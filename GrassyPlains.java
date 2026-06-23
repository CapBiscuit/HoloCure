import greenfoot.*;

/**
 * Stage 1
 */

public class GrassyPlains extends Game
{
    double x, y, mn = 2;
    public GrassyPlains(String charName, int character)
    {
        music = new GreenfootSound("OST/HoloCure OST - Grassy Plains (Stage 1).mp3");
        
        bg = new GreenfootImage("stages/grassyplains/background.png");
        bg.scale(bg.getWidth() * 2,bg.getHeight() * 2);
        
        WORLD_WIDTH  = bg.getWidth() ;   
        WORLD_HEIGHT = bg.getHeight() ; 
        
        addObject(new UI(new GreenfootImage("characters/" + charName + "/portrait.png")),50,75);
        addObject(new EXPBAR(), 640, 20);
        
        player = new Player(charName, character);
        player.worldX = WORLD_WIDTH/2;
        player.worldY = 0;
        
        addObject(player, getWidth()/2, getHeight()/2);
        spawnProps();
    }
    
    public void spawn()
    {
        TimeCountdown TIMER = getObjects(TimeCountdown.class).get(0);
        if (spawnTimer <= 0) {
            if(TIMER.min == 0 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("chumbud"));
            if(TIMER.min == 1 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("deadbeat"));
            if(TIMER.min == 2 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("investigator"));
            if(TIMER.min == 3 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("kfp"));
            if(TIMER.min >= 4 && getObjects(Enemy.class).size() < 300)spawn(new Enemy("takodachi"));
            spawnTimer = SPAWN_DELAY;
        }
    }
    
    private void spawnProps()
    {
        for (int i = 0; i < 35; i++) {
            double x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            double y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
            addObject(new Prop("flower_" + (Greenfoot.getRandomNumber(8) + 1) + ".png", "grassyplains", x, y), 0, 0);
        }

        for (int i = 0; i < 8; i++) {
            double x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            double y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
            addObject(new Prop("colum_" + (Greenfoot.getRandomNumber(2) + 1) + ".png", "grassyplains", x, y, true), 0, 0);
        }

        for (int i = 0; i < 6; i++) {
            double x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            double y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
            addObject(new Prop("fence_vertical.png", "grassyplains", x, y, true), 0, 0);
        }

        for (int i = 0; i < 5; i++) {
            double x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            double y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
            addObject(new Prop("fence_horizontal.png", "grassyplains", x, y, true), 0, 0);
        }
        
        for (int i = 0; i < 12; i++) {
            double x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            double y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
            addObject(new Prop("tree_" + (Greenfoot.getRandomNumber(2) + 1) + ".png", "grassyplains", x, y), 0, 0);
        }
    }
    private void gen(){
        x = 0;
        y = 0;
        do{
            x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
        }while (
                 ((x - WORLD_WIDTH/2) * (x - WORLD_WIDTH/2) +
                 (y - 1400) * (y - 1400) < 150 * 150)
            );
    }
}