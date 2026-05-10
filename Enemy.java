import greenfoot.*;

public class Enemy extends World_objects
{
    GreenfootImage[] frames = new GreenfootImage[3];
    GreenfootImage limpid = new GreenfootImage("limpid.png");//o_0
    
    int frameIndex = 0;
    int animDelay = 0;
    int ANIM_SPEED = 10;
    int WALK_SPEED = 2;
    boolean facingRight = false;

    public void act()
    {
        
        moveTowardsPlayer();
        animate();
        hitPlayer();
    }
    
    public void moveTowardsPlayer() //o_0
    {
        Player player = (Player)getWorld().getObjects(Player.class).get(0);
    
        double distanceX = worldX - player.worldX;
        double distanceY = worldY - player.worldY;
        if (Math.abs(distanceX) > Math.abs(distanceY))
        {
            if (distanceX > 0) {
                worldX -= 1;
                facingRight = false;
            }
            else if (distanceX < 0) {
                worldX += 1;
                facingRight = true;
            }
        }
        else
        {
            if (distanceY > 0) worldY -= 1;
            else if (distanceY < 0) worldY += 1;
        }
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
    
    public void death()
    {
        //getWorld().addObject(new EXP(), getX(), getY());
        EXP exp = new EXP();
        exp.worldX = worldX;
        exp.worldY = worldY;
        getWorld().addObject(exp, 0, 0);
        
        DefeatedCounter counter = (DefeatedCounter) getWorld().getObjects(DefeatedCounter.class).get(0);
        counter.increaseAmount(1);
        getWorld().removeObject(this);
    }
}