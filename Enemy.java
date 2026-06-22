import greenfoot.*;

public class Enemy extends World_objects
{
    GreenfootImage[] frames = new GreenfootImage[3];
    GreenfootImage limpid = new GreenfootImage("limpid.png");
    
    int frameIndex = 0;
    int animDelay = 0;
    int ANIM_SPEED = 10;
    int WALK_SPEED = 1;
    boolean facingRight = false;
    boolean stop = false;
    int HP = 200;
    int ATK = 1;
    int timer_amelia = 0;
    int stored_damage = 0;
    boolean is_KO = false;

    public Enemy(String enemyName) {
        updateStats(enemyName);
    }
    
    public void act()
    {
        if (!stop) {
            moveTowardsPlayer();
            animate();
            hitPlayer();
            if (timer_amelia > 0) {
                timer_amelia--;
            }
            else if (timer_amelia == 0) {
                HP -= stored_damage;
            }
            if (HP <= 0 || is_KO) death();
        }
    }
    
    public void updateStats(String enemyName) {
        switch(enemyName) {
            case "chumbud": 
                HP = 8;
                ATK = 2;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/chumbud.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "deadbeat": 
                HP = 40;
                ATK = 4;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/deadbeat.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "takodachi": 
                HP = 80;
                ATK = 4;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/takodachi.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "kfp": 
                HP = 20;
                ATK = 2;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/kfp.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "investigator": 
                HP = 40;
                ATK = 4;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/investigator.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "sukonbu": 
                HP = 10;
                ATK = 3;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/sukonbu.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "miofa": 
                HP = 40;
                ATK = 5;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/miofa.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "koronesuki": 
                HP = 120;
                ATK = 6;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/koronesuki.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "onigiriya": 
                HP = 80;
                ATK = 7;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/onigiriya.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "matsurisu": 
                HP = 12;
                ATK = 3;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/matsurisu.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "haaton": 
                HP = 45;
                ATK = 4;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/haaton.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "kapumin": 
                HP = 90;
                ATK = 8;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/kapumin.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
            case "rosetai": 
                HP = 140;
                ATK = 9;
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("enemies/rosetai.png"), 3,1,0,3,2);
                setImage(frames[0]);
                break;
        }
    }
    
    public void moveTowardsPlayer()
    {
        Game game = (Game)getWorld();
        Player player = (Player)game.getObjects(Player.class).get(0);
        double dx = worldX - player.worldX;
        double dy = worldY - player.worldY;
        
        if (dx > game.WORLD_WIDTH / 2.0)   dx -= game.WORLD_WIDTH;
        else if (dx < -game.WORLD_WIDTH / 2.0) dx += game.WORLD_WIDTH;
        
        if (dy > game.WORLD_HEIGHT / 2.0)  dy -= game.WORLD_HEIGHT;
        else if (dy < -game.WORLD_HEIGHT / 2.0) dy += game.WORLD_HEIGHT;
        
        double distance = Math.sqrt(dx*dx + dy*dy);
        
        if (distance < 5) return;
        
       
        facingRight = (dx < 0);
        worldX -= (dx / distance) * WALK_SPEED;
        worldY -= (dy / distance) * WALK_SPEED;
        game.wrap_object(this);
        
    }

    public void animate()
    {
        if (getX() < 5 || getX() > 1275 || getY() < 5 || getY() > 715) { setImage(limpid); return; } 
        animDelay--;
        if (animDelay <= 0) {
            animDelay = ANIM_SPEED;
            frameIndex = (frameIndex + 1) % frames.length;
            GreenfootImage img = new GreenfootImage(frames[frameIndex]);
            if (!facingRight && frames[frameIndex] != null) img.mirrorHorizontally();
            setImage(img);
        }
    }

    public void hitPlayer() {
        Player player = (Player) getOneIntersectingObject(Player.class);
        if (player != null) player.getDamaged(ATK);
    }
    
    
    public void hit(int damage) {
        HP -= damage;
        if (timer_amelia > 0) {
            stored_damage += (int)(damage * 0.15);
        }
        Greenfoot.playSound("game/enemy_hit.mp3");
    }
    
    
    public void ameliaAwakening() {
        if (timer_amelia == 0) timer_amelia = 120;
    }
    
    
    public void calliopeAwakening() {
        if (Greenfoot.getRandomNumber(10) == 9) {
            is_KO = true;
        }
    }
    
    
    public void death()
    {
        EXP exp = new EXP();
        exp.worldX = worldX;
        exp.worldY = worldY;
        getWorld().addObject(exp, 0, 0);
        
        DefeatedCounter counter = (DefeatedCounter) getWorld().getObjects(DefeatedCounter.class).get(0);
        counter.increaseAmount(1);
        getWorld().removeObject(this);
    }
}