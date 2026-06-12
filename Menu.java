import greenfoot.*;

public class Menu extends World
{
    //Default setting
    String menu     = "main";
    String charName = "suisei";
    
    int selectedMenu      = 1;
    int selectedCharacter = 1;
    int selectedStage     = 1;
    int selectedAuthor    = 1;
    
    boolean keyCooldown = true;
    
    boolean standsOn    = true;
    boolean mannequinOn = true;
    boolean authorOn    = true;
    
    GreenfootImage[] menuBackgrounds    = new GreenfootImage[4];
    GreenfootImage[] charBackgrounds    = new GreenfootImage[9];
    GreenfootImage[] stageBackgrounds   = new GreenfootImage[3];
    GreenfootImage[] authorsBackgrounds = new GreenfootImage[4];
    GreenfootImage   tutorialBackground = new GreenfootImage("mainmenu/tutorial.png");
    
    GreenfootSound music    = new GreenfootSound("OST/HoloCure OST - Title.mp3");
    GreenfootSound cecilia  = new GreenfootSound("OST/Nevermore.mp3"); //Nevermore
    GreenfootSound filiana  = new GreenfootSound("OST/Snackers.mp3"); //Snackers
    GreenfootSound neuro    = new GreenfootSound("OST/Truck.mp3"); //Chinatown //Truck //Evil
    GreenfootSound vova     = new GreenfootSound("OST/HoloCure OST - Title.mp3");
    
    public Menu()
    {
        super(1280, 720, 1);
        showMainMenu();
        for (int i = 0; i < menuBackgrounds.length; i++) {
            menuBackgrounds[i] = new GreenfootImage("mainmenu/mainmenu_" + (i+1) + ".png");
            menuBackgrounds[i].scale(getWidth(), getHeight());
        }
        for (int i = 0; i < charBackgrounds.length; i++) {
            charBackgrounds[i] = new GreenfootImage("mainmenu/char_" + (i+1) + ".png");
            charBackgrounds[i].scale(getWidth(), getHeight());
        }
        for (int i = 0; i < stageBackgrounds.length; i++) {
            stageBackgrounds[i] = new GreenfootImage("mainmenu/stage_picker_" + (i+1) + ".png");
            stageBackgrounds[i].scale(getWidth(), getHeight());
        }
        for (int i = 0; i < authorsBackgrounds.length; i++) {
            authorsBackgrounds[i] = new GreenfootImage("mainmenu/authors_" + (i+1) + ".png");
            authorsBackgrounds[i].scale(getWidth(), getHeight());
        }
        tutorialBackground.scale(getWidth(), getHeight());
        music.playLoop();
    }

    public String getCharName() {return charName;}
    
    public void showMainMenu()
    {
        setBackground(menuBackgrounds[selectedMenu-1]);
        menu = "main";
        standOffOn();
    }
    
    public void showAuthors()
    {
        setBackground(authorsBackgrounds[selectedAuthor-1]);
        menu = "authors";
        authorOffOn();
    }

    public void showCharacterSelection()
    {
        setBackground(charBackgrounds[selectedCharacter-1]);
        menu = "char";
        mannequinOffOn();
    }
    
    public void showStageSelection()
    {
        setBackground(stageBackgrounds[selectedStage-1]);
        menu = "stage";
    }

    public void standOffOn() {
        if (standsOn) {
            addObject(new Stand("cecilia", 0, 2), 180, 360);
            addObject(new Stand("filian", 15, 1), 310, 340);
            addObject(new Stand("neuro",  19, 1), 430, 360);
            addObject(new Stand("vova",   32, 1), 550, 360);
            
            addObject(new Stand("amelia",19, 2.5), 120, 460);
            addObject(new Stand("gura",   0, 2.5), 240, 420);
            addObject(new Stand("ina",   16, 2.5), 360, 460);
            addObject(new Stand("kiara", 15, 2.5), 480, 420);
            addObject(new Stand("mori",  32, 2.5), 600, 460);
            standsOn = false;
        } else {
            for (Stand std : getObjects(Stand.class)) removeObject(std);
            standsOn = true;
        }
    }
    
