import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Attack_Item extends Actor
{
    boolean stop = false;
    int cooldown_maximum = 0;
    int cooldown_at_the_moment = 0;
    int damage = 100;
    double size = 2;
    int amount_atm = 0;
    int burst_cd = 0;
    int amount = 0;
    int index = -1;
    int hit_limit = 0;
    int type = 0;
    boolean is_shooting = false;
    
    Attack_Item(int ind) {
        index = ind;
        switch(index) {
            case 0: {
                type = 1;
                damage = 10;                
                amount = 1;
                cooldown_maximum = 90;
                size *= 1;
                break;
            }
            case 1: {
                type = 2;
                damage = 10;
                amount = 3;
                cooldown_maximum = 20;
                hit_limit = 1;
                burst_cd = 10;
                GreenfootImage weapon_icon = new GreenfootImage("Weapons/Amelia.png");
                weapon_icon.scale(40, 40);
                setImage(weapon_icon);
                break;
            }
            case 2: {
                type = 1;
                damage = 16;
                amount = 1;
                cooldown_maximum = 70;
                size *= 1.4;
                GreenfootImage weapon_icon = new GreenfootImage("Weapons/Gawr.png");
                weapon_icon.scale(40, 40);
                setImage(weapon_icon);
                break;
            }
            case 3: {
                type = 1;
                damage = 14;
                amount = 1;
                cooldown_maximum = 100;
                size *= 1.5;
                GreenfootImage weapon_icon = new GreenfootImage("Weapons/Ina.png");
                weapon_icon.scale(40, 40);
                setImage(weapon_icon);
                break;
            }
            case 4: {
                type = 1;
                damage = 13;
                cooldown_maximum = 70;
                amount = 1;
                size *= 0.6;
                GreenfootImage weapon_icon = new GreenfootImage("Weapons/Kiara.png");
                weapon_icon.scale(40, 40);
                setImage(weapon_icon);
                break;
            }
            case 5: {
                type = 1;
                damage = 12;
                cooldown_maximum = 90;
                amount = 1;
                size *= 1.5;
                GreenfootImage weapon_icon = new GreenfootImage("Weapons/Mori.png");
                weapon_icon.scale(40, 40);
                setImage(weapon_icon);
                break;
            }
            case 6: {
                break;
            }
            case 7: {
                type = 1;
                damage = 5;
                cooldown_maximum = 35;
                size *= 0.8;
                amount = 1;
                GreenfootImage weapon_icon = new GreenfootImage("Weapons/Filian.png");
                weapon_icon.scale(40, 40);
                setImage(weapon_icon);
                break;
            }
            case 8: {
                type = 4;
                damage = 10;
                cooldown_maximum = 80;
                size *= 1;
                amount = 1;
                GreenfootImage weapon_icon = new GreenfootImage("Weapons/Filian.png");
                weapon_icon.scale(40, 40);
                setImage(weapon_icon);
                break;
            }
        }
    }
    
    
    public void act()
    {
        if (!stop) {
            if (type == 1) melee();      
            else shoot();
        }
    }    
    
    public void melee()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        Player player = getWorld().getObjects(Player.class).get(0);
        if (mouse == null) return; // no mouse info available
        int angleDeg = (int) Math.toDegrees(Math.atan2(mouse.getY() - player.getY(), mouse.getX() - player.getX())); //Rotation
        
        if (cooldown_at_the_moment > 0) cooldown_at_the_moment--;
        if (cooldown_at_the_moment == 0) {
            int offsetX = 90 - Math.abs(angleDeg);
            int offsetY = (Math.abs(angleDeg) <= 90) ? angleDeg : (Math.abs(angleDeg) == angleDeg) ? 180 - angleDeg : (180 + angleDeg) * -1;
            Attack attack = new Attack(index, angleDeg, size, damage);
            getWorld().addObject(attack, player.getX() + offsetX, player.getY() + offsetY);
            cooldown_at_the_moment = cooldown_maximum;
        }
    }
    
    
    public void shoot() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        Player player = getWorld().getObjects(Player.class).get(0);
        if (mouse == null) return; // no mouse info available
        int angleDeg = (int) Math.toDegrees(Math.atan2(mouse.getY() - player.getY(), mouse.getX() - player.getX())); //Rotation
        if (cooldown_at_the_moment == 0 && !is_shooting) {
            amount_atm = amount;
            is_shooting = true;
            cooldown_at_the_moment = burst_cd;
        }
        else if (amount_atm != 0 && cooldown_at_the_moment == 0 && is_shooting) {
            Projectile projectile = new Projectile(angleDeg, damage, hit_limit);
            projectile.worldX = player.worldX;
            projectile.worldY = player.worldY;
            getWorld().addObject(projectile, 0, 0);
            cooldown_at_the_moment = burst_cd;
            amount_atm--;
        }
        else if (amount_atm == 0 && is_shooting) {
            is_shooting = false; 
            cooldown_at_the_moment = cooldown_maximum;
        }
        cooldown_at_the_moment--;
    }
}