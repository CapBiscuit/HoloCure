import greenfoot.*;

public class TimeCountdown extends Actor {
    int timeInSeconds;      // remaining seconds
    int actCounter;         // counts acts to decrement each second
    int ACTS_PER_SECOND = 60;
    
    public TimeCountdown(int startSeconds) {
        timeInSeconds = startSeconds;
        actCounter = 0;
        updateImage();
    }
    
    public void act() {
        actCounter++;
        if (actCounter >= ACTS_PER_SECOND) {
            actCounter = 0;
            if (timeInSeconds > 0) {
                timeInSeconds--;
                updateImage();
                if (timeInSeconds == 0) Greenfoot.setWorld(new GameOver());
            }
        }
    }

    private void updateImage() {
        GreenfootImage img = new GreenfootImage(120, 40);
        img.setColor(Color.WHITE);
        img.setFont(new Font("Arial", true, false, 20));
        img.drawString("Time: " + timeInSeconds, 10, 25);
        setImage(img);
    }
    
}