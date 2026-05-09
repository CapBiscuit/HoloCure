import greenfoot.*;

public class EnemyProjectile extends Actor
{
    int speed = 5;
    int rotation;  // degrees, easier with setRotation()
    
    public EnemyProjectile(int rotation)
    {
        GreenfootImage img = new GreenfootImage(15, 15);
        img.setColor(Color.RED);
        img.fillOval(0, 0, 15, 15);
        setImage(img);
        this.rotation = rotation;
        setRotation(rotation);
    }
    
    public void act()
    {
        move(speed);
        Player player = (Player) getOneIntersectingObject(Player.class);
        if (isAtEdge()) getWorld().removeObject(this);
        if (player != null) {
            player.getDamaged();
            getWorld().removeObject(this);
        }
    }
}