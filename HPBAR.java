import greenfoot.*;

/**
 * Creates HP bar and updates it when Player is getting/losing HP
 */

public class HPBAR extends Actor
{
    GreenfootImage HP = new GreenfootImage(500,20); //45+100*4+48+10
    GreenfootImage HP_hp = new GreenfootImage("UI/HP_hp.png");
    GreenfootImage HP_bar = new GreenfootImage("UI/HP_bar.png");
    GreenfootImage HP_lost = new GreenfootImage("UI/HP_lost.png");
    GreenfootImage[] numbers = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("font/numbers.png"),10,1,0,10,2);
    GreenfootImage colon = new GreenfootImage("font/colon.png");
    
    public void act() {
        update();
    }
    
    private void update() {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        
        if (player.HP > 0) {
            HP.clear();
            HP_hp.scale(45,20);
            HP_bar.scale((int)(player.HP*3.5),12);
            HP_lost.scale((int)(player.HP_CAP*3.5),12);
            
            HP.drawImage(HP_hp, 0, 0);
            HP.drawImage(HP_lost, 45, 3);
            HP.drawImage(HP_bar, 45, 3);
            
            colon.scale(10,14);
            if (player.HP/100 >=1)
            HP.drawImage(numbers[player.HP/100],        (int)(player.HP_CAP*3.5)+50, 0);
            if (player.HP/10%10 >=1 || player.HP/100 >=1)
            HP.drawImage(numbers[player.HP/10%10],      (int)(player.HP_CAP*3.5)+62, 0);
            HP.drawImage(numbers[player.HP%10],         (int)(player.HP_CAP*3.5)+74, 0);
            HP.drawImage(colon,                         (int)(player.HP_CAP*3.5)+86, 0);
            if (player.HP_CAP/100 >=1)
            HP.drawImage(numbers[player.HP_CAP/100],    (int)(player.HP_CAP*3.5)+98, 0);
            HP.drawImage(numbers[player.HP_CAP/10%10],  (int)(player.HP_CAP*3.5)+110, 0);
            HP.drawImage(numbers[player.HP_CAP%10],     (int)(player.HP_CAP*3.5)+122, 0);
            
            
        }
        
        setImage(HP);
    }
}
