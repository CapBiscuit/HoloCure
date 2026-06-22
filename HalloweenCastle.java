import greenfoot.*;

/**
 * Stage 1
 */

public class HalloweenCastle extends Game
{
    String pole;
    double mn = 2;
    public HalloweenCastle(String charName, int character)
    {
        pole = "halloweencastle";
        music = new GreenfootSound("OST/HoloCure OST - Halloween Castle (Stage 3).mp3");
        bg = new GreenfootImage("stages/halloweencastle/замок.png");
        
        bg.scale((int)(bg.getWidth() * mn) ,(int)(bg.getHeight() * mn));
        
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
    
    public void spawn_lokal()
    {
        if (spawnTimer <= 0) {
            spawn_l(new Takodachi());
            spawnTimer = SPAWN_DELAY;
        }
    }
    
        public void spawn_l(Enemy enemy)
    {
        double mn = 2;
        do {
            double angle = Math.toRadians(Greenfoot.getRandomNumber(360));
            double distance = 600 + Greenfoot.getRandomNumber(400);
            enemy.worldX = player.worldX + Math.cos(angle) * distance;
            enemy.worldY = player.worldY + Math.sin(angle) * distance;
    
        } while (
            enemy.worldX < 233 * mn ||
            enemy.worldX > 1766 * mn ||
            enemy.worldY < 520 * mn ||
            enemy.worldY > 1547 * mn ||
            (enemy.worldX < 745 * mn &&
             enemy.worldY < 765 * mn) ||
            (enemy.worldX > 1256 * mn &&
             enemy.worldY < 765 * mn)
        );
    
        wrap_object(enemy);
        addObject(enemy, 0, 0);
    }

    private void spawnProps()
    {
    }
}