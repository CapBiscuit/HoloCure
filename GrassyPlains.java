import greenfoot.*;
public class GrassyPlains extends Game
{
    String pole;
    public GrassyPlains(String charName, int character)
    {
        pole = "grassyplains";
        music = new GreenfootSound("OST/HoloCure OST - Grassy Plains (Stage 1).mp3");
        bg = new GreenfootImage("stages/grassyplains/background.png");
        
        bg.scale(bg.getWidth() * 2,bg.getHeight() * 2);
        
        WORLD_WIDTH  = bg.getWidth() ;   
        WORLD_HEIGHT = bg.getHeight() ;  
        
        addObject(new UI(new GreenfootImage("characters/" + charName + "/portrait.png")),50,75);
        addObject(new EXPBAR(), 640, 20);
        
        player = new Player(charName, character);
        player.worldX = 0;
        player.worldY = 0;
        addObject(player, getWidth()/2, getHeight()/2);
        addObject(player.attacks.get(0), 145, 115);
        
        spawnProps();
    }
    
    public void spawn_lokal()
    {
        if (spawnTimer <= 0) {
            spawn(new Deadbeat());
            spawnTimer = SPAWN_DELAY;
        }
    }
    
    private void spawnProps()
    {
        for (int i = 0; i < 35; i++) {
            double x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            double y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
            String flower = "цветок" + (Greenfoot.getRandomNumber(7) + 1) + ".png";
            addObject(new Prop(flower, pole , x, y), 0, 0);
        }

        for (int i = 0; i < 12; i++) {
            double x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            double y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
            String tree = "дерево" + (Greenfoot.getRandomNumber(2) + 1) + ".png";
            addObject(new Prop(tree, pole , x, y), 0, 0);
        }

        for (int i = 0; i < 8; i++) {
            double x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            double y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
            String col = "колонна" + (Greenfoot.getRandomNumber(2) + 1) + ".png";
            addObject(new Prop(col, pole , x, y, true), 0, 0);
        }

        for (int i = 0; i < 6; i++) {
            double x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            double y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
            addObject(new Prop("забор_вертикаль.png", pole , x, y, true), 0, 0);
        }

        for (int i = 0; i < 5; i++) {
            double x = Greenfoot.getRandomNumber((int)WORLD_WIDTH);
            double y = Greenfoot.getRandomNumber((int)WORLD_HEIGHT);
            addObject(new Prop("забор_горизонт1.png", pole , x, y, true), 0, 0);
        }
    }
}