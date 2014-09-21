import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LaserBeam here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LaserBeam extends Laser
{
    public LaserBeam(Vector speed, int rotation)
    {
        super(speed, rotation);
        setRotation(rotation);
        increaseSpeed(new Vector(rotation, 15)); 
        setDamage(5);        
    }

    /**
     * Remove health and disappear if this laser is touching the base or rocket
     * Otherwise Keep moving in same direction
     * If the this laser is at the edge of the world it will disappear
     */
    public void act() 
    {
        if(!atWorldEdge())
        {
            if ( canSee(Base.class) || canSee(Rocket.class)) 
            {
                removeHealth();
                getWorld().removeObject(this);                     
            }
            else
            {
                moveInDirection(this, 90);
            }
        }
        else
        {
            getWorld().removeObject(this);
        }
    }
}
