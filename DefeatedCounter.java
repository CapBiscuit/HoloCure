import greenfoot.*;

public class DefeatedCounter extends Actor {
    int defeatedCount;
    
    public DefeatedCounter() {
        defeatedCount = 0;
        updateImage();
    }

    public void increaseAmount(int amount) {
        defeatedCount += amount;
        updateImage();
    }
    
    public void updateImage() {
        GreenfootImage img = new GreenfootImage(150, 40);
        img.setColor(Color.WHITE);
        img.setFont(new Font("Arial", true, false, 20));
        img.drawString("Defeated: " + defeatedCount, 10, 25);
        setImage(img);
    }
}