import greenfoot.*; 

/**
 * Map ist eine Welt, die eine Google-Karte als Hintergrund anzeigt.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class MapViewer extends World
{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;

    private Map map;

    /**
     * Erzeugt ein neues MapViewer-Objekt, in dessen Mittelpunkt sich die Universität von Kent befindet, 
     * aber ziemlich weit herausgezoomt.
     */
    public MapViewer()
    {
        super(WIDTH, HEIGHT, 1);
        
        // Orte können durch Name, PLZ oder Längen-/Breitegeraden angegeben werden
        
        String location = "CT2 7NF, UK";

        // andere Orte zum Ausprobieren:
        
        //String location = "Paris, France";
        //String location = "Brazil";
        //String location = "Taj Mahal";
        //String location = "Brooklyn Bridge, New York, NY";
        //String location = "51.178882,-1.826216";
        
        map = new Map(location, WIDTH, HEIGHT, 4);
        setBackground(map.getImage());
        
        //setType("hybrid");

        prepare();
    }

    /**
     * In die aktuelle Ansicht hineinzoomen.
     */
    public void zoomIn() 
    {
        map.zoomIn(1);
        setBackground(map.getImage());
    }

    /**
     * Aus der aktuellen Ansicht herauszoomen.
     */
    public void zoomOut() 
    {
        map.zoomOut(1);
        setBackground(map.getImage());
    }

    /**
     * Setzt den Kartentyp. Mögliche Parameter sind "Roadmap", "Satellite", "Hybrid" or "Terrain".
     */
    public void setType(String type) 
    {
        map.setType(type);
        setBackground(map.getImage());
    }

    /**
     * Bereitet die Welt für den Programmstart vor. Das heißt: erzeugt die Anfangsobjkte und 
     * fügt diese in die Welt ein.
     */
    private void prepare()
    {
        ZoomInButton zoominbutton = new ZoomInButton();
        addObject(zoominbutton, 50, 60);
        ZoomOutButton zoomoutbutton = new ZoomOutButton();
        addObject(zoomoutbutton, 50, 110);
    }
}
