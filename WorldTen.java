import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.reflect.*;
/**
 * LVL 10
 */
public class WorldTen extends WorldControl
{
    public WorldTen() 
    {
        super();
        GreenfootImage background = getBackground();        
        spawnBoss();            
    } 

    /**
     * Spawn EnemyShipSix     
     */
    public void spawnBoss()
    {
        addObject(base, 150, 400);
        addObject(new EnemyShipSix(base), 1100, 400);        
        addObject(Rocket.healthMessage, Rocket.getLastX() - 30, Rocket.getLastY() - 30);
        addObject(Base.baseHealthMessage, Base.getLastX() - 30, Base.getLastY() - 50);
    }    
}
