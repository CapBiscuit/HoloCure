import greenfoot.*;

public class FriendlyProjectile extends World_objects
{
    int speed = 5;
    int rotation;
    
    public FriendlyProjectile(int rotation)
    {
        GreenfootImage img = new GreenfootImage(15, 15);
        img.setColor(new Color(255,255,170,255));
        img.fillOval(0, 0, 15, 15);
        setImage(img);
        this.rotation = rotation;
        setRotation(rotation);
    }
    
    public void act()
    {
        move(speed);
        Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
        if (isAtEdge()) getWorld().removeObject(this);
        if (enemy != null) {
            enemy.death();
            getWorld().removeObject(this);
        }
    }
}