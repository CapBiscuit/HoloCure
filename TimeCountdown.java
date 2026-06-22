import greenfoot.*;

public class TimeCountdown extends Actor {
    int sec = 0;
    int min = 0;
    int actCounter = 0;         // counts acts to decrement each second
    int ACTS_PER_SECOND = 60;
    boolean stop = false;
    GreenfootImage TIMER = new GreenfootImage(80, 28);
    GreenfootImage[] numbers = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("font/num.png"),10,1,0,10,2);
    GreenfootImage colon = new GreenfootImage("font/colon.png");
    
    public TimeCountdown() {
        colon.scale(15,21);
        TIMER.clear();
        TIMER.drawImage(numbers[min/10], 0, 0);
        TIMER.drawImage(numbers[min%10], 16, 0);
        TIMER.drawImage(colon, 32, 0);
        TIMER.drawImage(numbers[sec/10], 48, 0);
        TIMER.drawImage(numbers[sec%10], 64, 0);
        setImage(TIMER);
    }
    
    public void act() {
        if (!stop) {
            actCounter++;
            if (actCounter >= ACTS_PER_SECOND) {
                actCounter = 0;
                sec++;
                if (sec == 60) { sec -= 60; min++; }
                update();
            }
        }
    }

    private void update() {
        colon.scale(15,21);
        TIMER.clear();
        TIMER.drawImage(numbers[min/10], 0, 0);
        TIMER.drawImage(numbers[min%10], 16, 0);
        TIMER.drawImage(colon, 32, 0);
        TIMER.drawImage(numbers[sec/10], 48, 0);
        TIMER.drawImage(numbers[sec%10], 64, 0);
        setImage(TIMER);
    }
    
}