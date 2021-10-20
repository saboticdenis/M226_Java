import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.List;

/**
 * Das Spielbrett. 
 * 
 * Diese Klasse liefert den Hintergrund, einschließlich des Bereiches für die 
 * Anzeige der Detailinformationen und des Punktestandes.
 * 
 * @author: Michael Kölling
 * @version 1.0
 */
public class Board extends World
{
    private int board;
    private int tries;
    private int rolls;
    private int score;
    private Text boardLabel;
    private Text tryLabel;
    private Text rollsLabel;
    private Text scoreLabel;
    private int marblesMoving;
    private boolean failed;
    private boolean cleared;
    private boolean displayPoints = false;
    /**
     * Definition der verschiedenen Spielbrett-Level.
     *   Eine Reihe definiert ein Spielbrett-Level.
     *   Die erste Zahl gibt die Anzahl der Kugelstöße für dieses Spielbrett an.
     *   Die 2te und 3te sind die Koordinaten der goldenen Murmel.
     *   Anschließend: jedes Paar beschreibt eine silberne Murmel, es sei denn:
     *     die nächste Zahl ist eine 0, dann folgt ein horizontaler Balken;
     *     die nächste Zahl ist eine 1, dann folgt ein vertikaler Balken
     */
    private static final int[][] setups = {  // Spielbrett = 640x640
        { 1, 320, 480, 320, 200 },
        { 2, 320, 320, 180, 320, 460, 320 },
        { 2, 320, 480, 200, 200, 440, 200 },
        { 3, 320, 320, 290, 480, 200, 190, 470, 230 },
        { 1, 120, 520, 420, 120, 520, 180 },
        { 2, 320, 480, 320, 180 , 0, 225, 315},        
        { 1, 140, 400, 470, 400 , 1, 320, 433, 0, 320, 100},                          // indirekt über einen Balken
        { 1, 320, 480, 320, 90, 320, 200, 320, 310 },
        { 4, 320, 320, 200, 320, 440, 320, 260, 220, 380, 220, 260, 420, 380, 420 },  // sechszackiger Stern
        { 1, 300, 520, 90, 320, 300, 100, 0, 390, 320},
        { 2, 360, 500, 90, 340, 325, 100, 570, 250, 0, 360, 330},
        { 2, 320, 320, 450, 95, 560, 140, 190, 545, 80, 500 },
        { 4, 320, 480, 120, 120, 120, 220, 220, 120, 220, 220, 320, 120, 320, 220,    // 10 in 2er-Reihen
             420, 120, 420, 220, 520, 120, 520, 220 },
        { 1, 284, 546, 387, 293 , 1, 75, 320, 0, 320, 75, 0, 420, 380 },              // indirekt über 2 Balken
        { 3, 536, 130, 146, 508 , 0, 410, 230, 0, 230, 410 },            
        { 4, 320, 135, 110, 200, 530, 200, 240, 470, 400, 470, 0, 320, 340 }, 
    };
    
    public Board()
    {    
        // Erzeugt eine neue Welt mit 20x20 Zellen mit einer Zellengröße von 10x10 Pixel.
        super (840, 640, 1);
        Greenfoot.setSpeed (52);
        setPaintOrder (ScoreBoard.class, Points.class, Marble.class, Arrow.class);

        board = 1;
        tries = 3;
        score = 0;
        
        createCounters ();
        setUp (board);
    }

    /**
     * Die Aktionen für das Spielbrett: Prüft, ob wir erfolgreich waren oder nicht.
     */
    public void act() 
    {
        if (cleared) {
            Marble goldMarble = getGoldMarble();
            if (goldMarble != null) {
                addObject (new Points ("200"), goldMarble.getX()+70, goldMarble.getY()-30);
                Greenfoot.playSound("ping.wav");
                addScore (300 + rolls*300); // 100 Punkte für das Leeren des Spielbretts 
                                           // 200, wenn die goldene Murmel noch auf dem Spielbrett liegt,
                                           // plus 300 für jeden nicht genutzten Stoß
            }
            else {
                addScore (100 + rolls*300); // 100 Punkte für das Leeren des Spielbretts
                                           // plus 300 für jeden nicht genutzten Stoß
            }
            cleared = false;
            displayPoints = true;
        }
        else if (displayPoints) {
            if ( pointsImageGone() ) {
                displayPoints = false;
                nextBoard();
            }
        }
        else if (failed) {
            lostBoard ();
        }
    }
    
    /**
     * Hält fest, dass eine Murmel begonnen oder gestoppt hat, sich zu bewegen.
     */
    public void marbleMoving(boolean moves)
    {
        if (moves) {
            marblesMoving++;
        }
        else {
            marblesMoving--;
            if (marblesMoving == 0) {           // alle Bewegung wurde gestoppt
                if ( isBoardClear() ) {
                    cleared = true;
                }
                else if (rolls == 0) {
                    failed = true;
                }
                else if (! haveGoldMarble()) {
                    failed = true;
                }
            }
        }
    }

