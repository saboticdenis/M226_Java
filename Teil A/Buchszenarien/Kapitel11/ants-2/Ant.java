import greenfoot.*;

/**
 * Eine Ameise, die Futter sammelt.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class Ant extends Creature
{
    /** Zeigt an, ob wir Futter mit uns herumtragen. */
    private boolean carryingFood = false;

    /**
     * Erzeugt eine Ameise mit gegebenen Heimathügel. Die Anfangsgeschwindigkeit ist null (keine Bewegung).
     */
    public Ant(AntHill home)
    {
        setHomeHill(home);
    }

    /**
     * Tut, was eine Ameise tun muss.
     */
    public void act()
    {
        if (carryingFood) {
            walkTowardsHome();
            checkHome();
        }
        else {
            searchForFood();
        }
    }

    /**
     * Wandert herum auf der Suche nach Futter.
     */
    private void searchForFood()
    {
        randomWalk();
        checkFood();
    }

    /**
     * Sind wir zu Hause? Wenn ja, Futter fallen lassen und wieder auf die Suche gehen.
     */
    private void checkHome()
    {
        if (atHome()) {
            dropFood();
        }
    }

    /**
     * Sind wir zu Hause?
     */
    private boolean atHome()
    {
        if (getHomeHill() != null) {
            return (Math.abs(getX() - getHomeHill().getX()) < 4) && (Math.abs(getY() - getHomeHill().getY()) < 4);
        }
        else {
            return false;
        }
        
    }
    /**
     * Gibt es hier, wo wir sind, Futter? Wenn ja, etwas mitnehmen!
     */
    public void checkFood()
    {
        Food food = (Food) getOneIntersectingObject(Food.class);
        if (food != null) {
            takeFood(food);
        }
    }

    /**
     * Nimmt etwas Futter von einem Futterhaufen.
     */
    private void takeFood(Food food)
    {
        carryingFood = true;
        food.takeSome();
        setImage("ant-with-food.gif");
    }

    /**
     * Unser Futter wird im Ameisenhügel abgeliefert.
     */
    private void dropFood()
    {
        carryingFood = false;
        getHomeHill().countFood();
        setImage("ant.gif");
    }

}
