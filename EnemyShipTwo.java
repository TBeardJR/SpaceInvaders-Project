import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class EnemyShipTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyShipTwo extends EnemyShip
{    
    private Vector direction;    
    private boolean isInRange = false;
    public boolean healthBar = false;
    public Font font = new Font("Serif", Font.BOLD , 10);
    public EnemyShipTwo(Actor target)
    {
        super(target);
        setHealth(150);
        direction = new Vector(getRotation(), 0.5);       
        increaseSpeed(direction);       
        setGunReloadTime(10);   
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
                getWorld().addObject(enemyHealthMessage, getX() - 30, getY() - 30);
                healthBar = true;
            }
            enemyHealthMessage.update("HP: " + getHealth(), Color.RED, font);
            faceTowardTarget(this);
            //If target is in range move towards it and bring the health status with it. Otherwise shoot at target
            if(!isTargetInRange(600))
            {
                moveTowardTarget(90);     
                enemyHealthMessage.updatePosition(getX() - 30, getY() - 30);
            }
            else
            {
                shootAtTarget("RedLaser", getGunReloadTime());
                //Greenfoot.playSound("Laze.mp3");
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
