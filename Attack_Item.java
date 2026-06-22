import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Attack_Item extends Actor
{
    boolean stop = false;
    int cooldown_maximum = 0;
    int cooldown_at_the_moment = 10;
    int damage = 100;
    double size = 2;
    int amount_atm = 0;
    int burst_cd = 0;
    int amount = 0;
    int index = -1;
    int hit_limit = 0;
    int type = 0;
    boolean is_shooting = false;
    int level = 1;
    int multi_hit_angle;
    
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
                amount = 3;
                cooldown_maximum = 70;
                size *= 1.4;
                burst_cd = 5;
                multi_hit_angle = 15;
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
                size *= 1.5;
                damage = 6;
                cooldown_maximum = 70;
                type = 1;
                GreenfootImage weapon_icon = new GreenfootImage("Weapons/Cecilia.png");
                weapon_icon.scale(40, 40);
                setImage(weapon_icon);
                
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
                type = 1;
                damage = 20;
                cooldown_maximum = 100;
                size *= 1.5;
                amount = 1;
                GreenfootImage weapon_icon = new GreenfootImage("Weapons/Caine.png");
                weapon_icon.scale(40, 40);
                setImage(weapon_icon);
                break;
            }
            case 9: {
                type = 4;
                damage = 10;
                cooldown_maximum = 80;
                size *= 1;
                amount = 2;
                burst_cd = 15;
                GreenfootImage weapon_icon = new GreenfootImage("Weapons/Neuro.png");
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
            else if (type == 4) random();
            else shoot();
        }
    }    
    
    public void melee()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        Player player = getWorld().getObjects(Player.class).get(0);
        if (mouse == null) return; // no mouse info available
        int angleDeg = (int) Math.toDegrees(Math.atan2(mouse.getY() - player.getY(), mouse.getX() - player.getX())) + (amount_atm - (amount / 2) - 1) * multi_hit_angle; //Rotation
        
        if (cooldown_at_the_moment > 0) cooldown_at_the_moment--;
        else if (cooldown_at_the_moment == 0 && is_shooting && amount_atm > 0) {
            int offsetX = 90 - Math.abs(angleDeg);
            int offsetY = (Math.abs(angleDeg) <= 90) ? angleDeg : (Math.abs(angleDeg) == angleDeg) ? 180 - angleDeg : (180 + angleDeg) * -1;
            Attack attack = new Attack(index, angleDeg, size, (int)(damage * player.ATK_MOD), 1);
            getWorld().addObject(attack, player.getX() + offsetX, player.getY() + offsetY);
            cooldown_at_the_moment = burst_cd;
            amount_atm--;
        }
        else if (is_shooting && amount_atm == 0) {
            is_shooting = false;
            cooldown_at_the_moment = cooldown_maximum;
        }
        else if (!is_shooting && cooldown_at_the_moment == 0) {
            is_shooting = true;
            amount_atm = amount;
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
            Projectile projectile = new Projectile(angleDeg, (int)(damage * player.ATK_MOD), hit_limit, level);
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
    
    
    public void random() {
        if (amount_atm > 0 && is_shooting && cooldown_at_the_moment <= 0) {
            int rand = Greenfoot.getRandomNumber(8);
            int dmg = 1;
            double sz = 1;
            switch(rand + 1) {
                case 1: {
                    dmg = 10;
                    break;
                }
                case 2: {
                    dmg = 16;
                    sz *= 1.4;
                    break;
                }
                case 3: {
                    dmg = 14;
                    sz *= 1.5;
                    break;
                }
                case 4: {
                    dmg = 13;
                    sz *= 0.6;
                    break;
                }
                case 5: {
                    dmg = 12;
                    sz *= 1.5;
                    break;
                }
                case 6: {
                    sz *= 1.5;
                    dmg = 6;
                    break;
                }
                case 7: {
                    dmg = 5;
                    sz *= 0.8;
                    break;
                }
                case 8: {
                    dmg = 20;
                    sz *= 1.5;
                    break;
                }
            }
            if (rand == 0) {
                MouseInfo mouse = Greenfoot.getMouseInfo();
                Player player = getWorld().getObjects(Player.class).get(0);
                if (mouse == null) return; // no mouse info available
                int angleDeg = (int) Math.toDegrees(Math.atan2(mouse.getY() - player.getY(), mouse.getX() - player.getX())); //Rotation
                Projectile projectile = new Projectile(angleDeg, (int)(damage * player.ATK_MOD * (double)(damage / 10)), hit_limit, 1);
                projectile.worldX = player.worldX;
                projectile.worldY = player.worldY;
                getWorld().addObject(projectile, 0, 0);
            }
            else {
                MouseInfo mouse = Greenfoot.getMouseInfo();
                Player player = getWorld().getObjects(Player.class).get(0);
                if (mouse == null) return; // no mouse info available
                int angleDeg = (int) Math.toDegrees(Math.atan2(mouse.getY() - player.getY(), mouse.getX() - player.getX())); //Rotation
                int offsetX = 90 - Math.abs(angleDeg);
                int offsetY = (Math.abs(angleDeg) <= 90) ? angleDeg : (Math.abs(angleDeg) == angleDeg) ? 180 - angleDeg : (180 + angleDeg) * -1;
                Attack attack = new Attack(rand + 1, angleDeg, sz * size, (int)(dmg * player.ATK_MOD * (double)(damage / 10)), 1);
                getWorld().addObject(attack, player.getX() + offsetX, player.getY() + offsetY);
            }
            cooldown_at_the_moment = burst_cd;
            amount_atm--;
        }
        else if (amount_atm == 0 && is_shooting && cooldown_at_the_moment == 0) {
            cooldown_at_the_moment = cooldown_maximum;
            is_shooting = false;
        }
        else if (amount_atm == 0 && cooldown_at_the_moment == 0 && !is_shooting) {
            cooldown_at_the_moment = cooldown_maximum;
            is_shooting = true;
            amount_atm = amount;
        }
        cooldown_at_the_moment--;
    }
    
    
    public void upgrade() {
        level++;
        switch (index) {
            case 1: {
                switch (level) {
                    case 2: {
                        hit_limit++;
                        amount = 5;
                    }
                    case 3: {
                        damage = (int)(damage * 1.2);
                    }
                    case 5: {
                        hit_limit++;
                        cooldown_maximum = (int)(cooldown_maximum * 0.75);
                    }
                    case 6: {
                        damage = (int)(damage * 1.2);
                    }
                }
            }
            case 2: {
                switch(level) {
                    case 2: damage = (int)(damage * 1.2);
                    case 3: amount++;
                    case 4: cooldown_maximum = (int)(cooldown_maximum * 0.85);
                    case 5: damage = (int)(damage * 1.4);
                    case 6: size = size * 1.25;
                    case 7: amount++;
                }
            }
            case 3: {
                switch(level) {
                    case 2: damage = (int)(damage * 1.2);
                    case 3: size = size * 1.15;
                    case 4: damage = (int)(damage * 1.3);
                    case 5: cooldown_maximum = (int)(cooldown_maximum * 0.9);
                    case 6: size = size * 1.1;
                }
            }
        }
    }
}