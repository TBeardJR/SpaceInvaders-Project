import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.*;
/**
 * A rocket that can be controlled by the arrowkeys: up, left, right.
 * The gun is fired by hitting the 'space' key.
 * 
 * @author Poul Henriksen
 * @author Michael Kolling
 */
public class Rocket extends SpaceCraft
{
    //private int reloadDelayCount;               // How long ago we fired the gun the last time.
    private Vector acceleration;                // How fast the rocket is.
    private int shotsFired;                     // Number of shots fired.
    public static int lastX = 100;
    public static int lastY = 300;
    public static boolean isRocketAlive = false;
    private GreenfootImage rocket = new GreenfootImage("Fighter.png");    
    private GreenfootImage rocketWithThrust = new GreenfootImage("Fighter.png");
    public Font font = new Font("Serif", Font.BOLD , 10);        
    public static HUDMessage healthMessage;
    /**
     * Initilise this rocket.
     */
    public Rocket()
    {
        setGunReloadTime(15);       
        acceleration = new Vector(0, 0.3);
        increaseSpeed(new Vector(13, 0.3)); // initially slowly drifting
        shotsFired = 0;
        setHealth(1500); 
        isInWorld = true;
        isRocketAlive = true;
        healthMessage = new HUDMessage("HP " + getHealth(), 160, 25, Color.GREEN, font);
    }  

    /**
     * Do what a rocket's gotta do. (Which is: mostly flying about, and turning,
     * accelerating and shooting when the right keys are pressed.)
     */
    public void act()
    {
        if(isInWorld == true)
        {
            if(!WorldOne.isTimerOn() && getWorld().getClass() == WorldOne.class && WorldControl.subWave == 1)
            {                
                getWorld().addObject(healthMessage, getX() - 30, getY() - 30);
            }
            getWorld().addObject(healthMessage, getX() - 30, getY() - 30);
            move();
            lastX = getX();
            lastY = getY();
            healthMessage.updatePosition(getX() - 30, getY() - 30);
            healthMessage.update("HP " + getHealth(), Color.GREEN, font);            
            checkKeys();
            incrementReloadDelayCount();
            //healthMessage.update("HP " + getHealth(), Color.GREEN, font);
            if(!hasHealth())
            {
                removeSelfFromWorld(healthMessage);
                removeSelfFromWorld(this);
                isInWorld = false;
                isRocketAlive = false;
                Greenfoot.playSound("Explosion.wav");
            }
        } 

    }   

    /**
     * You win.
     */
    public static void youWin()
    {
        Greenfoot.setWorld(new YouWin ());
        Greenfoot.playSound("hooray.wav");
    }    

    /**
     * Return the approximate current travelling speed of this rocket.
     */
    public int getSpeed()
    {
        return (int) (getMovement().getLength() * 10);
    }   
    
    public static int getLastX()
    {
        return lastX;
    }
    
    public static int getLastY()
    {
        return lastY;
    }    

    

    /**
     * Check whether there are any key pressed and react to them.
     */
    private void checkKeys() 
    {
        ignite(Greenfoot.isKeyDown("up"));

        if(Greenfoot.isKeyDown("left")) {
            setRotation(getRotation() - 5);
        }        
        if(Greenfoot.isKeyDown("right")) {
            setRotation(getRotation() + 5);
        }
        if(Greenfoot.isKeyDown("space")) {
            fire("GreenLaser", getGunReloadTime());
            
        }        
    }

    /**
     * Should the rocket be ignited?
     */
    private void ignite(boolean boosterOn) 
    {
        if(boosterOn) {
            //setImage(rocketWithThrust);
            acceleration.setDirection(getRotation());
            increaseSpeed(acceleration);
        }
        else {
            //setImage("Fighter.png");        
        }
    }

}