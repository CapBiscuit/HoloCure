import greenfoot.*;
import java.util.ArrayList;

public class Projectile extends World_objects
{
    int speed = 5;
    int rotation;
    int distance = 0;
    boolean stop = false;
    int damage = 0;
    int hit_limit = 0;
    ArrayList<Enemy> hit_enemies = new ArrayList<Enemy>();
    
    public Projectile(int rotation, int dmg, int h_l)
    {
        hit_limit = h_l;
        damage = dmg;
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
            
            boolean fl = false;
            Enemy enemy = (Enemy) getOneIntersectingObject(Enemy.class);
            if (isAtEdge() || distance == 100) getWorld().removeObject(this);
            for (int i = 0; i < hit_enemies.size(); i++) {
                if (hit_enemies.get(i) == enemy) fl = true;
            }
            if (enemy != null && !fl) {
                hit_enemies.add(enemy);
                enemy.hit(damage);
                getWorld().removeObject(this);
            }
        }   
    }
}