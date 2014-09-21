import greenfoot.*;
import java.awt.Color;
import java.awt.Font;

/**
 * HUDMessage
 * Used to display a text message in a world
 */
public class HUDMessage extends Actor {
    /**
     * the width of the HUD message
     */
    private static int width;
    /**
     * the height of the HUD message
     */
    private static int height;
    /**
     * font size of the HUD message
     */
    private static float fontSize = 12.0f;
    /**
     * font of the HUD message
     */
    private Font font;
    /**
     * HUD image
     */
    private GreenfootImage imageHUD;
    /**
     * Message
     */
    public String message;

    /**
     * Constructor.     
     */
    public HUDMessage(String message, int width, int height, Color color, Font font) {
        this.width = width;
        this.height = height; 
        this.message = message;
        imageHUD = new GreenfootImage(width, height);
        update(message, color, font);        
    }

    /**
     * Constructor. (by default)
     */
    public HUDMessage() {
        update("", Color.WHITE, font);
    }

    /**
     * Updates the HUD message.     
     */
    public void update(String message, Color color, Font font) {
        imageHUD = new GreenfootImage(width, height);       
        if (message != "") {            
            imageHUD.setFont(font);
            imageHUD.setColor(color);
            imageHUD.drawString(message, 15, 15);
        }
        setImage(imageHUD);
    }   
    
    /**
     * Updates the position of the HUD message.     
     */
    public void updatePosition(int x, int y)
    {
        setLocation(x, y);
    }
    
    /**
     * Set the width of the HUD message.     
     */
    public void setWidth(int width)
    {
        this.width = width;
    }
    
    /**
     * Set the height the HUD message.     
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * Gets the width of the HUD message.     
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the HUD message.     
     */
    public int getHeight() {
        return height;
    }
}