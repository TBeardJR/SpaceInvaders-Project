import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.util.*;
import java.lang.reflect.*;
/**
 * Write a description of class EnemyShipFive here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyShipFive extends EnemyShip
{
    public Class[] enemyShips = {EnemyShipOne.class}; 
    private Actor target;  
    private Vector direction;    
    private boolean isInRange = false;
    public boolean healthBar = false;
    public int numberOfEnemies;
    public int enemyChance;
    public int enemiesSpawned = 0;  
    public Timer enemySpawnTimer;
    public EnemySpawnTask enemySpawnTask;
    public boolean isTimerRunning = false;
    public Object object;
    Actor base = getTarget();
    public Object[] deathStar = new Object[] {base};
    public Font font = new Font("Serif", Font.BOLD , 10);
    public EnemyShipFive(Actor target)
    {
        super(target);
        setHealth(75);
        direction = new Vector(getRotation(), 0.9);       
        increaseSpeed(direction);       
        setGunReloadTime(30);   
    }   

    /**
     * Shoot at target if it's in range.
     * If not in range move towards the target
     * If this enemy ship doesn't have any health remove it from the world
     * Spawn 5 EnemyShipOnes every 15 seconds
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
            if(!isTargetInRange(500))
            {
                moveTowardTarget(90);  
                enemyHealthMessage.updatePosition(getX() - 30, getY() - 30);
            }
            else
            {
                shootAtTarget("redlaser", getGunReloadTime());
                //Greenfoot.playSound("Laze.mp3");
            }   
            incrementReloadDelayCount();
            //If the timer is not running start the timer
            if(isTimerRunning == false)
            {
                enemySpawnTimer = new Timer("Respawner");
                enemySpawnTask = new EnemySpawnTask();

                //enemySpawnTask is scheduled to spawn more enemy ships every 15 seconds (Second parameter)
                //The very first task doesn't take place until after a 5 second delay (First parameter)
                enemySpawnTimer.schedule(enemySpawnTask, 5000, 15000);
                isTimerRunning = true;
            }
            //Remove this ship from the world if it has no health and give another kill to the rocket (You)
            if(!hasHealth())
            {
                enemySpawnTask.cancel(); //Cancel spawning task
                enemySpawnTimer.cancel(); //Stop spawn timer
                removeSelfFromWorld(enemyHealthMessage);
                removeSelfFromWorld(this);
                Rocket.incrementKillCount();
                isInWorld = false;
                Greenfoot.playSound("shoot.wav");
            }
        }
    }  

    /**
     * Spawn 5 EnemyShipOnes
     */
    public void spawnReinforcements()
    {
        numberOfEnemies = 5;
        for(int y = 0; y < numberOfEnemies; y++)
        {
            getWorld().addObject((EnemyShip) createEnemyShip(enemyShips[0]), (this.getX() - 50) + Greenfoot.getRandomNumber(100), 
                (this.getY() - 50) + Greenfoot.getRandomNumber(100));
            enemiesSpawned++;
        }
        enemiesSpawned = 0;
    }

    /**
     * Gets the contructors of the specified class
     * Returns a new object created with the appropriate constructor
     */    
    public Object createEnemyShip(Class clss) {      
        Constructor[] theConstructors = clss.getConstructors(); //Puts all the constructors of the given class in an array 
        for (int i = 0; i < theConstructors.length; i++) {
            try{
                object = theConstructors[i].newInstance(deathStar); //Uses the constructor in the current index [i] to create a new object
            }catch (InstantiationException e) {
                System.out.println(e);
            } catch (IllegalAccessException e) {
                System.out.println(e);
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            } catch (InvocationTargetException e) {
                System.out.println(e);
            }
        }
        return object;
    }

    /**
     * Responsible for spawning enemy ships at the interval specified by the timer     
     */
    class EnemySpawnTask extends TimerTask {
        public void run() {
            spawnReinforcements();            
        }
    }
}
