import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GreenLaser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GreenLaser extends Laser
{
    public Class[] enemyShips = {EnemyShipOne.class, EnemyShipTwo.class, EnemyShipThree.class, EnemyShipFour.class
        ,EnemyShipFive.class, EnemyShipSix.class};        
    public boolean foundTarget = false;   
    public GreenLaser(Vector speed, int rotation)
    {
        super(speed, rotation);
        setRotation(rotation);
        increaseSpeed(new Vector(rotation, 15));
        setDamage(25);
        Greenfoot.playSound("EnergyGun.wav");
    }

    /**
     * Remove health and disappear if this laser is touching an enemy ship
     * Otherwise Keep moving in same direction
     * If the this laser is at the edge of the world it will disappear
     */
    public void act()
    {
        if(!atWorldEdge()){
            for(int x = 0; x < enemyShips.length; x++)
            {
                if ( canSee(enemyShips[x])) 
                {                                  
                    getWorld().removeObject(this);
                    removeHealth();                    
                    foundTarget = true;
                    break;
                }
            }  
            if(foundTarget == false)
            {
                move();
            }
        }
        else
        {
            getWorld().removeObject(this);
        }
    }  
}