    /**
     * Hält fest, dass ein Stoß beendet wurde.
     */
    public void countRoll()
    {
        rolls--;
        rollsLabel.setText("Restliche Stöße: " + rolls);
    }

    /**
     * Die silberne Murmel ist vom Spielbrett gefallen.
     */
    public void steelMarbleDropped()
    {
        addScore(10);
    }
    
    /**
     * Die goldene Murmel ist vom Spielbrett gefallen.
     */
    public void goldMarbleDropped()
    {
        // nichts zu tun - wir warten, bis alle Bewegung beendet wurden, bevor wir etwas machen
        //failed = true;
    }
    
    /**
     * Prüft, ob das Spielbrett geleert wurde.
     */
    private boolean isBoardClear()
    {
        int marbles = getObjects (Marble.class).size();
        int gold = getObjects (GoldMarble.class).size();
        return marbles - gold == 0;
    }
    
    /**
     *  Prüft, ob die goldene Murmel noch auf dem Spielbrett liegt.
     */
    private boolean haveGoldMarble()
    {
        return getGoldMarble() != null;
    }
    
    /**
     * Prüft, ob das Spielbrett geleert wurde.
     */
    private boolean pointsImageGone()
    {
        return getObjects (Points.class).size() == 0;
    }
    
    /**
     * Prüft, ob die goldene Murmel auf dem Spielbrett liegt.
     */
    private Marble getGoldMarble()
    {
        List<Actor> marbles = getObjects (GoldMarble.class);
        if (marbles.size() == 0) {
            return null;
        }
        else {
            return (Marble) marbles.get(0);
        }
    }
    
    /**
     * Hält fest, dass ein Versuch, das Spielbrett zu leeren, nicht erfolgreich war.
     * Startet erneut, wenn es noch Versuche gibt, ansonsten ist das Spiel verloren.
     */
    public void lostBoard() 
    {
        tries--;
        tryLabel.setText ("Restliche Versuche: " + tries);
        if (tries == 0) {
            Greenfoot.playSound("sad-trombone.wav");
            gameOver("Spiel beendet");
        }
        else {
            Greenfoot.delay(100);
            setUp (board);
        }
    }
    
    /**
     * Spiel wurde verloren.
     */
    public void gameOver(String message) 
    {
        addObject (new ScoreBoard(message, score), 320, getHeight()/2);
        Greenfoot.stop();
    }

    /**
     * Hält den Punktestand fest.
     */
    public void addScore(int points)
    {
        score = score + points;
        scoreLabel.setText ("Punktestand: " + score + "      ");
    }
    
    /**
     * Prüft, ob ein gegebener Punkt außerhalb des Spielbretts liegt.
     */
    public boolean isOffBoard (int x, int y) 
    {
        return (x < 20 || x > 620 || y < 20 || y > 620);
    }

    /**
     * Zeigt das nächste Spielbrett (sofern es eines gibt). Andernfalls ist das Spiel gewonnen.
     */
    private void nextBoard()
    {
        Greenfoot.delay(60);
        board++;
        if (board % 3 == 0) {
            tries++;
            tryLabel.setText("Restliche Versuche: " + tries);
        }
        if (board <= setups.length) {
            boardLabel.setText("BRETT " + board);
            setUp (board);
        }
        else {
            gameOver ("Du hast gewonnen!");
        }
    }
    
    private void setUp(int boardNumber)
    {
        removeObjects (getObjects (Marble.class));
        removeObjects (getObjects (Bar.class));
        
        int[] current = setups[boardNumber-1];
        int i = 0;
        rolls = current[i++];
        
        addObject ( new GoldMarble(), current[i++], current[i++]);

        while (i < current.length) {
            int next = current[i++];
            if (next == 0) {
                addObject ( new Bar(false), current[i++], current[i++]);
            }
            else if (next == 1) {
                addObject ( new Bar(true), current[i++], current[i++]);
            }
            else {
                addObject ( new Marble(), next, current[i++]);
            }
        }
        
        rollsLabel.setText ("Restliche Stöße: " + rolls);
        failed = false;
        cleared = false;
        marblesMoving = 0;
    }
    
    private void createCounters()
    {
        boardLabel = new Text("BRETT " + board);
        addObject (boardLabel, 670, 120);
        tryLabel = new Text("Restliche Versuche: " + tries);
        addObject (tryLabel, 670, 150);
        rollsLabel = new Text("Restliche Stöße: " + rolls);
        addObject (rollsLabel, 670, 200);
        scoreLabel = new Text("Punktestand: " + score + "      ");
        addObject (scoreLabel, 670, 230);
    }
}
