import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PowerUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PowerUp extends SpaceCraft
{
    public boolean isSpawned = false;
    public Actor actor;
    public SpaceCraft spaceCraft;
    private GreenfootImage powerUpImage;
    private GreenfootImage apple = new GreenfootImage("apple1.png"); 
    private GreenfootImage fireRate = new GreenfootImage("FireRate.png"); 
    private GreenfootImage health = new GreenfootImage("Health.png"); 
    private GreenfootImage invincibility = new GreenfootImage("Invincible.png");
    public GreenfootImage[] powerUps = {fireRate, health, invincibility};
    public PowerUp()
    {
        isSpawned = true;
    }   

    public void act()
    {
        if(isSpawned == true)
        {
            if(canSee(Rocket.class)){
                if(getPowerUpImage().equals(health))
                {
                    restoreHealth((SpaceCraft) actor);
                }
                else if(getPowerUpImage().equals(fireRate))
                {
                    increaseFireRate((SpaceCraft) actor);
                }
                else if(getPowerUpImage().equals(invincibility))
                {
                    setInvincibility((SpaceCraft) actor);
                }
                removeSelfFromWorld(this);
                isSpawned = false;
            } 
        }
    }   

    public void setPowerUpImage(GreenfootImage image)
    {
        setImage(image);
    }

    public GreenfootImage getPowerUpImage()
    {
        return getImage();
    }
    
    public GreenfootImage[] getPowerUpImages()
    {
        return powerUps;
    }

    public void restoreHealth(SpaceCraft spaceCraft)
    {
        spaceCraft.setHealth(2500);
    }

    public void increaseFireRate(SpaceCraft spaceCraft)
    {
        spaceCraft.setGunReloadTime(spaceCraft.getGunReloadTime() / 2);
    }

    public void setInvincibility(SpaceCraft spaceCraft)
    {
        spaceCraft.setHealth(999999999);
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

}
