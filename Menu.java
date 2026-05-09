import greenfoot.*;

public class Menu extends World
{
    String menu = "main";
    String charName = "suisei";
    int selectedCharacter = 0; // 0 - Suisei // 1 - Amelia // 2 - Gura // 3 - Ina // 4 - Kiara // 5 - Mori //
    int selectedStage = 1;
    int keyCooldown = 40;
    GreenfootImage[] charBackgrounds = new GreenfootImage[8];
    GreenfootImage[] stageBackgrounds = new GreenfootImage[3];
    GreenfootSound music = new GreenfootSound("HoloCure OST - Title.mp3");
    GreenfootSound filiana = new GreenfootSound("Snackers.mp3"); //Snackers
    GreenfootSound neuro = new GreenfootSound("Truck.mp3"); //Chinatown //Truck //Evil
    GreenfootSound cecilia = new GreenfootSound("Nevermore.mp3"); //Nevermore
    
    public Menu()
    {
        super(1280, 720, 1);
        showMainMenu();
        for (int i = 0; i < charBackgrounds.length; i++) {
            charBackgrounds[i] = new GreenfootImage("mainmenu/charachter_picker_" + (i+1) + ".png");
            charBackgrounds[i].scale(getWidth(), getHeight());
        }
        for (int i = 0; i < stageBackgrounds.length; i++) {
            stageBackgrounds[i] = new GreenfootImage("mainmenu/stage_picker_" + (i+1) + ".png");
            stageBackgrounds[i].scale(getWidth(), getHeight());
        }
        music.playLoop();
    }

    public void showMainMenu()
    {
        GreenfootImage Background = new GreenfootImage("mainmenu/main_menu.png");
        Background.scale(getWidth(), getHeight());
        setBackground(Background);
        menu = "main";
    }

    public void showCharacterSelection()
    {
        setBackground(charBackgrounds[selectedCharacter]);
        menu = "char";
    }
    
    public void showStageSelection()
    {
        setBackground(stageBackgrounds[selectedStage]);
        menu = "stage";
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
            case 6: charName = "filian"; break;
            case 7: charName = "neuro"; break;
            case 8: charName = "cecilia"; break;
        }
        
        
        switch (selectedStage) { 
            case 1: Greenfoot.setWorld(new GrassyPlains(charName)); break;
            case 2: Greenfoot.setWorld(new HoloOffice(charName)); break;
            case 3: Greenfoot.setWorld(new HalloweenCastle(charName)); break;
        }
    }
    
    
    /**
     * Pressing button with cooldown
     */
    public boolean nextPressed() {
        String key = Greenfoot.getKey();
        if (("space".equals(key) || "enter".equals(key)) && keyCooldown == 0) {
            keyCooldown = 10;
            return true;
        }
        if (keyCooldown > 0) keyCooldown--;
        return false;
    }
    
    public void act()
    {
        if (menu == "main") if (nextPressed()) showCharacterSelection();
        if (menu == "char") {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null && Greenfoot.mouseClicked(null)) {
                int x = mouse.getX();
                int y = mouse.getY();
                // Define 8 buttons horizontally
                int buttonWidth = 91, buttonHeight = 68;
                int xPos = 180;
                int yPos = 75;
                for (int i = 0; i < 8; i++) {
                    int buttonX = xPos + i * buttonWidth;
                    if (x >= buttonX && x <= buttonX + buttonWidth && y >= yPos && y <= yPos+buttonHeight) {
                        selectedCharacter = i+1;
                        setBackground(charBackgrounds[i]);
                        
                        if (selectedCharacter <= 5) music.playLoop(); else music.pause();
                        if (selectedCharacter == 6) filiana.playLoop(); else filiana.pause();
                        if (selectedCharacter == 7) neuro.playLoop(); else neuro.pause();
                        if (selectedCharacter == 8) cecilia.playLoop(); else cecilia.pause();
                        break;
                    }
                }
            }
            if (nextPressed()) menu = "stage";
        }
        if (menu == "stage") {
            String key = Greenfoot.getKey();
            if ( "left".equals(key) || "a".equals(key)) selectedStage--;
            if ("right".equals(key) || "d".equals(key)) selectedStage++;
            if (selectedStage == 0) selectedStage = 3; 
            if (selectedStage == 4) selectedStage = 1; 
            setBackground(stageBackgrounds[selectedStage-1]);
            if (("space".equals(key) || "enter".equals(key)) && keyCooldown == 0) {
                keyCooldown = 10;
                switchToGameStage();
            }
            if (keyCooldown > 0) keyCooldown--;
        }
    }
}