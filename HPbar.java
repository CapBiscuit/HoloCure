import greenfoot.*;

/**
 * Creates HP bar and updates it when Player is getting/losing HP
 */

public class HPbar extends Actor
{
    GreenfootImage[] bar = new GreenfootImage[10];
    public HPbar() {
        bar = SpriteSheetHandler.splitSheetVertical(new GreenfootImage("UI/HP_bar.png"),1,10,0,10,4);
        setImage(bar[0]);
    }
    
    public void act() {
        updateBar();
    }
    
    private void updateBar() {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        bar = SpriteSheetHandler.splitSheetVertical(new GreenfootImage("UI/HP_bar.png"),1,10,0,10,4);
        if (player.HP > 0) setImage(bar[10-player.HP/10]);
    }
}
