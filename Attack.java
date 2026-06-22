import greenfoot.*;
import java.util.ArrayList;


public class Attack extends Actor
{
    boolean stop = false;
    // Animation
    GreenfootImage[] frames;
    int frameIndex = 0;
    int animationDelay = 0;
    int animationInterval = 5;
    int damage = 0;
    
    // Stats
    int range = 150;
    ArrayList<Enemy> enemies_hit = new ArrayList<Enemy>();

    public Attack(int weapon_index, int rotation, double attack_size, int dmg)
    {
        switch (weapon_index) {
            case 0: 
                frames = new GreenfootImage[6];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/suisei/attack.png"), 6,1,0,6,attack_size);
                break;
            case 2:
                frames = new GreenfootImage[4];
                frames = SpriteSheetHandler.splitSheetVertical(new GreenfootImage("characters/gura/attack.png"), 1,4,0,4,attack_size);
                break;
            case 3: 
                frames = new GreenfootImage[12];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/ina/attack.png"), 12,1,0,12,attack_size);
                break;
            case 4: 
                frames = new GreenfootImage[8];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/kiara/attack.png"), 8,1,0,8,attack_size);
                break;
            case 5:
                frames = new GreenfootImage[10];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/mori/attack.png"), 10,1,0,10,attack_size);
                break;
            case 6:
                frames = new GreenfootImage[6];
                frames = SpriteSheetHandler.splitSheetVertical(new GreenfootImage("characters/cecilia/attack.png"), 1,6,0,6,attack_size);
                break;
            case 7:
                frames = new GreenfootImage[6];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/filian/attack.png"), 6,1,0,6,attack_size);
                break;
            case 8:
                frames = new GreenfootImage[6];
                frames = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/filian/attack.png"), 6,1,0,6,attack_size);
                break;
        }
        damage = dmg;
        setRotation(rotation);
        setImage(frames[0]);
    }

    public void act()
    {
        if (!stop) {
            ArrayList<Enemy> enemies = (ArrayList<Enemy>)getIntersectingObjects(Enemy.class);
            for (int j = 0; j < enemies.size(); j++) {
                boolean fl = false;
                for (int i = 0; i < enemies_hit.size(); i++) {
                    if (enemies_hit.get(i) == enemies.get(j)) fl = true;
                } 
                if (!fl) {
                    enemies.get(j).hit(damage);
                    enemies_hit.add(enemies.get(j));
                };
            }
            
            animationDelay--;
            if (animationDelay <= 0) {
                animationDelay = animationInterval;
                frameIndex++;
                if (frameIndex < frames.length) setImage(frames[frameIndex]);
                else getWorld().removeObject(this);
            }
        }
    }
}