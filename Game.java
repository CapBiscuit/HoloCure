import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Game World
 * Does not used as a stand-alone world, but rather as
 * a common functions and setting for other stage worlds
 */

public class Game extends World
{
    protected int WORLD_WIDTH;
    protected int WORLD_HEIGHT;
    
    Player player;
    GreenfootSound music;
    
    int spawnTimer = 0;
    int SPAWN_DELAY = 30;
    int selectedPause = 1;
    int selectedLevelup = 1;
    
    boolean keyCooldown = true;
    boolean PAUSE = false;
    boolean LEVELUP = false;
    
    GreenfootImage bg;
    
    public Game()
    {    
        super(1280, 720, 1); 
        addObject(new TimeCountdown(), getWidth()/2, 80);
        addObject(new DefeatedCounter(), getWidth()/2 + 425, 50);
        addObject(new HPBAR(),343, 46);
        addObject(new Pointer(),getWidth()/2,getHeight()/2);
        addObject(new Crosshair(),0,0);
        addObject(new AttackItemBar(), 320, 115);
        setPaintOrder(Pause.class, LevelUp.class,  //TOP PRIORITY
                      TimeCountdown.class,DefeatedCounter.class,UI.class,EXPBAR.class,HPBAR.class,
                      Attack_Item.class, AttackItemBar.class, Pointer.class, Crosshair.class, 
                      Enemy.class); //BOTTOM PRIORITY
        
    }

    /**
     * @param enemy - put a type of enemy to spawn it
     * @param x,y - randomize location to spawn enemy within borders
     */

    public void spawn(Enemy enemy)
    {
        double angle = Math.toRadians(Greenfoot.getRandomNumber(360));
        double distance = 600 + Greenfoot.getRandomNumber(400);
        enemy.worldX = player.worldX + Math.cos(angle) * distance;
        enemy.worldY = player.worldY + Math.sin(angle) * distance;
        wrap_object(enemy);
        addObject(enemy, 0, 0);
    }
    
    public void endgame() {
        music.stop();
        Greenfoot.setWorld(new GameOver());
    }
    
    public void spawn(){}
    
    public double wrapX(double x) {
        while (x >= WORLD_WIDTH)  x -= WORLD_WIDTH;
        while (x < 0)             x += WORLD_WIDTH;
        return x;
    }
    
    public double wrapY(double y) {
        while (y >= WORLD_HEIGHT) y -= WORLD_HEIGHT;
        while (y < 0)             y += WORLD_HEIGHT;
        return y;
    }
    
    public void wrap_object(Actor obj) {
        if (obj == null) return;
        
        if (obj instanceof World_objects wo) {
            wo.worldX = wrapX(wo.worldX);
            wo.worldY = wrapY(wo.worldY);
        }
        else if (obj instanceof Player p) {
            p.worldX = wrapX(p.worldX);
            p.worldY = wrapY(p.worldY);
        }
    }
    
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
        
        if (!PAUSE && !LEVELUP) { spawnTimer--; spawn(); resume(); } else stop();
        
