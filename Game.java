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
    int SPAWN_DELAY = 30;
    int selectedPause = 1;
    
    boolean keyCooldown = true;
    boolean PAUSE = false;
    
    GreenfootImage bg;
    
    public Game()
    {    
        super(1280, 720, 1); 
        addObject(new TimeCountdown(), getWidth()/2, 50);
        addObject(new DefeatedCounter(), getWidth()/2 + 425, 50);
        addObject(new EXP_empty(), 640, 14);
        addObject(new EXP_full(), 640, 14);
        addObject(new EXPbar(),640,14);
        addObject(new HPbar(),300,50);
        addObject(new Pointer(),getWidth()/2,getHeight()/2);
        addObject(new Crosshair(),0,0);
        setPaintOrder(TimeCountdown.class,DefeatedCounter.class,UI.class,EXPbar.class,EXP_full.class,EXP_empty.class,HPbar.class,Pause.class,
                      Pointer.class, Crosshair.class, Enemy.class);
        
    }

    /**
     * @param enemy - put a type of enemy to spawn it
     * @param x,y - randomize location to spawn enemy within borders
     */

    public void spawn(Enemy enemy)
    {
        double angle = Math.toRadians(Greenfoot.getRandomNumber(360));
        
        int spawnX = (int)(player.worldX + Math.cos(angle) * 720);
        int spawnY = (int)(player.worldY + Math.sin(angle) * 720);

        
        int x = (Greenfoot.getRandomNumber(2) == 0)
            ? (int)(player.worldX + Greenfoot.getRandomNumber(200)+100)
            : (int)(player.worldX - Greenfoot.getRandomNumber(200)-100);
    
        int y = (Greenfoot.getRandomNumber(2) == 0)
            ? (int)(player.worldY + Greenfoot.getRandomNumber(200)+100)
            : (int)(player.worldY - Greenfoot.getRandomNumber(200)-100);
    
        enemy.worldX = spawnX;
        enemy.worldY = spawnY;
        addObject(enemy, 0, 0);
    }
    
    public void endgame() {
        music.stop();
        Greenfoot.setWorld(new GameOver());
    }
    
    public void spawn(){}
    
    public void act()
    {
        draw_background();
        update_camera();
        music.playLoop();
        if (!keyCooldown && Greenfoot.isKeyDown("escape") && selectedPause != 5) {
            PAUSE = PAUSE ? false : true;
            keyCooldown = true;
            if (PAUSE) addObject(new Pause(), 640, 360); else removeObject(getObjects(Pause.class).get(0));
        }
        
        if (!PAUSE) { spawnTimer--; spawn(); resume(); } else stop();
        
        if (PAUSE) {
            if (!keyCooldown && (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) && selectedPause != 1) 
            {selectedPause--; keyCooldown = true; Greenfoot.playSound("menu/select.wav");}
            if (!keyCooldown && (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) && selectedPause != 3) 
            {selectedPause++; keyCooldown = true; Greenfoot.playSound("menu/select.wav");}
            Pause.selectedPause = selectedPause;
            
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) && selectedPause == 1) 
            {removeObject(getObjects(Pause.class).get(0)); PAUSE = false; keyCooldown = true; Greenfoot.playSound("menu/confirm.wav");}
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) && selectedPause == 2) 
            {selectedPause = 5; keyCooldown = true; Greenfoot.playSound("menu/confirm.wav");}
            if (!keyCooldown && (Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("shift")) && selectedPause == 5) 
            {selectedPause = 2; keyCooldown = true; Greenfoot.playSound("menu/confirm.wav");}
        }
        
        if (keyCooldown && !(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("shift") || 
                             Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up") ||
                             Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down") ||
                             Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter"))) keyCooldown = false;
    }
    
    public void update_camera()
    {
        for (World_objects wObj : getObjects(World_objects.class))
        {
            int screenX = (int)(wObj.worldX - player.worldX + getWidth()/2);
            int screenY = (int)(wObj.worldY - player.worldY + getHeight()/2);
            wObj.setLocation(screenX, screenY);
        }
        player.setLocation(getWidth()/2, getHeight()/2);
    }
    
    public void draw_background()
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
    
    public void stop() {
        for (Enemy enemy : getObjects(Enemy.class)) enemy.stop                          = true;
        for (Projectile projectile : getObjects(Projectile.class)) projectile.stop      = true;
        for (Attack Atk : getObjects(Attack.class)) Atk.stop                            = true;
        for (Attack_Item Atk_I : getObjects(Attack_Item.class)) Atk_I.stop              = true;
        getObjects(Player.class).get(0).stop                                            = true;
        getObjects(Pointer.class).get(0).stop                                           = true;
        getObjects(TimeCountdown.class).get(0).stop                                     = true;
    }
    
    public void resume() {
        for (Enemy enemy : getObjects(Enemy.class)) enemy.stop                          = false;
        for (Projectile projectile : getObjects(Projectile.class)) projectile.stop      = false;
        for (Attack Atk : getObjects(Attack.class)) Atk.stop                            = false;
        for (Attack_Item Atk_I : getObjects(Attack_Item.class)) Atk_I.stop              = false;
        getObjects(Player.class).get(0).stop                                            = false;
        getObjects(Pointer.class).get(0).stop                                           = false;
        getObjects(TimeCountdown.class).get(0).stop                                     = false;
    }
}
