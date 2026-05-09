import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EXPbar extends Actor
{
    GreenfootImage[] bar = new GreenfootImage[10];
    public EXPbar() {
        bar = SpriteSheetHandler.splitSheetVertical(new GreenfootImage("UI/EXP_bar.png"),1,62,0,62,2);
        setImage(bar[0]);
    }
    
    public void act() {
        updateBar();
    }
    
    private void updateBar() {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        bar = SpriteSheetHandler.splitSheetVertical(new GreenfootImage("UI/EXP_bar.png"),1,62,0,62,2);
        setImage(bar[player.Exp * 62/player.EXP_CAP]);
    }
}
