import greenfoot.*;
import java.util.ArrayList;

public class Player extends Actor
{
    // data о_0 // было бы неплохо вынести всю игровую статистику отдельно 
    public double worldX;
    public double worldY;
    
    boolean stop = false;
    
    // Frames
    GreenfootImage[] standSets = new GreenfootImage[3];
    GreenfootImage[] moveSets = new GreenfootImage[6];
    
    int character;
    String charName;
    
    // Animation
    int frameIndexStand = 0;
    int frameIndexMov = 0;
    int animationDelay = 0;
    int animationInterval = 10;
    
    // Stats
    int HP_CAP = 100;
    int HP = 100;
    float ATK_MOD = 1;
    int INVINCIBILITY = 0;
    int WeaponCooldown = 0;
    int BurstCooldown = 50;
    int BURST = 0;
    ArrayList<Attack_Item> attacks = new ArrayList<Attack_Item>();
    
    // Exp
    int Exp = 0;
    int level = 1;
    int EXP_CAP = 5;

    // Movement
    String STATUS = "stand"; // stand // walk //
    boolean facingRight = false;
    int WALK_SPEED = 5;

    // Attack
    int attackCooldown = 0;
    public Player(String charName, int character)
    {
        this.charName = charName;
        standSets = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/" + charName + "/" + charName + ".png"), 6,2,0,3,1.5);
        moveSets  = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/" + charName + "/" + charName + ".png"), 6,2,1,6,1.5);
        setImage(standSets[0]);
        attacks.add(new Attack_Item(character));
    }

    public void act()
    {
        if (this != null && !stop) {
            movements();
            death();
            update();
        }
    }
    
    /**
     * Amelia Exclusive Attack  -  a  G u n    // Never give your VTuber a GUN
     * 
     * Creates a Burst of projectiles. 
     * It shoots every 10 frames, but can not shoot during BurstCooldown.
     * The lower BurstCooldown the more bullets are shot // Default is 3 bullets
     * With BurstCooldown = 0 shoots all the time
     * 
     * BURST (70 frames) Visualization: |-------Burst window (BurstCooldown)-------|------Bursting------|
     * Default Visualization:           |----------------------------------------|*|---------*---------*| 
     *                                                      50 frames             ^         20 frames
     *                    "*" bullet                                              |
     *                    "-" 1 frame                                        Bullet shot 
     *                                                                      at 50th frame
     */
    
    /**
     * Creates Attack that deals damage to Enemies.
     * A little buggy, but whatever...
     */

    public void movements()
    {
        int xSpeed = 0;
        int ySpeed = 0;
        int buttonPressed = 0;

        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
            ySpeed = -WALK_SPEED;
            buttonPressed++;
        }
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            xSpeed = -WALK_SPEED;
            facingRight = false;
            buttonPressed++;
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) {
            ySpeed = WALK_SPEED;
            buttonPressed++;
        }
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            xSpeed = WALK_SPEED;
            facingRight = true;
            buttonPressed++;
        }
        if (!(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w") ||
              Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a") ||
              Greenfoot.isKeyDown("down")|| Greenfoot.isKeyDown("s") ||
              Greenfoot.isKeyDown("right")|| Greenfoot.isKeyDown("d"))) {
            STATUS = "stand";
        } else STATUS = "walk";
        
        worldX += buttonPressed >= 2 ? xSpeed / Math.sqrt(2) : xSpeed;
        worldY += buttonPressed >= 2 ? ySpeed / Math.sqrt(2) : ySpeed;
        Game game = (Game)getWorld();
        if (game != null) {
            game.wrap_object(this); 
        }
        
        for (Prop p : game.getObjects(Prop.class)) {
        if (p.isSolid() && intersectsProp(p)) {
            worldX -= buttonPressed >= 2 ? xSpeed / Math.sqrt(2) : xSpeed;
            worldY -= buttonPressed >= 2 ? ySpeed / Math.sqrt(2) : ySpeed;
            game.wrap_object(this);
            break;
            }
        }
        }
        
        private boolean intersectsProp(Prop p)
    {
        double dx = Math.abs(worldX - p.worldX);
        double dy = Math.abs(worldY - p.worldY);
        return dx < (getImage().getWidth()/2 + p.getImage().getWidth()/2) &&
               dy < (getImage().getHeight()/2 + p.getImage().getHeight()/2);
    }
    
        public void death()
        {
            if (HP == 0) ((Game) getWorld()).endgame();
            if (INVINCIBILITY != 0) INVINCIBILITY--;
        }
    
    public void getDamaged() {
        if (HP != 0 && INVINCIBILITY == 0) {
            HP -= 10;
            INVINCIBILITY = 100;
        }
    }
    /**
     * Player's animation
     */
    public void update()
    {
        animationDelay--;
        if (animationDelay <= 0) {
            animationDelay  = animationInterval;
            frameIndexStand = STATUS == "walk"  ? 0 : (frameIndexStand + 1) % standSets.length;
            frameIndexMov   = STATUS == "stand" ? 0 : (frameIndexMov   + 1) % moveSets.length;
        }

        GreenfootImage img = (STATUS == "walk") ? moveSets[frameIndexMov] : standSets[frameIndexStand];

        if (!facingRight && img != null) {
            img = new GreenfootImage(img);
            img.mirrorHorizontally();
        }
        setImage(img);
    }

    /**
     * Increases Exp by 1.
     * When reached EXP_CAP:
     * Increases ATK by 1. // Not supported as of now, as Enemies dies immediatly
     * Heals Player by 10.
     * Increases Time left by 50 seconds. // Later remove as it's pointless
     * Increases EXP_CAP by 5.
     */
    public void increaseExp(int amount)
    {
        Exp += amount;
        if (Exp == EXP_CAP) {
            if (HP != 100) HP += 10;
            TimeCountdown time = (TimeCountdown) getWorld().getObjects(TimeCountdown.class).get(0);
            Exp = 0;
            EXP_CAP = (int)(EXP_CAP * 1.2);
        }
    }
}