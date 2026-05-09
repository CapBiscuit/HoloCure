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
    
    GreenfootImage bg; // ФОООООООООООООООООООН
    
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
        int x = (Greenfoot.getRandomNumber(2) == 0)
            ? (int)(player.worldX + Greenfoot.getRandomNumber(200)+100)
            : (int)(player.worldX - Greenfoot.getRandomNumber(200)-100);
    
        int y = (Greenfoot.getRandomNumber(2) == 0)
            ? (int)(player.worldY + Greenfoot.getRandomNumber(200)+100)
            : (int)(player.worldY - Greenfoot.getRandomNumber(200)-100);
    
        enemy.worldX = x;
        enemy.worldY = y;
        addObject(enemy, 0, 0);
    }
    
    public void endgame() {
        music.stop();
        Greenfoot.setWorld(new GameOver());
    }
    
    public void World_logic(){}
    
    public void act() //o_0
    {
        draw_background();
        update_camera();
        music.playLoop();
        spawnTimer--;
        World_logic();
    }
    
        private boolean isVisible(int x, int y)
    {
        return x >= -64 && x <= getWidth() + 64 &&
               y >= -64 && y <= getHeight() + 64;
    }
    
        public void update_camera()//o_0
    {
        for (World_objects wObj : getObjects(World_objects.class))
        {
            int screenX = (int)(wObj.worldX - player.worldX + getWidth()/2);
            int screenY = (int)(wObj.worldY - player.worldY + getHeight()/2);
            wObj.setLocation(screenX, screenY);
            if (isVisible(screenX, screenY)) { wObj.setLocation(screenX, screenY); }
        }
        player.setLocation(getWidth()/2, getHeight()/2);
    }
    
    public void draw_background() //o_0
    {
        GreenfootImage canvas = new GreenfootImage(getWidth(), getHeight());
    
        int camX = (int)player.worldX;
        int camY = (int)player.worldY;
    
        int tileW = bg.getWidth();
        int tileH = bg.getHeight();
    
        int offsetX = camX % tileW;
        int offsetY = camY % tileH;
    
        if (offsetX < 0) offsetX += tileW;
        if (offsetY < 0) offsetY += tileH;
    
        for (int x = -tileW; x < getWidth() + tileW; x += tileW)
        {
            for (int y = -tileH; y < getHeight() + tileH; y += tileH)
            {
                canvas.drawImage(bg, x - offsetX, y - offsetY);
            }
        }
        setBackground(canvas);
    }
    
}
