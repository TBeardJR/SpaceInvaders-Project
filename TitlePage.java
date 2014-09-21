import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the title screen that appears when you start the game.
 * @author Group 10
 */
public class TitlePage extends World
{
    public static GreenfootSound myMusic = new GreenfootSound("BraveWorm.wav");
    public Start startbutton = new Start();
    public HowToPlay howToPlay = new HowToPlay();
    /**
     * Constructor for objects of class TitlePage.
     * 
     */
    public TitlePage()
    {            
        super(750, 500, 1);
        addObject(startbutton, 380, 400); //Add start button to this world        
    }
    
    /**
     * Plays music and checks for mouse click
     */
    public void act()
    {
        myMusic.playLoop();
        checkMouseClick();
    }
    
    /**
     * Check if the mouse has been clicked on the background
     * If it is clicked display the "How To Play" box
     */
    private void checkMouseClick() 
    {
        if(Greenfoot.mouseClicked(this)) {
            addObject(howToPlay, 400, 275);            
        }        
    }
}
