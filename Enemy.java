import greenfoot.*;

public class Enemy extends World_objects
{
    GreenfootImage[] frames = new GreenfootImage[3];
    
    int frameIndex = 0;
    int animDelay = 0;
    int ANIM_SPEED = 10;
    int WALK_SPEED = 1;
    boolean facingRight = false;
    boolean stop = false;
    int hp = 200;
    Attack last_hit_attack = null;

    public void act()
    {
        if (!stop) {
            moveTowardsPlayer();
            animate();
            hitPlayer();
            if (hp <= 0) death();
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
        if (getX() < 5 || getX() > 1275 || getY() < 5 || getY() > 715) //o_0// было бы непло считать эти граници относительно спрайтов мобов
        { setImage(limpid); return; } 
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
        if (player != null) player.getDamaged();
    }
    
    
    public void hit(int damage) {
        hp -= damage;
    }
    
    
    public void death()
    {
        EXP exp = new EXP();
        exp.worldX = worldX;
        exp.worldY = worldY;
        ((Game)getWorld()).wrap_object(exp);
        getWorld().addObject(exp, 0, 0);
        
        DefeatedCounter counter = (DefeatedCounter) getWorld().getObjects(DefeatedCounter.class).get(0);
        counter.increaseAmount(1);
        getWorld().removeObject(this);
    }
}