import greenfoot.*;

public class EXP extends World_objects
{
    GreenfootImage img = new GreenfootImage("misc/EXP.png");
    GreenfootImage limpid = new GreenfootImage("limpid.png");
    int WALK_SPEED = 3;
    public EXP()
    {
        img.scale(30, 30);
        setImage(img);
    }

    public void act()
    {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        if (isTouching(Player.class)) {
            player.increaseExp(1);
            Greenfoot.playSound("game/exp_gain.mp3");
            getWorld().removeObject(this);
            return;
        }
        if (getX() < 5 || getX() > 1275 || getY() < 5 || getY() > 715)
        { setImage(limpid); } else { setImage(img); }
        moveTowardsPlayer();
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
        if (distance <= 150){
            worldX -= (dx / distance) * WALK_SPEED;
            worldY -= (dy / distance) * WALK_SPEED;
            game.wrap_object(this);
        }
        
    }
}