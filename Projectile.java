import greenfoot.*;

public class Projectile extends World_objects
{
    int speed = 5;
    int rotation;
    int distance = 0;
    boolean stop = false;
    public Projectile(int rotation)
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
        if (!stop) {
            double radians = Math.toRadians(rotation);
            worldX += Math.cos(radians) * speed;
            worldY += Math.sin(radians) * speed;
            
            distance++;
            
            Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
            if (isAtEdge() || distance == 100) getWorld().removeObject(this);
            if (enemy != null) {
                enemy.hit(1);
                getWorld().removeObject(this);
            }
        }   
    }
}