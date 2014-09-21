import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start extends Actor
{    
    public HowToPlay howToPlay = new HowToPlay();
    public void act() 
    {
        checkMouseClick();
    } 
    
    /**
     * Check if the mouse has been clicked on the start button
     * If it is clicked display the "How To Play" box
     */
    private void checkMouseClick() 
    {
        if(Greenfoot.mouseClicked(this)){
            getWorld().addObject(howToPlay, 400, 275); 
        }        
    }
}
