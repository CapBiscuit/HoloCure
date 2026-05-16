import greenfoot.GreenfootImage;

public class SpriteSheetHandler {
    public static GreenfootImage[] splitSheetHorizontal(GreenfootImage sheet, int totalColums, int totalRows, int currentRow, int neededFrames, int RESIZE) {
        GreenfootImage[] images = new GreenfootImage[neededFrames];

        int frameWidth = sheet.getWidth() / totalColums;
        int frameHeight = sheet.getHeight() / totalRows;
         

        for (int currentColum = 0; currentColum < neededFrames; currentColum++) {
            GreenfootImage frame = new GreenfootImage(frameWidth, frameHeight);
            frame.drawImage(sheet, -currentColum * frameWidth, -currentRow * frameHeight);
            frame.scale(frame.getWidth() * RESIZE, frame.getHeight() * RESIZE);
            images[currentColum] = frame;
        }
        
        return images;
    }
    public static GreenfootImage[] splitSheetVertical(GreenfootImage sheet, int totalColums, int totalRows, int currentColum, int neededFrames, int RESIZE) {
        GreenfootImage[] images = new GreenfootImage[neededFrames];

        int frameWidth = sheet.getWidth() / totalColums;
        int frameHeight = sheet.getHeight() / totalRows;
         

        for (int currentRow = 0; currentRow < neededFrames; currentRow++) {
            GreenfootImage frame = new GreenfootImage(frameWidth, frameHeight);
            frame.drawImage(sheet, -currentColum * frameWidth, -currentRow * frameHeight);
            frame.scale(frame.getWidth() * RESIZE, frame.getHeight() * RESIZE);
            images[currentRow] = frame;
        }
        
        return images;
    }
    
    /**
     * Cuts frames from spritesheet horizontally to an array of images
     * @param sheet Image of a spritesheet
     * @param totalColums total amount of colums in the spritesheet
     * @param totalRows   total amount of rows   in the spritesheet
     * @param currentRow row from which to start cutting (starts with 0)
     * @param neededFrames amount of frames needed in an array
     * @param RESIZE resizes all images by multiplying by this amount
     * @return an array of images
     */
}