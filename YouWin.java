import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * YouWin world. It's displayed when you win the game.
 * 
 * @author Group 10 
 */
public class YouWin extends World
{

    /**
     * Constructor for objects of class YouWin.
     * 
     */
    public YouWin()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 400, 1); 
        setBackground("Up.png");
        TitlePage.myMusic.stop();
    }
}
