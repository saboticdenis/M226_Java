import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.List;

/**
 * Eine Murmel in einem Murmelspiel. Die Murmel kann sich bewegen und andere Murmeln vom Spielbrett stoßen, 
 * und wenn sie über den Spielbrettrand schießt, fällt sie herunter und verschwindet.
 * 
 * Die gesamte Bewegungsfunktionalität ist in der Oberklasse SmoothMover implementiert.
 * 
 * @author: Michael Kölling
 * @version 1.0
 */
public class Marble extends SmoothMover
{
    private static final double DIAMETER = 54.0;
    private static final int RADIUS = 27;
    private static final int BAR_SHADOW = 10;
    
    private boolean moving;
    private boolean out;
    private boolean haveHitLastAct;   // true, wenn der letzte act-Schritt ein Treffer auf einen Balken war
    private GreenfootImage image;
    
    /**
     * Erzeugt eine Murmel.
     */
    public Marble()
    {
        image = getImage();
    }
    
    /**
     * Aktionen. Entweder bewegt sich die Murmel oder sie fällt vom Spielbrett.
     */
    public void act() 
    {
        if (out) {
            move();
            drop();
        }
        else if (moving) {
            move();
            checkCollision();
            accelerate(0.99);  // Beschleunigung um den Faktor < 1 - d.h. Verlangsamung
            if (getSpeed() < 0.1) {
                setMoving(false);
            }
            checkBoardBoundary();
        }
    }
    
    /**
     * Prüft, ob wir mit einer anderen Murmel zusammenstoßen.
     */
    private void checkCollision()
    {
        // ermittelt zuerst alle Murmeln, deren Bild sich mit unserem überschneidet (die anderen müssen wir nicht prüfen))
        List<Marble> marbles = (List<Marble>) getIntersectingObjects(Marble.class);
        for (Marble marble : marbles) {
            // schaut, welche von diesen sich wirklich mit der Murmel überschneiden (das Bild ist
            // größer als die Murmel, so dass wir den Schatten zeichnen können etc.).
            if ( haveHit(marble) ) {
                doCollision(marble);
            }
        }

        // Prüft jetzt auf Holzbalken
        List<Bar> bars = (List<Bar>) getIntersectingObjects(Bar.class);
        for (Bar bar : bars) {
            // schaut, welche von diesen sich wirklich  mit dem Balken überschneiden (das Bild ist
            // größer als der Balken, da es den Schatten umfasst).
            if ( haveHit(bar) ) {
                doCollision(bar);
            }
        }
    }
    
    /**
     * Wir haben eine andere Murmel getroffen. Führe die Kollision jetzt durch (d.h.: berechne die neuen
     * Bewegungsvektoren für uns und die andere Murmel).
     */
    private void doCollision(Marble marble)
    {
        double dx = this.getExactX() - marble.getExactX();
        double dy = this.getExactY() - marble.getExactY();
        int direction = (int) Math.toDegrees(Math.atan2(dy, dx));
        double angle = direction - getMovement().getDirection();
        
        // wenn der Winkel nicht zwischen 90 und 270 liegt, dann wurden wir von hinten getroffen und die andere Kugel wird nicht bewegt
        if (Math.abs(angle) < 90 || Math.abs(angle) > 270) {
            return;
        }

        Greenfoot.playSound("click.wav");
        
        double length = Math.cos(Math.toRadians(angle)) * getMovement().getLength();
        marble.addForce( new Vector (direction, length) );
        marble.setMoving(true);
        
        this.addForce (new Vector (direction + 180, length) );
        //System.out.println("dir (after): " + direction + "  " + this);
    }
    
