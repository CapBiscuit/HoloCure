import greenfoot.*;
import java.util.ArrayList;

public class Projectile extends World_objects
{
    int speed = 6;
    int rotation;
    int distance = 0;
    int DISTANCE_MAX = 100;
    boolean stop = false;
    int damage = 0;
    int hit_limit = 0;
    ArrayList<Enemy> hit_enemies = new ArrayList<Enemy>();
    int ricochet = 0;
    boolean awakened = false;
    
    public Projectile(int rotation, int dmg, int h_l, int level)
    {
        hit_limit = h_l;
        damage = dmg;
        GreenfootImage img = new GreenfootImage(15, 15);
        img.setColor(new Color(255,255,170,255));
        img.fillOval(0, 0, 15, 15);
        setImage(img);
        this.rotation = rotation;
        setRotation(rotation);
        if (level >= 4) {
            ricochet = 2;
        }
        if (level == 7) {
            awakened = true;
        }
    }
    
    public void act()
    {
        if (!stop) {
            double radians = Math.toRadians(rotation);
            worldX += Math.cos(radians) * speed;
            worldY += Math.sin(radians) * speed;
            
            distance++;
            
            boolean fl = false;
            ArrayList<Enemy> enemies = (ArrayList<Enemy>)getIntersectingObjects(Enemy.class);
            if (isAtEdge() || distance == DISTANCE_MAX) getWorld().removeObject(this);
            for (int i = 0; i < enemies.size(); i++) {
                for (int j = 0; j < hit_enemies.size(); j++) {
                    if (hit_enemies.get(j) == enemies.get(i)) fl = true;
                }
                
                if (!fl && getWorld() != null) {
                    hit_enemies.add(enemies.get(i));
                    enemies.get(i).hit(damage);
                    if (awakened) {
                        enemies.get(i).ameliaAwakening();
                    }
                    if (hit_limit == 0) {
                        if (ricochet == 0) {
                            getWorld().removeObject(this);
                            break;
                        }
                        else {
                            ricochet--;
                            rotation = Greenfoot.getRandomNumber(360);
                        }
                    }
                    else hit_limit--;
                }
                if (getWorld() == null) break;
            }
        }   
    }
}