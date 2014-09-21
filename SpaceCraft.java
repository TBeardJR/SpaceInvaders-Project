import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is a general template for all space ships
 * It's in control of the firing mechanism and 
 * keeping track of the different status's of a ship
 */
public class SpaceCraft extends Mover
{
    private int shotsFired;
    private Laser laser;
    private int reloadDelayCount;
    private int gunReloadTime;
    public boolean isInWorld;
    public static int killCount = 0;
    private int health;
    public boolean isAlive;
    public SpaceCraft()
    {
        setReloadDelayCount(0);
        isInWorld = true;
        health = 0;
        isAlive = true;
    }   
    
    /**
     * Fire a laser if the gun is ready.
     */
    public void fire(String laserType, int gunReloadTime) 
    {
        if(reloadDelayCount >= gunReloadTime) {            
            Laser laser = decideLaserType(laserType);
            getWorld().addObject(laser, getX(), getY());
            laser.move();
            shotsFired++;            
            setReloadDelayCount(0);
        }        
    }
    
    /**
     * Determine the type of Laser to be fired
     */
    public Laser decideLaserType(String laserType)
    {
        if(laserType.equalsIgnoreCase("Laser"))
        {
            laser = new LaserBeam(getMovement().copy(), getRotation());
        }
        else if(laserType.equalsIgnoreCase("GreenLaser"))
        {
            laser = new GreenLaser(getMovement().copy(), getRotation());
        }   
        else if(laserType.equalsIgnoreCase("RedLaser"))
        {
            laser = new RedLaser(getMovement().copy(), getRotation());
        }
        else if(laserType.equalsIgnoreCase("GreenLaserBeam"))
        {
            laser = new GreenLaserBeam(getMovement().copy(), getRotation());
        }
        else if(laserType.equalsIgnoreCase("GiantPurpleLaser"))
        {
            laser = new GiantPurpleLaser(getMovement().copy(), getRotation());
        }
        return laser;
    }    
    
    /**
     * Increment the reloadDelayCount count by one
     */
    public void incrementReloadDelayCount()
    {
        reloadDelayCount++;
    }
    
    /**
     * Set the reloadDelayCount number
     */
    public void setReloadDelayCount(int reloadDelayCount)
    {
        this.reloadDelayCount = reloadDelayCount;
    }
    
    /**
     * Set the time needed for re-loading. The shorter this time is,
     * the faster the firerate.
     */
    public void setGunReloadTime(int reloadTime)
    {
        gunReloadTime = reloadTime;
    } 
    
    /**
     * Set the health of the spacecraft
     */
    public void setHealth(int health)
    {
        this.health = health;
    }
    
    /**
     * Get the health of the spacecraft
     */
    public int getHealth()
    {
        return this.health;
    }
    
    /**
     * Get gunReloadTime
     */
    public int getGunReloadTime()
    {
        return gunReloadTime;
    } 
    
    /**
     * Return the number of shots fired from this rocket.
     */
    public int getShotsFired()
    {
        return shotsFired;
    }
    
    /**
     * How many enemy ships killed.
     */
    public static int getKillCount()
    {
        return killCount;
    }
    
    /**
     * How many enemy ships killed.
     */
    public static void setKillCount(int killCount_)
    {
        killCount = killCount_;
    }

    /**
     * Increment the kill count by one
     */
    public static void incrementKillCount()
    {
        killCount++;
    }
    
    /**
     * Remove the specified actor from the world
     */
    public void removeSelfFromWorld(Actor actor)
    {
        getWorld().removeObject(actor);
    }
    
    /**
     * Returns true if the spacecraft has health
     */
    public boolean hasHealth()
    {
        if(getHealth() <= 0)
        {
            isAlive = false;
        }
        else
        {
            isAlive = true;
        }
        return isAlive;
    }
    
}
