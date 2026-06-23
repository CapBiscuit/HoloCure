import greenfoot.*;

/**
 * Endscreen world that appears after
 * Player's HP dropping to 0
 * or when the time runs out
 * Picks random endscreen (total 6)
 */

public class GameOver extends World {
    GreenfootSound music = new GreenfootSound("OST/Let this ensemble begin! holoJustice.mp3");
    
    public GameOver() {    
        super(1280, 720, 1);
        setBackground("end/CCTV_" + (Greenfoot.getRandomNumber(6)+1) + ".png");
        music.playLoop();
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("enter") || Greenfoot.isKeyDown("space")) switchToMenu();
    }
    
    public void switchToMenu() {
        music.pause();
        Greenfoot.setWorld(new Menu());
    }
}