        if (PAUSE) {
            if (!keyCooldown && (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) && selectedPause != 1) 
            {selectedPause--; keyCooldown = true; Greenfoot.playSound("menu/select.mp3");}
            if (!keyCooldown && (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) && selectedPause != 3) 
            {selectedPause++; keyCooldown = true; Greenfoot.playSound("menu/select.mp3");}
            
            Pause.selectedPause = selectedPause;
            
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) && selectedPause == 1) 
            {removeObject(getObjects(Pause.class).get(0)); PAUSE = false; keyCooldown = true; Greenfoot.playSound("menu/confirm.mp3");}
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) && selectedPause == 2) 
            {selectedPause = 5; keyCooldown = true; Greenfoot.playSound("menu/confirm.mp3");}
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) && selectedPause == 3) 
            {music.stop(); keyCooldown = true; Greenfoot.playSound("menu/confirm.mp3"); Greenfoot.setWorld(new Menu());}
            if (!keyCooldown && (Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("shift")) && selectedPause == 5) 
            {selectedPause = 2; keyCooldown = true; Greenfoot.playSound("menu/confirm.mp3");}
        } else if (LEVELUP) {
            if (!keyCooldown && (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up"))) 
            {selectedLevelup--; keyCooldown = true; Greenfoot.playSound("menu/select.mp3");}
            if (!keyCooldown && (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down"))) 
            {selectedLevelup++; keyCooldown = true; Greenfoot.playSound("menu/select.mp3");}
            
            selectedLevelup += selectedLevelup == 0 ? 4 : 0;
            selectedLevelup -= selectedLevelup == 5 ? 4 : 0;
            
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter"))) {
                removeObject(getObjects(LevelUp.class).get(0)); 
                if (getObjects(LevelUp.class).size() == 0) {
                    LEVELUP = false;
                }
                else {
                    LevelUp();
                }
                keyCooldown = true; 
                Greenfoot.playSound("menu/confirm.mp3");
            }
        }
        
        if (keyCooldown && !(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("shift") || 
                             Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up") ||
                             Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down") ||
                             Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter"))) keyCooldown = false;
    }
    
    public void update_camera()
    {
        double camX = player.worldX;
        double camY = player.worldY;
        player.setLocation(getWidth()/2, getHeight()/2);
        
        for (World_objects wObj : getObjects(World_objects.class))
        {
            double dx = wObj.worldX - camX;
            double dy = wObj.worldY - camY;
            
            if (dx > WORLD_WIDTH / 2.0)   dx -= WORLD_WIDTH;
            else if (dx < -WORLD_WIDTH / 2.0) dx += WORLD_WIDTH;
            
            if (dy > WORLD_HEIGHT / 2.0)  dy -= WORLD_HEIGHT;
            else if (dy < -WORLD_HEIGHT / 2.0) dy += WORLD_HEIGHT;
            int screenX = (int)(dx + getWidth() / 2);
            int screenY = (int)(dy + getHeight() / 2);
            
            wObj.setLocation(screenX, screenY);
        }
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
        
        int startX = (getWidth() / 2) % tileW - offsetX;
        int startY = (getHeight() / 2) % tileH - offsetY;
    
        for (int x = startX-tileW; x < getWidth() + tileW; x += tileW)
        {
            for (int y = startY-tileH; y < getHeight() + tileH; y += tileH)
            {
                canvas.drawImage(bg, x, y);
            }
        }
        setBackground(canvas);
    }
    
    public void LevelUp() {
        addObject(new LevelUp(), 640, 360);
        LEVELUP = true;
    }
    
    public void stop() {
        for (Enemy enemy : getObjects(Enemy.class)) enemy.stop                          = true;
        for (Projectile projectile : getObjects(Projectile.class)) projectile.stop      = true;
        for (Attack Atk : getObjects(Attack.class)) Atk.stop                            = true;
        for (Attack_Item Atk_I : getObjects(Attack_Item.class)) Atk_I.stop              = true;
        for (EXP exp : getObjects(EXP.class)) exp.stop                                  = true;
        getObjects(Player.class).get(0).stop                                            = true;
        getObjects(Pointer.class).get(0).stop                                           = true;
        getObjects(TimeCountdown.class).get(0).stop                                     = true;
    }
    
    public void resume() {
        for (Enemy enemy : getObjects(Enemy.class)) enemy.stop                          = false;
        for (Projectile projectile : getObjects(Projectile.class)) projectile.stop      = false;
        for (Attack Atk : getObjects(Attack.class)) Atk.stop                            = false;
        for (Attack_Item Atk_I : getObjects(Attack_Item.class)) Atk_I.stop              = false;
        for (EXP exp : getObjects(EXP.class)) exp.stop                                  = false;
        getObjects(Player.class).get(0).stop                                            = false;
        getObjects(Pointer.class).get(0).stop                                           = false;
        getObjects(TimeCountdown.class).get(0).stop                                     = false;
    }
}
