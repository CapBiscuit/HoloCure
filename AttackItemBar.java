import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AttackItemBar extends Actor
{
    public AttackItemBar() {
        setImage(new GreenfootImage("UI/AttackItemBar_1.png"));
    }
    
    public void act()
    {
        update();
    }
    
    public void update() {
        Player player = getWorld().getObjects(Player.class).get(0);
        setImage(new GreenfootImage("UI/AttackItemBar_" + player.attacks.size() + ".png"));
        if (player.attacks.size() >= 1) getWorld().addObject(player.attacks.get(0), 130, 95);
        if (player.attacks.size() >= 2) getWorld().addObject(player.attacks.get(1), 180, 95);
        if (player.attacks.size() >= 3) getWorld().addObject(player.attacks.get(2), 230, 95);
        if (player.attacks.size() >= 4) getWorld().addObject(player.attacks.get(3), 280, 95);
        if (player.attacks.size() >= 5) getWorld().addObject(player.attacks.get(4), 330, 95);
        if (player.attacks.size() >= 6) getWorld().addObject(player.attacks.get(5), 380, 95);
    }
}