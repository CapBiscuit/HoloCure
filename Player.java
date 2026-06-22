import greenfoot.*;
import java.util.ArrayList;

public class Player extends Actor
{
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
    double ATK_MOD = 1;
    int INVINCIBILITY = 0;
    int WeaponCooldown = 0;
    int BurstCooldown = 50;
    int BURST = 0;
    int CRT = 5;
    ArrayList<Attack_Item> attacks = new ArrayList<Attack_Item>();
    
    // Exp
    int Exp = 0;
    int level = 1;
    int EXP_CAP = 5;

    // Movement
    String STATUS = "stand"; // stand // walk //
    boolean facingRight = false;
    double WALK_SPEED = 4;

    // Attack
    int attackCooldown = 0;
    public Player(String charName, int character)
    {
        this.charName = charName;
        standSets = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/" + charName + "/" + charName + ".png"), 6,2,0,3,2);
        moveSets  = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/" + charName + "/" + charName + ".png"), 6,2,1,6,2);
        setImage(standSets[0]);
        attacks.add(new Attack_Item(character));
        updateStats(charName);
    }
    
    public void act()
    {
        if (this != null && !stop) {
            movements();
            death();
            update();
        }
    }
    
    public void movements()
    {
        double xSpeed = 0;
        double ySpeed = 0;
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
        
        double newX = worldX + (buttonPressed >= 2 ? xSpeed / Math.sqrt(2) : xSpeed);
        double newY = worldY + (buttonPressed >= 2 ? ySpeed / Math.sqrt(2) : ySpeed);
        
        Game game = (Game)getWorld();
        
        if (game == null) return;
        if (game instanceof HoloOffice) {
            if (newY < 995)  newY = 995;   
            if (newY > 2025) newY = 2025;  
        }
        
        double mn = 2;
        if (game instanceof HalloweenCastle) {
        
            if (newX < 233 * mn)  newX = 233 * mn;
            if (newX > 1766 * mn) newX = 1766 * mn;
        
            if (newY < 520 * mn)  newY = 520 * mn;
            if (newY > 1535 * mn) newY = 1535 * mn;
        
            if (newX < 745 * mn && newY < 765 * mn) {
                if (worldX >= 745 * mn) newX = 745 * mn;
                else newY = 765 * mn;
            }
        
            if (newX > 1256 * mn && newY < 765 * mn) {
                if (worldX <= 1256 * mn) newX = 1256 * mn;
                else newY = 765 * mn;
            }
        }
    
        worldX = newX;
        worldY = newY;
        
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
    
    public void getHealed(int amount) {
        HP += amount;
        if (HP>HP_CAP) HP=HP_CAP;
    }
    
    public void getDamaged(int damage) {
        if (HP != 0 && INVINCIBILITY == 0) {
            HP -= damage;
            Greenfoot.playSound("game/hit_player.mp3");
            INVINCIBILITY = 100;
        }
    }
    
    public void death()
    {
        if (HP < 0) ((Game) getWorld()).endgame();
        if (INVINCIBILITY != 0) INVINCIBILITY--;
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
     * Increases EXP_CAP 1.3 times.
     */
    public void increaseExp(int amount)
    {
        Exp += amount;
        if (Exp >= EXP_CAP) {
            Game game = (Game)getWorld();
            Greenfoot.playSound("game/level.mp3");
            game.LevelUp();
            level++;
            Exp -= EXP_CAP;
            EXP_CAP = (int)(EXP_CAP * 1.3);
        }
    }
    
    public void updateStats(String charName) {
        switch(charName) {
            case "amelia":
                HP_CAP = 75;
                HP = HP_CAP;
                ATK_MOD = 1.3;
                WALK_SPEED *= 1.35;
                CRT = 10;
                break;
            case "gura":
                HP_CAP = 65;
                HP = HP_CAP;
                ATK_MOD = 1.1;
                WALK_SPEED *= 1.4;
                CRT = 5;
                break;
            case "ina":
                HP_CAP = 75;
                HP = HP_CAP;
                ATK_MOD = 0.9;
                WALK_SPEED *= 1.5;
                CRT = 1;
                break;
            case "kiara":
                HP_CAP = 90;
                HP = HP_CAP;
                ATK_MOD = 1;
                WALK_SPEED *= 1.4;
                CRT = 5;
                break;
            case "mori":
                HP_CAP = 70;
                HP = HP_CAP;
                ATK_MOD = 1.15;
                WALK_SPEED *= 1.3;
                CRT = 10;
                break;
            case "cecilia":
                HP_CAP = 100;
                HP = HP_CAP;
                ATK_MOD = 1;
                WALK_SPEED *= 1.2;
                CRT = 10;
                break;
            case "filian":
                HP_CAP = 60;
                HP = HP_CAP;
                ATK_MOD = 1.15;
                WALK_SPEED *= 1.4;
                CRT = 5;
                break;
            case "neuro":
                HP_CAP = 85;
                HP = HP_CAP;
                ATK_MOD = 1.3;
                WALK_SPEED *= 0.8;
                CRT = 4;
                break;
            case "caine":
                HP_CAP = 80;
                HP = HP_CAP;
                ATK_MOD = 1.2;
                WALK_SPEED *= 1.1;
                CRT = 5;
                break;
        }
    }
}