    public void mannequinOffOn() {
        if (mannequinOn) {addObject(new PlayerNot(), 990, 400);mannequinOn=false;}
        else {for (PlayerNot mannequin: getObjects(PlayerNot.class)) removeObject(mannequin);mannequinOn=true;}
    }
    
    public void authorOffOn() {
        if (authorOn) {
            addObject(new PlayerNot(), 990, 400);
            addObject(new Stand("cecilia",0, 1), 360, 330);
            authorOn = false;
        } else {
            for (PlayerNot mannequin : getObjects(PlayerNot.class)) removeObject(mannequin);
            for (Stand std : getObjects(Stand.class)) removeObject(std);
            authorOn = true;
        }
    }
    
    public void switchToGameStage() {
        music.pause();
        filiana.pause();
        neuro.pause();
        cecilia.pause();
                
        switch(selectedCharacter) {
            case 0: charName = "suisei"; break;
            case 1: charName = "amelia"; break;
            case 2: charName = "gura"; break;
            case 3: charName = "ina"; break;
            case 4: charName = "kiara"; break;
            case 5: charName = "mori"; break;
            case 6: charName = "cecilia"; break;
            case 7: charName = "filian"; break;
            case 8: charName = "neuro"; break;
            case 9: charName = "vova"; break;
        }
        
        switch (selectedStage) { 
            case 1: Greenfoot.setWorld(new GrassyPlains(charName, selectedCharacter)); break;
            case 2: Greenfoot.setWorld(new HoloOffice(charName, selectedCharacter)); break;
            case 3: Greenfoot.setWorld(new HalloweenCastle(charName, selectedCharacter)); break;
        }
    }

