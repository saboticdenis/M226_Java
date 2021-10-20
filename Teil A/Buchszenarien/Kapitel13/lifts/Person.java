import greenfoot.World;
import greenfoot.Actor;

public class Person extends Actor
{
    private static final int ST_ON_FLOOR = 0;
    private static final int ST_IN_LIFT = 1;

    private Floor originFloor;
    private int targetFloor;
    private int status;
    
    public Person()
    {
        this(null, null);
    }

    public Person(Floor floor, Building building)
    {
        setImage("person.gif");
        originFloor = floor;
        status = ST_ON_FLOOR;
        if((floor != null) && (building != null)) {
            targetFloor = pickRandomFloor(originFloor.getFloorNumber(), building);
            if(goingUp()) {
                floor.pressButton(Button.UP);
            }
            else {
                floor.pressButton(Button.DOWN);
            }
        }
    }

    public void act()
    {
        if(status == ST_ON_FLOOR) {
        }
    }


    /**
     * Liefert zurück, ob wir hochfahren wollen oder nicht (andernfalls wollen wir runterfahren).
     */
    private boolean goingUp()
    {
        return targetFloor > originFloor.getFloorNumber();
    }

    /**
     * Wählt ein beliebiges Stockwerk (aber nicht das, auf dem wir uns gerade befinden).
     */
    private int pickRandomFloor(int currentFloor, Building building)
    {
        int floor;
        do {
            floor = building.getRandomFloor();
        } while (floor == currentFloor);
        return floor;
    }
}
