import greenfoot.*;

/**
 * Endscreen world that appears after
 * Player's HP dropping to 0
 * or when the time runs out
 * Picks random endscreen (total 6)
 */

public class GameOver extends World {
    public GameOver() {    
        super(1280, 720, 1);
        setBackground("end/CCTV_" + (Greenfoot.getRandomNumber(6)+1) + ".png");
        GreenfootSound music = new GreenfootSound("Let this ensemble begin! holoJustice.mp3");
        music.playLoop();
    }
}
