import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Ein Gesteinsbrocken.
 * 
 * @author mik
 * @version 1.0
 */
public class Rock extends Actor
{
    private static final int NUM_FRAGMENTS = 40;
    
    /**
     * Agiere - tue, was immer ein Gesteinsbrocken so tun möchte. Diese Methode wird immer aufgerufen,
     * wenn in der Umgebung der Button 'Act' oder 'Run' gedrückt wird.
     */
    public void act() 
    {
        String key = Greenfoot.getKey();
        if (key != null) {
            explode();
        }
    }
    
    public void explode()
    {
        placeDebris (getX(), getY(), NUM_FRAGMENTS);
        getWorld().removeObject(this);
    }
    
    private void placeDebris(int x, int y, int numFragments)
    {
        for (int i=0; i < numFragments; i++) {
            getWorld().addObject ( new Debris(), x, y );
        }
    }
}
