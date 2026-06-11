import greenfoot.*;

public class TimeCountdown extends Actor {
    int sec = 0;
    int min = 0;
    int actCounter = 0;         // counts acts to decrement each second
    int ACTS_PER_SECOND = 60;
    boolean stop = false;
    
    public void act() {
        if (!stop) {
            actCounter++;
            if (actCounter >= ACTS_PER_SECOND) {
                actCounter = 0;
                sec++;
                if (sec == 60) { sec -= 60; min++; }
                updateImage();
            }
        }
    }

    private void updateImage() {
        GreenfootImage img = new GreenfootImage(120, 40);
        img.setColor(Color.WHITE);
        img.setFont(new Font("Arial", true, false, 20));
        img.drawString(min + " : " + sec, 10, 25);
        setImage(img);
    }
    
}