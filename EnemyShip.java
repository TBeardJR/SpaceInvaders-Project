import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * General template for all enemy ships
 * it's in charge of setting targets, locking on targets, moving towards targets
 * and shooting at targets
 */
public class EnemyShip extends SpaceCraft
{
    private Actor target; //The target of an enemy ship
    private Actor newTarget; //A new target for an enemy ship
    private Actor mainTarget; //Main target of an enemy ship
    private Vector direction; //Direction of movement   
    private int shotsFired;    
    private boolean isInRange = false;   
    public Font font = new Font("Serif", Font.BOLD , 10); 
    public HUDMessage enemyHealthMessage; 
    public EnemyShip(Actor target)
    {
        setTarget(target);
        mainTarget = target;        
        setReloadDelayCount(0);        
        shotsFired = 0;
        direction = new Vector(0, 0);
        enemyHealthMessage = new HUDMessage("HP: " + getHealth(), 160, 25, Color.RED, font);  
    }

    /**
     * Set the target for this actor
     */
    public void setTarget(Actor target)
    {
        this.target = target;        
    }

    /**
     * Get the target for this actor
     */
    public Actor getTarget()
    {
        return target;
    }

    /**
     * Moves toward this actors target. 
     */
    public void moveTowardTarget(int rotation)
    {       
        moveInDirection(this, rotation);
    }

    /**
     * Face toward this actors target.      
     */
    public void faceTowardTarget(EnemyShip enemyShip)
    {
        if(getTarget().getWorld() != null)
        {
            turnTowards(getTarget().getX(), getTarget().getY());
            turn(90);  
            direction.setDirection(enemyShip.getRotation()); 
        }
    }

    /**
     * Returns true if a certain actor is within the specified range
     * This actor becomes the new target
     * If no actor is in range is goes back to the main target and returns false
     */
    public boolean isTargetInRange(int range)
    {
        if(!getObjectsInRange(range, Rocket.class).isEmpty())
        {
            isInRange = true;
            newTarget = (Actor) getObjectsInRange(range, Rocket.class).get(0);
            setTarget(newTarget);            
        }
        else if(!getObjectsInRange(range, Base.class).isEmpty())
        {
            isInRange = true;
            newTarget = (Actor) getObjectsInRange(range, Base.class).get(0);
            setTarget(newTarget);
        }        

        else
        {
            setTarget(mainTarget);
            isInRange = false;
        }        
        return isInRange;
    }

    /**
     * Shoots laser at target
     */
    public void shootAtTarget(String laserType, int gunReloadTime)
    {
        fire(laserType, gunReloadTime);
    }    
}
