import greenfoot.*;
import java.util.*;

public class BitmapFont
{
    private GreenfootImage fontSheet;

    private Map<Character, GreenfootImage> glyphs;

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";

    private static final String SYMBOLS = "0123456789:/-+%";

    private int glyphWidth;
    private int glyphHeight;

    public BitmapFont(String imageName, int glyphWidth, int glyphHeight)
    {
        this.glyphWidth = glyphWidth;
        this.glyphHeight = glyphHeight;

        fontSheet = new GreenfootImage(imageName);
        glyphs = new HashMap<>();

        loadGlyphs();
    }

    private void loadGlyphs()
    {
        loadRow(UPPER, 0);
        loadRow(LOWER, 1);
        loadRow(SYMBOLS, 2);
    }

    private void loadRow(String chars, int row)
    {
        for (int i = 0; i < chars.length(); i++)
        {
            GreenfootImage glyph = new GreenfootImage(glyphWidth, glyphHeight);

            glyph.drawImage(fontSheet, -i * glyphWidth, -row * glyphHeight);

            glyphs.put(chars.charAt(i), glyph);
        }
    }

    public GreenfootImage renderText(String text, int scale)
    {
        String[] lines = text.split("\n");
    
        int maxLength = 0;
    
        for (String line : lines)
        {
            maxLength = Math.max(maxLength, line.length());
        }
    
        int width = maxLength * glyphWidth * scale;
        int height = lines.length * glyphHeight * scale;
    
        GreenfootImage result = new GreenfootImage(width+1, height+1);
    
        int y = 0;
    
        for (String line : lines)
        {
            int x = 0;
    
            for (char c : line.toCharArray())
            {
                GreenfootImage glyph = glyphs.get(c);
    
                if (glyph != null)
                {
                    GreenfootImage scaled =
                        new GreenfootImage(glyph);
    
                    scaled.scale(
                        glyphWidth * scale,
                        glyphHeight * scale
                    );
    
                    result.drawImage(scaled, x, y);
                }
    
                x += glyphWidth * scale;
            }
    
            y += glyphHeight * scale;
        }
    
        return result;
    }
}