import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Game World
 * Does not used as a stand-alone world, but rather as
 * a common functions and setting for other stage worlds
 */

public class Game extends World
{
    Player player;
    GreenfootSound music;
    int spawnTimer = 0;
    int SPAWN_DELAY = 60;
    
    int TIME = 500; // in seconds
    
    public Game()
    {    
        super(1280, 720, 1); 
        addObject(new TimeCountdown(TIME), getWidth()/2, 50);
        addObject(new DefeatedCounter(), getWidth()/2 + 425, 50);
    }

    /**
     * @param enemy - put a type of enemy to spawn it
     * @param x,y - randomize location to spawn enemy within borders
     */

    public void spawn(Enemy enemy)
    {
        int x = (Greenfoot.getRandomNumber(2) == 0) ? (player.getX() + Greenfoot.getRandomNumber(200)+100) : (player.getX() - Greenfoot.getRandomNumber(200) - 100);
        int y = (Greenfoot.getRandomNumber(2) == 0) ? (player.getY() + Greenfoot.getRandomNumber(200)+100) : (player.getY() - Greenfoot.getRandomNumber(200) - 100);
        x = Math.min(getWidth()-50, Math.max(50, x));
        y = Math.min(getHeight()-50, Math.max(50, y));
        addObject(enemy, x, y);
    }
    
    public void endgame() {
        music.stop();
        Greenfoot.setWorld(new GameOver());
    }
    
}
