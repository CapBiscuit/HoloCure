import greenfoot.*;

public class EXPBAR extends Actor
{
    int frame = 0;
    
    GreenfootImage EXPBAR = new GreenfootImage(1280,56);
    GreenfootImage EXPBAR_background = new GreenfootImage("UI/EXPBAR_background.png");
    GreenfootImage[] EXPBAR_animation = SpriteSheetHandler.splitSheetVertical(new GreenfootImage("UI/EXPBAR_animation.png"),1,62,0,62,2);
    GreenfootImage EXPBAR_frame = new GreenfootImage("UI/EXPBAR_frame.png");
    
    
    public void act() 
    {
        update();
    }
    
    public void update() {
        
        EXPBAR_background.scale(1280, 56);
        EXPBAR_frame.scale(1280, 56);
        
        Player player = getWorld().getObjects(Player.class).get(0);
        GreenfootImage EXPBAR_animation_frame = new GreenfootImage(5+1280*player.Exp/player.EXP_CAP,30);
        
        EXPBAR_animation_frame.drawImage(EXPBAR_animation[frame], 0, -2);
        EXPBAR.drawImage(EXPBAR_background, 0, 0);
        EXPBAR.drawImage(EXPBAR_animation_frame, 0, 0);
        EXPBAR.drawImage(EXPBAR_frame, 0, 0);
        
        setImage(EXPBAR);
        frame = frame >= 61 ? 0 : frame + 1;
        //пусть так
    
    }
}
