import greenfoot.*;

/**
 * Die Klasse Animal dient als Oberklasse zu allen Tieren in dieser Räuber-Beute-Simulation.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Animal extends Actor
{
    // Das Alter des Tiers.
    private int age;
    // Ist dieses Tier noch lebendig?
    private boolean alive;

    /**
     * Erzeugt ein neues Tier mit dem Alter 0 (Neugeborenes).
     */
    public Animal()
    {
        age = 0;
        alive = true;
    }
    
    /**
     * Prüft, ob dieses Tier noch lebendig ist.
     * @return True wenn dieses Tier noch lebendig ist.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Teilt dem Tier mit, dass es tot ist :(
     */
    public void setDead()
    {
        alive = false;
        World world = getWorld();
        if (world != null) {
            world.removeObject(this);
        }
    }
    
    /**
     * Liefert das Alter des Tiers zurück.
     * @return das Alter des Tiers.
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Setzt das Alter des Tiers zurück.
     * @param age das Alter des Tiers.
     */
    public void setAge(int age)
    {
        this.age = age;
    }
    
    /**
     * Liefert das Feld zurück, in dem wir uns befinden.
     */
    public Field getField()
    {
        return (Field) getWorld();
    }
}
