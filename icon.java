import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class icon extends Actor
{
    public icon(GreenfootImage img, int x, int y) {
        img.scale(x,y);
        setImage(new GreenfootImage(img));
    }
}
