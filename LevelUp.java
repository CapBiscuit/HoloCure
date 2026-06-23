import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class LevelUp extends Actor
{
    ArrayList<Boolean> is_Upgrade = new ArrayList<Boolean>();
    ArrayList<Integer> weaponInd = new ArrayList<Integer>();
    
    ArrayList<Integer> IDs = getUniqueRandomNumbers();
        
    public ArrayList<Integer> getUniqueRandomNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        
        while (numbers.size() < 4) {
            int randomNumber = Greenfoot.getRandomNumber(6);
            if (!numbers.contains(randomNumber))  numbers.add(randomNumber);
        }
        
        return numbers;
    }
    
    public void act()
    {
        Game game = (Game)getWorld();
        Player player = (Player) game.getObjects(Player.class).get(0);
        update(game.selectedLevelup);
    }
    
    public void update(int sLU)
    {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        GreenfootImage bg = new GreenfootImage("menu/levelup_" + sLU + ".png");
        GreenfootImage stand = new GreenfootImage("characters/" + player.charName + "/stand.png");
        
        IDs.set(0,player.character);//for showcase
        
        icon icon1 = new icon(new GreenfootImage("Weapons/" + getName(IDs.get(0)) + ".png"),40,40);
        icon icon2 = new icon(new GreenfootImage("Weapons/" + getName(IDs.get(1)+10) + ".png"),40,40);
        icon icon3 = new icon(new GreenfootImage("Weapons/" + getName(IDs.get(2)+10) + ".png"),40,40);
        icon icon4 = new icon(new GreenfootImage("Weapons/" + getName(IDs.get(3)+10) + ".png"),40,40);
        
        getWorld().addObject(icon1,496,215); //+138
        if (!player.hasWeapon(IDs.get(0))) getWorld().addObject(new BitmapText(getName(IDs.get(0)), 2), 600, 160);
        else getWorld().addObject(new BitmapText(getName(IDs.get(0)) + " LV " + player.getWeaponLevel(IDs.get(0)), 2), 600, 160);
        getWorld().addObject(new BitmapText(getText(IDs.get(0)), 2), 840, 235);
        
        getWorld().addObject(icon2,496,353);
        if (!player.hasWeapon(IDs.get(1)+10)) getWorld().addObject(new BitmapText(getName(IDs.get(1)+10), 2), 600, 298);
        else getWorld().addObject(new BitmapText(getName(IDs.get(1)+10) + " LV " + player.getWeaponLevel(IDs.get(1)+10), 2), 600, 298);
        getWorld().addObject(new BitmapText(getText(IDs.get(1)+10), 2), 840, 363);
        
        getWorld().addObject(icon3,496,491);
        if (!player.hasWeapon(IDs.get(2)+10))getWorld().addObject(new BitmapText(getName(IDs.get(2)+10), 2), 600, 436);
        else getWorld().addObject(new BitmapText(getName(IDs.get(2)+10) + " LV " + player.getWeaponLevel(IDs.get(2)+10), 2), 600, 436);
        getWorld().addObject(new BitmapText(getText(IDs.get(2)+10), 2), 840, 501);
        
        getWorld().addObject(icon4,496,629);
        if (!player.hasWeapon(IDs.get(3)+10)) getWorld().addObject(new BitmapText(getName(IDs.get(3)+10), 2), 600, 574);
        else getWorld().addObject(new BitmapText(getName(IDs.get(3)+10) + " LV " + player.getWeaponLevel(IDs.get(3)+10), 2), 600, 574);
        getWorld().addObject(new BitmapText(getText(IDs.get(3)+10), 2), 840, 639);
        
        if(player.charName == "amelia"
        || player.charName == "gura"
        || player.charName == "ina"
        || player.charName == "kiara"
        || player.charName == "mori"
        || player.charName == "caine")stand.scale(stand.getWidth()*4,stand.getHeight()*4);
        if(player.charName == "cecilia")stand.scale(stand.getWidth()*2,stand.getHeight()*2);
        if(player.charName == "filian"
        || player.charName == "neuro")stand.scale((int)(stand.getWidth()*1.5),(int)(stand.getHeight()*1.5));
        bg.drawImage(stand,50,240);
        setImage(bg);
    }
    
    public void remove() {
        Game game = (Game)getWorld();
        Player player = (Player) game.getObjects(Player.class).get(0);
        for(BitmapText BtM : getWorld().getObjects(BitmapText.class)) getWorld().removeObject(BtM);
        for(icon i : getWorld().getObjects(icon.class)) getWorld().removeObject(i);
        if(game.selectedLevelup == 1) player.gainWeapon(IDs.get(game.selectedLevelup-1));
        else player.gainWeapon(IDs.get(game.selectedLevelup-1)+10);
        getWorld().removeObject(this);
    }
    
    public String getName(int weaponID) {
        String string = "_";
        switch(weaponID) {
            case 1: string= "Amelia"; break;
            case 2: string= "Gawr"; break;
            case 3: string= "Ina"; break;
            case 4: string= "Kiara"; break;
            case 5: string= "Mori"; break;
            case 6: string= "Cecilia"; break;
            case 7: string= "Filian"; break;
            case 8: string= "Caine"; break;
            case 9: string= "Neuro"; break;
            case 10: string= "Elite_Lava_Bucket"; break;
            case 11: string= "Spider_Cooking"; break;
            case 12: string= "Plug_Type_Asacoco"; break;
            case 13: string= "Holo_Bomb"; break;
            case 14: string= "Psycho_Axe"; break;
            case 15: string= "BL_Book"; break;
        }
        return string;
    }
    
    public String getText(int ID) {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        switch (ID) {
            case 1: //Amelia
                switch (player.getWeaponLevel(1)+1) {
                    case 2: return "Shoot 2 additional shot, \nand each bullet can pierce +1 times. ";
                    case 3: return "Increase damage by 20%";
                    case 4: return "Bullets ricochet if hit limit is reached. ";
                    case 5: return "Each bullet can pierce +1 times. \nReduce the time between attacks by 25%";
                    case 6: return "Increase damage by 20%. ";
                    case 7: return "15% of all damage taken by target is stored in time.";
                }
                break;
            case 2: // Gura
                switch(player.getWeaponLevel(2)+1) {
                    case 2: return "Increase damage by 20%. ";
                    case 3: return "Stab 1 extra time, forming a V shape. ";
                    case 4: return "Reduce the time between attacks by 15%";
                    case 5: return "Increase damage by 40%. ";
                    case 6: return "Increase attack area by 25%. ";
                    case 7: return "Thrust 3 times, in a fork-like shape.";
                }
                break;
            case 3: // Ina
                switch(player.getWeaponLevel(3)+1) {
                    case 2: return "Increase damage by 20%.";
                    case 3: return "Reduce the time between attacks by 20%. ";
                    case 4: return "Increase attack area by 25%. ";
                    case 5: return "Increase damage by 50%. ";
                    case 6: return "Adds small knockback on hit";
                    case 7: return "Chain another 3 Tentacles \nin a chain at a random direction.";
                }
                break;
            case 4: // Kiara
                switch(player.getWeaponLevel(4)+1) {
                    case 2: return "Increase damage by 20%";
                    case 3: return "Increase attack area by 25%. ";
                    case 4: return "Reduce the time between attacks by 15%. ";
                    case 5: return "Can hit twice per slash. ";
                    case 6: return "Increase damage by 20%. ";
                    case 7: return "Sword is engulfed in flames, \nand can hit many times.";
                }
            case 5: //Mori
                switch(player.getWeaponLevel(5)+1) {
                    case 2: return "Increase damage by 20%. ";
                    case 3: return "Increase attack area by 15%. ";
                    case 4: return "Increase damage by 30%.";
                    case 5: return "Reduce the time between attacks by 10%. ";
                    case 6: return "Increase attack area by 10%. ";
                    case 7: return "Increase damage by 40%. \nHas a 10% chance to instant KO.";
                }
            case 6: // Cecilia
                switch(player.getWeaponLevel(6)+1) {
                    case 2: return " ";
                    case 3: return " ";
                    case 4: return " ";
                    case 5: return " ";
                    case 6: return " ";
                    case 7: return " ";
                }
            case 7: // Filian
                switch(player.getWeaponLevel(7)+1) {
                    case 2: return " ";
                    case 3: return " ";
                    case 4: return " ";
                    case 5: return " ";
                    case 6: return " ";
                    case 7: return " ";
                }
            case 8: // Caine
                switch(player.getWeaponLevel(8)+1) {
                    case 2: return " ";
                    case 3: return " ";
                    case 4: return " ";
                    case 5: return " ";
                    case 6: return " ";
                    case 7: return " ";
                }
            case 9: // Vedal
                switch(player.getWeaponLevel(9)+1) {
                    case 2: return " ";
                    case 3: return " ";
                    case 4: return " ";
                    case 5: return " ";
                    case 6: return " ";
                    case 7: return " ";
                }
            case 10: 
                switch(player.getWeaponLevel(10)+1) {
                    case 0: return "Drop lava on the ground, \nburning targets slowly. ";
                    case 2: return "Increase lava area by 20%. ";
                    case 3: return "Throw 2 lava buckets.";
                    case 4: return "Increase damage by 30% and \nincrease duration of lava by 1 second";
                    case 5: return "Increase damage by 30%";
                    case 6: return "Throw 3 lava buckets";
                    case 7: return "Throw 4 lava buckets and \nincrease lava size by 20% ";
                }
            case 11: 
                switch(player.getWeaponLevel(11)+1) {
                    case 0: return "Create an area of miasma around, \ndealing slow damage to enemies inside.";
                    case 2: return "Increase area by 15%. ";
                    case 3: return "Increase damage by 30%. ";
                    case 4: return "Increase area by 25%";
                    case 5: return "Increase frequency of hits by 20%. ";
                    case 6: return "Increase damage by 60%. ";
                    case 7: return "Add small knockback on hit. ";
                }
            case 12: 
                switch(player.getWeaponLevel(12)+1) {
                    case 0: return "Fires a fast piercing \ntail at a random target.";
                    case 2: return "Increase damage by 20%. ";
                    case 3: return "Fire an additional Asacoco. ";
                    case 4: return "Increase damage by 30%. ";
                    case 5: return "Fire an additional Asacoco. ";
                    case 6: return "Adds knockback on hit. ";
                    case 7: return "Fire an additional Asacoco. ";
                }
            case 13: 
                switch(player.getWeaponLevel(13)+1) {
                    case 0: return "A bomb that explodes, \ndealing damage to all nearby targets.";
                    case 2: return "Increase explosion size by 15%. ";
                    case 3: return "Increase damage by 20%. ";
                    case 4: return "Throw 2 bombs";
                    case 5: return "Reduce the time between attacks by 20%. ";
                    case 6: return "Increase explosion size by 20%. ";
                    case 7: return "Throw 3 bombs. ";
                }
            case 14: 
                switch(player.getWeaponLevel(14)+1) {
                    case 0: return "Throw an axe that spirals \noutward from the player. ";
                    case 2: return "Increase size of axe by 20%. \nIncrease damage of axe by 20%.";
                    case 3: return "Reduce delay between attacks by 20%. ";
                    case 4: return "Increase damage by 33%, \nand size by 20%.";
                    case 5: return "Remove hit limit, \nand lasts 1 second longer.";
                    case 6: return "Increase attack size by 50%.";
                    case 7: return "Increase damage by 50%.";
                }
            case 15: 
                switch(player.getWeaponLevel(15)+1) {
                    case 0: return "Repels targets with \norbiting BL Books.";
                    case 2: return "Add 1 additional book. ";
                    case 3: return "Increase damage by 30% \nand increase duration by 1 second.";
                    case 4: return "Add 1 additional book. ";
                    case 5: return "Increase Damage by 40%. ";
                    case 6: return "Add 1 additional book";
                    case 7: return "Increase damage by 40%.";
                }
        }
        return "";
    }
}