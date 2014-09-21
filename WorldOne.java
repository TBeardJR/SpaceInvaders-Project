import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;
import java.lang.reflect.*;
import java.util.*;
import java.awt.*;
/**
 * LVL 1
 * 
 * @author Group 10
 */
public class WorldOne extends WorldControl
{    
    //The chances that an enemy ship can spawn. The higher the number the better chance an enemy ship has of spawning
    public static final int ENEMY_ONE_CHANCE = 100;
    public static final int ENEMY_TWO_CHANCE = 0;
    public static final int ENEMY_THREE_CHANCE = 0;
    public static final int ENEMY_FOUR_CHANCE = 0;
    public static final int ENEMY_FIVE_CHANCE = 0;
    public static final int ENEMY_SIX_CHANCE = 0;
    public int enemyChances[] = {ENEMY_ONE_CHANCE, ENEMY_TWO_CHANCE, ENEMY_THREE_CHANCE, ENEMY_FOUR_CHANCE,
            ENEMY_FIVE_CHANCE, ENEMY_SIX_CHANCE};   
    public static EnemySpawnTask enemySpawnTask;    
    public Object[] target = new Object[] {base}; //The first target newly spawned enemy ships will have
    public WorldOne() 
    {
        super();
        GreenfootImage background = getBackground();          
        populateLevel(enemyChances, target); //Spawn enemy ships with a target               
        enemySpawnTask = new EnemySpawnTask(); //Create a new enemySpawnTask
        
        //enemySpawnTask is scheduled to spawn more enemy ships every 10 seconds (Third parameter)
        //The very first task doesn't take place until after a 10 second delay (Second parameter)
        enemySpawnTimer.schedule(enemySpawnTask, 10000, 10000);
        isTimerRunning = true;
    }   

    /**
     * Responsible for spawning enemy ships at the interval specified by the timer 
     * Cancels the timer and this task when there are no more sub waves left
     */
    class EnemySpawnTask extends TimerTask {
        private int wavesLeft = 1;
        public void run() {            
            if(wavesLeft > 0)
            {
                populateLevel(enemyChances, target);  
                wavesLeft--;
                subWave++;
                subWaveMessage.update("Sub-Wave: " + subWave, Color.RED, font);
            }
            else
            {
                enemySpawnTask.cancel();
                enemySpawnTimer.cancel();
                enemySpawnTimer.purge();
                isTimerRunning = false;
            }
        }
    }
}