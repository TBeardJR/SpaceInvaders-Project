import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class Base here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Base extends SpaceCraft 
{
    private GreenfootImage base = new GreenfootImage("Death Star.jpg");   
    public Class[] enemyShips = {EnemyShipOne.class, EnemyShipTwo.class, EnemyShipThree.class, EnemyShipFour.class
        ,EnemyShipFive.class, EnemyShipSix.class};    
    private int reloadDelayCount;
    private int shotsFired; 
    private static int lastX;
    private static int lastY;
    public static int health = 10000;    
    public boolean isLevelClear = false;
    public Font font = new Font("Serif", Font.BOLD , 10);
    public static HUDMessage baseHealthMessage;    
    public Base()
    {
        setGunReloadTime(20);
        setHealth(health);        
        reloadDelayCount = 0;
        shotsFired = 0;
        isInWorld = true;
        baseHealthMessage = new HUDMessage("HP: " + getHealth(), 160, 25, Color.RED, font);
    }

    /**
     * Act - do whatever the Base wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(isInWorld == true)
        {
            //Set's postion of base's health status on the first level and first subwave and stays there indefinitely
            if(getWorld().getClass() == WorldOne.class && WorldControl.subWave == 1)
            {                
                getWorld().addObject(baseHealthMessage, getX() - 30, getY() - 50); //Set position of base's health status on the map
            }
            //baseHealthMessage.updatePosition(getX() - 30, getY() - 50); //Move the health status
            baseHealthMessage.update("HP: " + getHealth(), Color.RED, font);
            health = getHealth(); // update to current health
            if(Rocket.isRocketAlive == false)
            {
                shootEnemyShips(); //Base will shoot at enemies when the rocket has been destroyed
            }
            lastX = getX(); //update last known x coordinate
            lastY = getY(); //update last known y coordinate
            Actor enemyShip =  find(); //Find an enemyship close by
            if (enemyShip != null && Rocket.isRocketAlive == false)
            {
                turnTowards(enemyShip.getX(), enemyShip.getY()); //turn towards the closest enemyship found
            }
            incrementReloadDelayCount();
            //If the map is clear of enemies and the enemySpawnTimer is not running change the world
            if(WorldControl.isMapClear(getWorld()) && !WorldControl.isTimerOn())
            {                
                WorldControl.changeWorld();
            }
            //If the base has no health remove it from the world and end the game
            if(!hasHealth())
            {
                removeSelfFromWorld(this);
                isInWorld = false;
                Greenfoot.playSound("MetalExplosion.wav");                
                youLose();
            }            
        }
    }   

    /**
     * Finds specified actor withing a certain range
     * Returns null if no objects are found
     */
    public Actor find()
    {
        if (getObjectsInRange(1200, EnemyShip.class).isEmpty())
        {
            return null;
        }
        return (Actor)getObjectsInRange(1200, EnemyShip.class).get(0);
    }

    /**
     * Change the world to YouLose
     */    
    public static void youLose()
    {
        Greenfoot.setWorld(new YouLose ());
        Greenfoot.playSound("death.wav");
    }

    /**
     * Shoots at enemies when they are within specified range
     */    
    public void shootEnemyShips()
    {
        for(int x = 0; x < enemyShips.length; x++)
        {
            if(!getObjectsInRange(1200, enemyShips[x]).isEmpty())
            {
                fire("GreenLaser", getGunReloadTime());
                break;
            }
        }        
    } 

    /**
     * Return the base's last known x coordinate
     */    
    public static int getLastX()
    {
        return lastX;
    }

    /**
     * Return the base's last known y coordinate
     */    
    public static int getLastY()
    {
        return lastY;
    }   

}
