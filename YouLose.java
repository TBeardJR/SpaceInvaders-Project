import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * YouLose world. It's displayed whne you lose the game
 * 
 * @author Group 10 
 */
public class YouLose extends World
{

    /**
     * Constructor for objects of class YouLose.
     * 
     */
    public YouLose()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 400, 1); 
        setBackground("Down.png");  
        TitlePage.myMusic.stop();
    }
}

