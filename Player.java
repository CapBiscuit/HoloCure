import greenfoot.*;

public class Player extends Actor
{
    // data о_0 // было бы неплохо вынести всю игровую статистику отдельно 
    public double worldX;
    public double worldY;
    
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
    int HP = 100;
    int ATK = 1;
    int INVINCIBILITY = 0;
    int WeaponCooldown = 0;
    int BurstCooldown = 50;
    int BURST = 0;
    
    // Exp
    int Exp = 0;
    int EXP_CAP = 5;

    // Movement
    String STATUS = "stand"; // stand // walk //
    boolean facingRight = false;
    int WALK_SPEED = 5;

    // Attack
    int attackCooldown = 0;
    public Player(String charName)
    {
        this.charName = charName;
        standSets = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/" + charName + "/" + charName + ".png"), 6,2,0,3,2);
        moveSets  = SpriteSheetHandler.splitSheetHorizontal(new GreenfootImage("characters/" + charName + "/" + charName + ".png"), 6,2,1,6,2);
        setImage(standSets[0]);
    }

    public void act()
    {
        if (this != null) {
            movements();
            if (charName == "amelia") shoot();
            else attack();
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
    
    public void shoot() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null) return; // no mouse info available
        int angleDeg = (int) Math.toDegrees(Math.atan2(mouse.getY() - getY(), mouse.getX() - getX())); //Rotation
        
        if (BURST >= BurstCooldown && WeaponCooldown == 10) {
            FriendlyProjectile projectile = new FriendlyProjectile(angleDeg);
            getWorld().addObject(projectile, getX(), getY());
        }
        if (WeaponCooldown == 10) WeaponCooldown = 0;
        if (BURST == 70) BURST = 0;
        WeaponCooldown++;
        BURST++;
    }
    
    /**
     * Creates Attack that deals damage to Enemies.
     * A little buggy, but whatever...
     */
    
    public void attack()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null) return; // no mouse info available
        int angleDeg = (int) Math.toDegrees(Math.atan2(mouse.getY() - getY(), mouse.getX() - getX())); //Rotation
        
        if (attackCooldown > 0) attackCooldown--;
        if (attackCooldown == 0) {
            int offsetX = 90 - Math.abs(angleDeg);
            int offsetY = (Math.abs(angleDeg) <= 90) ? angleDeg : (Math.abs(angleDeg) == angleDeg) ? 180 - angleDeg : (180 + angleDeg) * -1;
            Attack attack = new Attack(charName, angleDeg);
            getWorld().addObject(attack, getX() + offsetX, getY() + offsetY);
            attackCooldown = 60;
        }
    }

    public void movements()
    {
        int xSpeed = 0;
        int ySpeed = 0;

        if (Greenfoot.isKeyDown("up")|| Greenfoot.isKeyDown("w")) {
            ySpeed = -WALK_SPEED;
            STATUS = "walk";
        }
        if (Greenfoot.isKeyDown("left")|| Greenfoot.isKeyDown("a")) {
            xSpeed = -WALK_SPEED;
            facingRight = false;
            STATUS = "walk";
        }
        if (Greenfoot.isKeyDown("down")|| Greenfoot.isKeyDown("s")) {
            ySpeed = WALK_SPEED;
            STATUS = "walk";
        }
        if (Greenfoot.isKeyDown("right")|| Greenfoot.isKeyDown("d")) {
            xSpeed = WALK_SPEED;
            facingRight = true;
            STATUS = "walk";
        }
        if (!(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w") ||
              Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a") ||
              Greenfoot.isKeyDown("down")|| Greenfoot.isKeyDown("s") ||
              Greenfoot.isKeyDown("right")|| Greenfoot.isKeyDown("d"))) {
            STATUS = "stand";
        }

        //setLocation(getX() + xSpeed, getY() + ySpeed); о_0 забудьте о setLocation это миф
        worldX += xSpeed;
        worldY += ySpeed;
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
            animationDelay = animationInterval;
            if (STATUS == "walk") {
                frameIndexStand = 0;
                frameIndexMov = (frameIndexMov + 1) % moveSets.length;
            } else if (STATUS == "stand") {
                frameIndexMov = 0;
                frameIndexStand = (frameIndexStand + 1) % standSets.length;
            }
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
            ATK++;
            if (HP != 100) HP += 10;
            TimeCountdown time = (TimeCountdown) getWorld().getObjects(TimeCountdown.class).get(0);
            time.timeInSeconds += 50;
            Exp = 0;
            EXP_CAP +=5;
        }
    }
}