    public void act()
    {
        if (menu == "main") {
            if (!keyCooldown && (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) && selectedMenu != 1) 
            {selectedMenu--; keyCooldown = true;Greenfoot.playSound("menu/select.wav");}
            if (!keyCooldown && (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) && selectedMenu != 4) 
            {selectedMenu++; keyCooldown = true;Greenfoot.playSound("menu/select.wav");}
            
            setBackground(menuBackgrounds[selectedMenu-1]);
            
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) && selectedMenu == 1) 
            {Greenfoot.playSound("menu/confirm.wav");showCharacterSelection(); standOffOn(); keyCooldown = true;}
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) && selectedMenu == 2) 
            {Greenfoot.playSound("menu/confirm.wav");setBackground(tutorialBackground); menu = "tutorial"; keyCooldown = true;}
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) && selectedMenu == 3) 
            {Greenfoot.playSound("menu/confirm.wav");standOffOn(); showAuthors();  keyCooldown = true;}
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter")) && selectedMenu == 4) 
            {music.pause(); Greenfoot.stop();}
        }
        
        if (menu == "authors") {
            PlayerNot mannequin = getObjects(PlayerNot.class).get(0);
            Stand std = getObjects(Stand.class).get(0);
            
            //switching mannequin
            switch(selectedAuthor) {
                case 1: mannequin.Switch("cecilia"); std.charName = "cecilia"; std.rescale = 3; break;
                case 2: mannequin.Switch("filian"); std.charName = "filian"; std.rescale = 2; break;
                case 3: mannequin.Switch("neuro"); std.charName = "neuro"; std.rescale = 2; break;
                case 4: mannequin.Switch("vova"); std.charName = "vova"; std.rescale = 2; break;
            }
            
            setBackground(authorsBackgrounds[selectedAuthor-1]);
            
            if (!keyCooldown && (Greenfoot.isKeyDown("shift") || Greenfoot.isKeyDown("escape"))) {
                Greenfoot.playSound("menu/exit.wav"); authorOffOn(); showMainMenu(); keyCooldown = true;}
            if (!keyCooldown && (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))) {
                selectedAuthor--; keyCooldown = true; Greenfoot.playSound("menu/select.wav");}
            if (!keyCooldown && (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))) {
                selectedAuthor++; keyCooldown = true; Greenfoot.playSound("menu/select.wav");}
            if (selectedAuthor == 0) selectedAuthor = 4; 
            if (selectedAuthor == 5) selectedAuthor = 1; 
        }
        
        if (menu == "tutorial") {
            if (!keyCooldown && (Greenfoot.isKeyDown("shift") || Greenfoot.isKeyDown("escape"))) {
                Greenfoot.playSound("menu/exit.wav");setBackground(menuBackgrounds[selectedMenu-1]); menu = "main"; keyCooldown = true;}
        }
        
        if (menu == "char") {
            //menu movements
            if (!keyCooldown && (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))) {
                selectedCharacter--; keyCooldown = true; Greenfoot.playSound("menu/select_char.wav");}
            if (!keyCooldown && (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))) {
                selectedCharacter++; keyCooldown = true; Greenfoot.playSound("menu/select_char.wav");}
            if (!keyCooldown && (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down"))) {
                selectedCharacter+=5; keyCooldown = true; Greenfoot.playSound("menu/select_char.wav");}
            
            //handle of out of bound values 
            if (selectedCharacter <= 0) selectedCharacter = 9; 
            if (selectedCharacter >= 10) selectedCharacter -= 9; 
            
            
            PlayerNot mannequin = new PlayerNot();
            for (PlayerNot manequin : getObjects(PlayerNot.class)) mannequin = manequin;
            
            //switching mannequin
            switch(selectedCharacter) {
                case 1: mannequin.Switch("amelia");  break;
                case 2: mannequin.Switch("gura");    break;
                case 3: mannequin.Switch("ina");     break;
                case 4: mannequin.Switch("kiara");   break;
                case 5: mannequin.Switch("mori");    break;
                case 6: mannequin.Switch("cecilia"); break;
                case 7: mannequin.Switch("filian");  break;
                case 8: mannequin.Switch("neuro");   break;
                case 9: mannequin.Switch("vova");    break;
            }
            
            //background
            setBackground(charBackgrounds[selectedCharacter-1]);
            
            //music
            if (selectedCharacter <= 5) music.playLoop();   else music.pause();
            if (selectedCharacter == 6) cecilia.playLoop(); else cecilia.pause();
            if (selectedCharacter == 7) filiana.playLoop();   else filiana.pause();
            if (selectedCharacter == 8) neuro.playLoop(); else neuro.pause();
            if (selectedCharacter == 9) vova.playLoop(); else vova.pause();
            
            //exit and confirmation
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter"))) {
                Greenfoot.playSound("menu/confirm.wav");showStageSelection(); mannequinOffOn(); keyCooldown = true;}
            if (!keyCooldown && (Greenfoot.isKeyDown("shift") || Greenfoot.isKeyDown("escape"))) {
                Greenfoot.playSound("menu/exit.wav");showMainMenu(); mannequinOffOn(); keyCooldown = true;}
        }
        
        if (menu == "stage") {
            if (!keyCooldown && (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))) {selectedStage--; keyCooldown = true;}
            if (!keyCooldown && (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))) {selectedStage++; keyCooldown = true;}
            if (selectedStage == 0) selectedStage = 3; 
            if (selectedStage == 4) selectedStage = 1; 
            setBackground(stageBackgrounds[selectedStage-1]);
            if (!keyCooldown && (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter"))) {
                Greenfoot.playSound("menu/confirm.wav");switchToGameStage(); keyCooldown = true;}
            if (!keyCooldown && (Greenfoot.isKeyDown("shift") || Greenfoot.isKeyDown("escape"))) {
                Greenfoot.playSound("menu/exit.wav");showCharacterSelection(); keyCooldown = true;}
        }
        if (keyCooldown && !(Greenfoot.isKeyDown("shift") || Greenfoot.isKeyDown("escape") ||
                             Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("enter") ||
                             Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up") || 
                             Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left") ||
                             Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down") ||
                             Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))) keyCooldown = false;
    }
}
