import greenfoot.*;

/**
 * Intro world
 * Game starts with intro
 * 8 Frames that changes after FRAME_DURATION amount of frames
 * Can be skipped by clicking spacebar
 * At the end changes world to Menu
 */

public class Intro extends World
{
    GreenfootImage[] frames = new GreenfootImage[8];
    int frameIndex = 0;
    int frameTimer = 0;
    int FRAME_DURATION = 280;
    
    GreenfootSound music = new GreenfootSound("HoloCure OST - Intro (Save the Fans).mp3");
    
    public Intro() {
        super(1280, 720, 1);
        for (int i = 0; i < 8; i++) {
            frames[i] = new GreenfootImage("intro/intro_" + (i+1) + ".png");
            frames[i].scale(getWidth(), getHeight());
        }
        setBackground(frames[0]);
        
    }

    public void act() {
        music.play();
        if (Greenfoot.isKeyDown("space")) end();
        frameTimer++;
        if (frameTimer >= FRAME_DURATION) {
            frameTimer = 0;
            frameIndex++;
            if (frameIndex < frames.length) setBackground(frames[frameIndex]);
            else end();
        }
    }
    
    public void end() {
        music.pause();
        Greenfoot.setWorld(new Menu());
    }
}