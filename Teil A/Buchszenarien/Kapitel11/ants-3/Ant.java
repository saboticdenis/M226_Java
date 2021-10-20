import greenfoot.*;

/**
 * Eine Ameise, die Futter sammelt.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class Ant extends Creature
{
    /** Nach wie vielen Schritten können wir einen Tropfen Pheromon abgeben. */
    private static final int MAX_PH_LEVEL = 18;

    /** Wie lange halten wir eine Richtung ein, nachdem wir auf Pheromone gestoßen sind. */
    private static final int PH_TIME = 30;

    /** Zeigt an, ob wir Futter mit uns herumtragen. */
    private boolean carryingFood = false;

    /** Über wie viel Pheromon verfügen wir gerade. */
    private int pheromoneLevel = MAX_PH_LEVEL;

    /** Wie gut können wir uns an das letzte Pheromon erinnern - je größer der Wert, desto länger */
    private int foundLastPheromone = 0;

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
            handlePheromoneDrop();
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
        if (foundLastPheromone > 0) { // wenn wir uns noch erinnern können ...
            foundLastPheromone--;
            walkAwayFromHome();
        }
        else if (smellPheromone()) {
            walkTowardsPheromone();
        }
        else {
            randomWalk();
        }
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

    /**
     * Prüft, ob wir schon Pheromon abgeben können. Wenn ja, abgeben.
     */
    private void handlePheromoneDrop()
    {
        if (pheromoneLevel == MAX_PH_LEVEL) {
            Pheromone ph = new Pheromone();
            getWorld().addObject(ph, getX(), getY());
            pheromoneLevel = 0;
        }
        else {
            pheromoneLevel++;
        }
    }
    
    /**
     * Prüft, ob wir Pheromone riechen können. Wenn ja, wird "true" zurückgeliefert, andernfalls false.
     */
    public boolean smellPheromone()
    {
        Actor ph = getOneIntersectingObject(Pheromone.class);
        return (ph != null);
    }

    /**
     * Wenn wir Pheromone riechen können, geh darauf zu. Wenn nicht, tue nichts.
     */
    public void walkTowardsPheromone()
    {
        Actor ph = getOneIntersectingObject(Pheromone.class);
        if (ph != null) {
            headTowards(ph);
            walk();
            if (ph.getX() == getX() && ph.getY() == getY()) {
                foundLastPheromone = PH_TIME;
            }
        }
    }
}