    /**
     * Wir haben eine andere Murmel getroffen. Führe die Kollision jetzt durch (d.h.: berechne die neuen
     * Bewegungsvektoren für uns und die andere Murmel).
     */
    private void doCollision(Bar bar)
    {
        int dx = Math.abs (this.getX() - bar.getX()) - bar.getImage().getWidth()/2 + BAR_SHADOW - RADIUS;
        int dy = Math.abs (this.getY() - bar.getY()) - bar.getImage().getHeight()/2 + BAR_SHADOW - RADIUS;
        boolean hitSide = dx > dy;
        boolean hitTopBottom = ! hitSide;
        
        if (Math.abs(dx-dy) <= 2) {     // Wenn sie nahezu gleich sind, haben wir die Ecke getroffen
            hitSide = hitTopBottom = true;
        }
        if (hitSide) {  // haben von der Seite getroffen
            getMovement().revertHorizontal();
        }
        if (hitTopBottom) {  // haben von oben oder unten getroffen
            getMovement().revertVertical();
        }
        accelerate (0.9);    // verlieren etwas Geschwindigkeit beim Abprallen

        Greenfoot.playSound("tock.wav");
    }
    
    /**
     * Prüft, ob wir die gegebene Murmel getroffen haben. Wir haben sie getroffen, wenn der Abstand von uns aus
     * (gemessen ab dem Mittelpunkt) kleiner als unser Durchmesser ist.
     */
    private boolean haveHit(Marble marble)
    {
        int dx = Math.abs (this.getX() - marble.getX());
        int dy = Math.abs (this.getY() - marble.getY());
        double distance = Math.sqrt(dx*dx+dy*dy);
        
        return distance < DIAMETER;
    }
    
    /**
     * Prüft, ob wir die gegebene Murmel getroffen haben. Wir haben sie getroffen, wenn der Abstand von uns aus
     * (gemessen ab dem Mittelpunkt) kleiner als unser Durchmesser ist.
     */
    private boolean haveHit(Bar bar)
    {
        if (haveHitLastAct) {       // stellt sicher, dass wir einen Treffer in einer Reihe nicht zweimal registrieren
            haveHitLastAct = false;
            return false;
        }
        else {
            int dx = Math.abs (this.getX() - bar.getX()) - bar.getImage().getWidth()/2 + BAR_SHADOW - RADIUS;
            int dy = Math.abs (this.getY() - bar.getY()) - bar.getImage().getHeight()/2 + BAR_SHADOW - RADIUS;
            haveHitLastAct = (dx < 0) && (dy < 0);
        
            return haveHitLastAct;
        }
    }
    
    /**
     * Prüft, ob wir vom Spielbrett gefallen sind.
     */
    private void checkBoardBoundary()
    {
        Board board = (Board) getWorld();
        if ( board.isOffBoard(getX(), getY()) ) {
            out = true;
        }
    }
    
    /**
     * Zeigt die Fallbewegung (wenn wir vom Spielbrett fallen). Dazu wird lediglich unser Bild kleiner skaliert
     * Wenn es klein genug ist, verschwindet es aus der Welt.
     */
    private void drop()
    {
        if(getImage().getWidth() < 10) {
            Greenfoot.playSound("tock.wav");
            hasDropped();
            Board board = getBoard();
            board.removeObject(this);
            board.marbleMoving(false);
        }
        else {
            GreenfootImage img = new GreenfootImage(image);
            img.scale ( getImage().getWidth()-6, getImage().getHeight()-6 );
            setImage (img);
        }
    }

    /**
     * Diese Murmel ist vom Spielbrett gefallen. Ergreife entsprechende Maßnahmen.
     */
    public void hasDropped()
    {
        Board board = getBoard();
        board.steelMarbleDropped();
        int x = Math.max (getX(), 50);
        int y = Math.max (getY(), 40);
        y = Math.min (y, board.getHeight()-40);
        board.addObject (new Points("10"), x, y);
    }
    
    /**
     * Prüft, ob wir uns bewegen.
     */
    public boolean isMoving()
    {
        return moving;
    }

    /**
     * Setzt diese Murmel in den Zustand Bewegen oder nicht Bewegen.
     */
    public void setMoving(boolean move)
    {
        if (moving != move) {
            getBoard().marbleMoving(move);
        }
        moving = move;
    }
    
    /**
     * Liefert das Spielbrett zurück, auf dem wir gerade spielen.
     */
    public Board getBoard()
    {
        return (Board)getWorld();
    }
}
