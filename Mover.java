import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A thing that can move around with a certain speed.
 * 
 * @author Poul Henriksen
 */
public abstract class Mover extends Actor
{
    private Vector movement = new Vector();
    
    private double x = 0;
    private double y = 0;
    
    public static int shotsFired = 0;    
    
    public Mover()
    {
        
    }
    
    /**
     * Create new thing initialised with given speed.
     */
    public Mover(Vector speed)
    {
        movement = speed;
        
    }
    
    /**
     * Move forward one step.
     */
    public void move() 
    {
        x = x + movement.getX();
        y = y + movement.getY();
        if(x >= getWorld().getWidth()) {
            x = 0;
        }
        if(x < 0) {
            x = getWorld().getWidth() - 1;
        }
        if(y >= getWorld().getHeight()) {
            y = 0;
        }
        if(y < 0) {
            y = getWorld().getHeight() - 1;
        }
        setLocation(x, y);
    }
    
    /**
     * Moves forward with respect to the direction the actor is facing
     */
    public void moveInDirection(Actor actor, int rotation) 
    {
        movement.setDirection(actor.getRotation() - rotation);
        x = x + movement.getX();
        y = y + movement.getY();
        if(x >= getWorld().getWidth()) {
            x = 0;
        }
        if(x < 0) {
            x = getWorld().getWidth() - 1;
        }
        if(y >= getWorld().getHeight()) {
            y = 0;
        }
        if(y < 0) {
            y = getWorld().getHeight() - 1;
        }
        setLocation(x, y);        
    }
    
    public void setLocation(double x, double y) 
    {
        this.x = x;
        this.y = y;
        super.setLocation((int) x, (int) y);
    }
    
    public void setLocation(int x, int y) 
    {
        setLocation((double) x, (double) y);
    }

    /**
     * Increase the speed with the given vector.
     */
    public void increaseSpeed(Vector s) 
    {
        movement.add(s);
    }   
    
    /**
     * Return the current movement.
     */
    public Vector getMovement() 
    {
        return movement;
    } 
    
    /**
     * Test if we are close to one of the edges of the world. Return true is we are.
     */
    public boolean atWorldEdge()
    {
        if(getX() < 20 || getX() > getWorld().getWidth() - 20)
            return true;
        if(getY() < 20 || getY() > getWorld().getHeight() - 20)
            return true;
        else
            return false;
    }
    
}