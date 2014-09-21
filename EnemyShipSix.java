import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.reflect.*;
import java.util.*;
import java.awt.*;
/**
 * Write a description of class EnemyShipSix here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyShipSix extends EnemyShip
{   
    private Actor target;  
    private Vector direction;    
    private boolean isInRange = false;
    public static final int ENEMY_ONE_CHANCE = 25;
    public static final int ENEMY_TWO_CHANCE = 35;
    public static final int ENEMY_THREE_CHANCE = 95;
    public static final int ENEMY_FOUR_CHANCE = 95;
    public static final int ENEMY_FIVE_CHANCE = 80;
    public static final int ENEMY_SIX_CHANCE = 0;
    public int enemyChances[] = {ENEMY_ONE_CHANCE, ENEMY_TWO_CHANCE, ENEMY_THREE_CHANCE, ENEMY_FOUR_CHANCE,
            ENEMY_FIVE_CHANCE, ENEMY_SIX_CHANCE};
    public Class[] enemyShips = {EnemyShipOne.class, EnemyShipTwo.class, EnemyShipThree.class, EnemyShipFour.class
        ,EnemyShipFive.class, EnemyShipSix.class}; 
    public int numberOfEnemies;
    public int enemyChance;
    public int enemiesSpawned = 0;  
    public Timer enemySpawnTimer;
    public EnemySpawnTask enemySpawnTask;
    public boolean isTimerRunning = false;
    public Object object;
    Actor base = getTarget();
    public Object[] deathStar = new Object[] {base};
    public boolean healthBar = false;
    public Font font = new Font("Serif", Font.BOLD , 15);
    public EnemyShipSix(Actor target)
    {
        super(target);
        setHealth(500);
        //direction = new Vector(getRotation(), 0.9);       
        //increaseSpeed(direction);       
        setGunReloadTime(1000);   
    }   

    /**
     * Shoot at base     
     * If this enemy ship doesn't have any health remove it from the world
     * Spawn 5 EnemyShips every 15 seconds
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
            setTarget((Actor) getObjectsInRange(1200, Base.class).get(0)); //Target is always set to the Base
            shootAtTarget("GiantPurpleLaser", getGunReloadTime());
            //Greenfoot.playSound("Laze.mp3");
            incrementReloadDelayCount();
            //If the timer is not running start the timer
            if(isTimerRunning == false)
            {
                enemySpawnTimer = new Timer("Respawner");
                enemySpawnTask = new EnemySpawnTask(); 
                
                //enemySpawnTask is scheduled to spawn more enemy ships every 15 seconds (Second parameter)
                //The very first task doesn't take place until after a 0 second delay (First parameter)
                enemySpawnTimer.schedule(enemySpawnTask, 0, 15000);
                isTimerRunning = true;
            }
            //Remove this ship from the world if it has no health and give another kill to the rocket (You)
            if(!hasHealth())
            {
                enemySpawnTask.cancel(); //Cancel task
                enemySpawnTimer.cancel(); //Stop timer
                removeSelfFromWorld(enemyHealthMessage);
                removeSelfFromWorld(this);
                Rocket.incrementKillCount();
                isInWorld = false;
                Greenfoot.playSound("shoot.wav");
            }
        }
    }  

    /**
     * Spawn 5 Random enemy ships
     */
    public void spawnReinforcements()
    {
        numberOfEnemies = 5;         
        while(enemiesSpawned < numberOfEnemies)
        {            
            for(int y = 0; y < enemyChances.length; y++)
            {
                enemyChance = Greenfoot.getRandomNumber(100);
                if(enemyChance < enemyChances[y] && enemiesSpawned < numberOfEnemies)
                {                                                                  
                    getWorld().addObject((EnemyShip) createEnemyShip(enemyShips[y]), (this.getX() - 200) + Greenfoot.getRandomNumber(400), 
                        (this.getY() - 200) + Greenfoot.getRandomNumber(400));
                    enemiesSpawned++;                   
                }
            }
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
