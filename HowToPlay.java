import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

/**
 * The ScoreBoard is used to display results on the screen. It can display some
 * text and several numbers.
 * 
 * @author M Kolling
 * @version 1.0
 */
public class HowToPlay extends Actor
{
    public static final float FONT_SIZE = 30.0f;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    
    /**
     * Create a score board with dummy result for testing.
     */
    public HowToPlay()
    {
        this(100);
    }
    
    public void act()
    {
        checkMouseClick();
    }

    /**
     * Create a score board for the final result.
     */
    public HowToPlay(int score)
    {
        makeImage("How to Play");
    }

    /**
     * Make the score board image.
     */
    private void makeImage(String title)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
        image.setColor(new Color(255,255,255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 255));
        image.fillRect(5, 5, WIDTH-10, HEIGHT-10);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(title, 120, 50);
        Font font2 = image.getFont();
        font2 = font2.deriveFont(15.0f);
        image.setFont(font2);
        image.drawString("- GOAL: Protect your base from 10 waves of enemies", 30, 80);
        image.drawString("- Press SPACE Bar to shoot", 30, 100);
        image.drawString("- Use UP,LEFT and RIGHT arrow keys to move around", 30, 120);
        image.drawString("- Move across power ups to give yourself an advantage", 30, 140);
        image.drawString("- Complete the 10 waves to win game", 30, 160);
        image.drawString("- If your base is destroyed you lose game", 30, 180);
        font2 = font2.deriveFont(30.0f);
        image.setFont(font2);
        image.setColor(Color.GREEN);
        image.drawString("CLICK TO START", 75, 245);
        setImage(image);
    }
    
    /**
     * Check if the mouse has been clicked on the "How To Play" Box
     * If it has been clicked set the first world
     */
    private void checkMouseClick() 
    {
        if(Greenfoot.mouseClicked(this)) {            
            Greenfoot.setWorld(new WorldOne ());
        }        
    }
}
