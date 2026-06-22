import greenfoot.*;
public class HoloOffice extends Game
{
    String pole;
    public HoloOffice(String charName, int character)
    {
        pole = "holooffice";
        music = new GreenfootSound("OST/HoloCure OST - Holo Office (Stage 2).mp3");
        bg = new GreenfootImage("stages/holooffice/Этаж_полный.png");
        
        bg.scale(bg.getWidth() * 2,bg.getHeight() * 2);
        
        WORLD_WIDTH  = bg.getWidth() ;   
        WORLD_HEIGHT = bg.getHeight() ;  
        
        addObject(new UI(new GreenfootImage("characters/" + charName + "/portrait.png")),50,75);
        addObject(new EXPBAR(), 640, 20);
        
        player = new Player(charName, character);
        player.worldX = WORLD_WIDTH/2;
        player.worldY = WORLD_HEIGHT/2;
        addObject(player, getWidth()/2, getHeight()/2);
        addObject(player.attacks.get(0), 145, 115);
        
        spawnProps();
    }
    
    
    public void spawn_lokal()
    {
        if (spawnTimer <= 0) {
            spawn_l(new Chumbud());
            spawnTimer = SPAWN_DELAY;
        }
    }
    
    public void spawn_l(Enemy enemy) //o_0
    {
        System.out.println(" ccccc ");
        double angle = Math.toRadians(Greenfoot.getRandomNumber(360));
        double distance = 600 + Greenfoot.getRandomNumber(400);
        enemy.worldX = player.worldX + Math.cos(angle) * distance;
        enemy.worldY = player.worldY + Math.sin(angle) * distance;
        if (enemy.worldY < 1050) enemy.worldY = 1050;
        if (enemy.worldY > 1950) enemy.worldY = 1950;
        wrap_object(enemy);
        addObject(enemy, 0, 0);
    }
    
    private void spawnProps()
    {
        
    }
}
  
   