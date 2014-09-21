import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.*;
/**
 * Write a description of class EnemyShipOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyShipOne extends EnemyShip
{    
    private Actor target;  
    private Vector direction;    
    private boolean isInRange = false;
    public boolean healthBar = false;
    public Font font = new Font("Serif", Font.BOLD , 10);
    public EnemyShipOne(Actor target)
    {
        super(target);
        setHealth(15);
        direction = new Vector(getRotation(), 0.9);       
        increaseSpeed(direction);       
        setGunReloadTime(15);         
    }   

    /**
     * Shoot at target if it's in range.
     * If not in range move towards the target
     * If this enemy ship doesn't have any health remove it from the world
     */
    public void act() 
    {        
        if(isInWorld == true)
        {           
            //If enemyships health status isn't being displayed add it to the world
            if(healthBar == false){
                getWorld().addObject(enemyHealthMessage, getX() - 10, getY() - 10);
                healthBar = true;
            }
            enemyHealthMessage.update("HP: " + getHealth(), Color.RED, font); //Update status to the current health
            faceTowardTarget(this);
            //If target is in range move towards it and bring the health status with it. Otherwise shoot at target
            if(!isTargetInRange(200))
            {
                moveTowardTarget(90);  
                enemyHealthMessage.updatePosition(getX() - 10, getY() - 10);
            }
            else
            {
                shootAtTarget("laser", getGunReloadTime());                
            }   
            incrementReloadDelayCount();
            //Remove this ship from the world if it has no health and give another kill to the rocket (You)
            if(!hasHealth())
            {                
                removeSelfFromWorld(enemyHealthMessage);
                removeSelfFromWorld(this);
                Rocket.incrementKillCount();
                isInWorld = false;
                Greenfoot.playSound("shoot.wav");
            }
        }
    } 

}
