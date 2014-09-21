import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * General template for all lasers
 * it's in control removing health from ships and
 * checking if a laser had made contact with a ship
 */
public class Laser extends Mover
{
    /** A bullet looses one life each act, and will disappear when life = 0 */
    private int life = 30;

    /** The damage this bullet will deal */
    private int damage;   
    public Actor actor;
    public SpaceCraft spaceCraft;
    public Laser(Vector speed, int rotation)
    {
        super(speed);
        setRotation(rotation);
        increaseSpeed(new Vector(rotation, 15)); 
        damage = 0;        
    }   
    
     /**
     * Return true if we can see an object of class 'clss' right where we are. 
     * False if there is no such object here.
     */
    public boolean canSee(Class clss)
    {
        actor = getOneObjectAtOffset(0, 0, clss);
        spaceCraft = (SpaceCraft) actor;
        return actor != null;        
    }
    
    /**
     * Remove health from the space craft the bullet is touching    
     */
    public void removeHealth()
    {       
        spaceCraft.setHealth(spaceCraft.getHealth() - getDamage());
    }    
    
    /**
     * Set the damage a bullet does    
     */
    public void setDamage(int damage)
    {
        this.damage = damage;
    } 
    
    /**
     * Get the damage a bullet does
     */
    public int getDamage()
    {
        return damage;
    } 
   
}