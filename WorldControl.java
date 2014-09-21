import greenfoot.*;
import java.lang.reflect.*;
import java.util.*;
import java.awt.*;
/**
 * This class is in control of all the spawning mechanisms and switching worlds.
 * 
 * @author Group 10 
 * 
 */
public class WorldControl extends World
{    
    public static Class[] enemyShips = {EnemyShipOne.class, EnemyShipTwo.class, EnemyShipThree.class, EnemyShipFour.class
        ,EnemyShipFive.class, EnemyShipSix.class}; 
    public static boolean[] isWorldsSet = {false, false, false, false, false, false, false, false, false, false};
    public static Class[] worlds = {WorldTwo.class, WorldThree.class, WorldFour.class, WorldFive.class, WorldSix.class, 
            WorldSeven.class, WorldEight.class, WorldNine.class, WorldTen.class, YouWin.class};    
    public int numberOfEnemies;
    public int enemyChance;
    public int enemiesSpawned = 0;   
    public static Object object;    
    public static boolean isLevelClear = false;
    public static boolean isTimerRunning = false;
    public static Timer enemySpawnTimer;
    public PowerUp powerUp; 
    public Rocket rocket = new Rocket();
    public Base base = new Base();
    public static int wave = 1;
    public static int subWave = 1;    
    public static Font font = new Font("Serif", Font.BOLD , 20); 
    public Font font2 = new Font("Serif", Font.BOLD , 10);
    public static HUDMessage waveMessage = new HUDMessage("Wave: " + wave, 160, 25, Color.GREEN, font);
    public static HUDMessage subWaveMessage = new HUDMessage("Sub-Wave: " + subWave, 160, 25, Color.RED, font);
    /**
     * Constructor
     * All the World classes call this constructor when created
     */    
    public WorldControl()
    {
        super(1200, 800, 1); //Creates the map and sets the map size
        enemySpawnTimer = new Timer("Respawner"); // Creates a new timer for the purpose of respawning enemies
        powerUp = new PowerUp();  //Creates a new power up object     
        addObject(waveMessage, 175, 500); //Adds text under the base that tells you what wave you are on
        addObject(subWaveMessage, 160, 530); //Adds text under the wavemessage that tells you what subwave you are on      
        addObject(base, 150, 400); //Adds the base on the far left side of the map
        addObject(rocket, Rocket.getLastX(), Rocket.getLastY()); //Adds rocket (you) to it's last known position before the world switches
        base.setHealth(Base.health); //Set the Base's health
        selectPowerUp(); //Select a random power up to be spawned at the beginning of each wave
        spawnPowerUp(this); //Spawn power up on this map
    }

    /**
     * Returns true only if world is clear of all enemy ships
     * If at least one enemy ship is found in the world it returns false
     */    
    public static boolean isMapClear(World world)
    {
        for(int x = 0; x < enemyShips.length; x++)
        {
            if(!world.getObjects(enemyShips[x]).isEmpty()){
                isLevelClear = false;
                break;
            }
            else if(x == enemyShips.length - 1)
            {
                isLevelClear = true;
            }
        }
        return isLevelClear;
    }

    /**
     * Change the world and update wave/sub-wave number  
     */    
    public static void changeWorld()
    {
        for(int x = 0; x < worlds.length; x++)
        {
            if(isWorldsSet[x] == false)
            {                   
                try{
                    isWorldsSet[x] = true; //The current position in the isWorldsSet array is set to true
                    object = worlds[x].newInstance(); //Creates the new world
                    Greenfoot.setWorld((World) object); //Sets the new world
                    subWave = 1;
                    wave++; 
                    subWaveMessage.update("Sub-Wave: " + subWave, Color.RED, font); // Sub Wave text is reset to 1
                    waveMessage.update("Wave: " + wave, Color.GREEN, font); //Wave number text is updated to the next wave
                    break;
                }
                catch(InstantiationException e){                    
                    System.out.println(e.toString());
                }
                catch(IllegalAccessException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    /**
     * Spawn 5 new enemy ships at random locations on the far right side of the map
     */    
    public void populateLevel(int[] enemyChances, Object[] targets)
    {        
        numberOfEnemies = 5;         
        while(enemiesSpawned < numberOfEnemies)
        {            
            for(int y = 0; y < enemyChances.length; y++)
            {
                //Random number b/w 0 & 100 is chosen. The lower the number the better chance an enemy ship has of spawning
                enemyChance = Greenfoot.getRandomNumber(100);                
                if(enemyChance < enemyChances[y] && enemiesSpawned < numberOfEnemies)
                {                    
                    // Add the lucky enemy ship to the world
                    addObject((EnemyShip) createEnemyShip(enemyShips[y], targets), 700 + Greenfoot.getRandomNumber(300), Greenfoot.getRandomNumber(1000));
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
    public Object createEnemyShip(Class clss, Object[] targets) {      
        Constructor[] theConstructors = clss.getConstructors(); //Puts all the constructors of the given class in an array 
        for (int i = 0; i < theConstructors.length; i++) {
            try{
                object = theConstructors[i].newInstance(targets); //Uses the constructor in the current index [i] to create a new object
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
     * Returns true if the timer is currently running
     */    
    public static boolean isTimerOn()
    {
        return isTimerRunning;
    }   

    /**
     * Adds a power up to world at the specified location in a certain world
     */    
    public void spawnPowerUp(World world)
    {        
        world.addObject(powerUp, 500, 500);
    }

    /**
     * A power up is selected through chance
     * And it's image is set
     */    
    public void selectPowerUp()
    {
        int powerUpChance = Greenfoot.getRandomNumber(4);
        for(int x = -1; x < powerUpChance; x++)
        {
            if(x == powerUpChance - 1)
            {
                if(x < 0)
                {
                    x = 0;
                }
                powerUp.setPowerUpImage(powerUp.getPowerUpImages()[x]);
                break;
            }
        }
    }
}
