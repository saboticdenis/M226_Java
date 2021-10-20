import greenfoot.*;

import java.awt.Color;

/**
 * Dies ist die Erde. Oder zumindest irgendein entfernter, nicht bewohnter Teil der Erde.
 * Hier können Greeps landen und nach Tomatenhaufen Ausschau halten ...
 * 
 * @author Michael Kölling
 * @version 1.0.1
 */
public class Earth extends World
{
    public static final int RESOLUTION = 1;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final int SCORE_DISPLAY_TIME = 240;

    private GreenfootImage map;
    private Ship ship;
    private int currentMap;
    
    // Daten für das Karten-Layout. Für jede Karte definiert das erste 3er-Tupel die Position 
    // des Raumschiffs: { ziel-y, start-x, start-y }. Das Schiff bewegt sich von start-y nach ziel-y.
    //
    // dann folgt eine beliebige Anzahl von zusätzlichen 3er-Tupeln, ein Tupel für jeden Tomatenhaufen,
    // im Format: { anzahl-Tomaten-in-Haufen, x, y }
    //
    private int[][][] mapData = {
        { {480, 100, 0}, {40, 721, 532}, {12, 400, 560}, {40, 615, 400},    // Karte 1
          {40, 642, 192}, {16, 128, 113}, {30, 400, 40} },
 
        { {496, 709, 0}, {10, 322, 422}, {40, 700, 241}, {40, 681, 49},     // Karte 2
          {10, 317, 54}, {50, 90, 174}, {40, 60, 339} },
          
        { {272, 394, 0}, {10, 39, 30}, {30, 71, 476}, {50, 398, 520},       // Karte 3
          {40, 655, 492} },          
    };

    private int[] scores;
    
    /**
     * Erzeugt eine neue Welt. 
     */
    public Earth()
    {
        super(WIDTH / RESOLUTION, HEIGHT / RESOLUTION, RESOLUTION);
        currentMap = 0;
        scores = new int[mapData.length];    // ein Punktekonto für jede Kart
        showMap(currentMap);
    }
    
    /**
     * Liefert "true", wenn an der angegebenen Koordinate Wasser ist.
     * (Wasser ist durch eine Farbe mit dominierendem Blauton gekennzeichnet.)
     */
    public boolean isWater(int x, int y)
    {
        Color col = map.getColorAt(x, y);
        return col.getBlue() > (col.getRed() * 2);
    }
    
    /**
     * Springt zu der angegebenen Kartennummer (1..n).
     */
    public void jumpToMap(int map)
    {
        clearWorld();
        currentMap = map-1;
        showMap(currentMap);
    }
    
    /**
     * Richtet die Startszenerie ein.
     */
    private void showMap(int mapNo)
    {
        map = new GreenfootImage("map" + mapNo + ".jpg");
        setBackground(map);
        Counter mapTitle = new Counter(Greep.getAuthorName() + " - Karte ", mapNo+1);
        addObject(mapTitle, 200, 20);
        int[][] thisMap = mapData[mapNo];
        for (int i = 1; i < thisMap.length; i++) {
            int[] data = thisMap[i];
            addObject(new TomatoPile(data[0]), data[1], data[2]);
        }
        int[] shipData = thisMap[0];
        ship = new Ship(shipData[0]);
        addObject(ship, shipData[1], shipData[2]);
    }
    
    /**
     * Das Spiel ist vorbei. Stoppen und Punktestand anzeigen.
     */
    public void mapFinished(int time)
    {
        displayScore(time);
        Greenfoot.delay(SCORE_DISPLAY_TIME);
        clearWorld();
        currentMap++;
        if (currentMap < mapData.length) {
            showMap(currentMap);
        }
        else {
            displayFinalScore();
            Greenfoot.stop();
        }
    }

    private void displayScore(int time)
    {
        int points = ship.getTomatoCount() + time;
        scores[currentMap] = points;
        ScoreBoard board = new ScoreBoard(Greep.getAuthorName(), "Karte " + (currentMap+1), "Punktestand: ", currentMap, scores);
        addObject(board, getWidth() / 2, getHeight() / 2);
    }
    
    private void displayFinalScore()
    {
        clearWorld();
        ScoreBoard board = new ScoreBoard(Greep.getAuthorName(), "Endstand", "Insgesamt: ", scores);
        addObject(board, getWidth() / 2, getHeight() / 2);
    }
    
    private void clearWorld()
    {
        removeObjects(getObjects(null));
    }
}
