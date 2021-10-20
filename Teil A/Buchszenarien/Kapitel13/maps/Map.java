import greenfoot.*;
import javax.imageio.ImageIO;

/**
 * Eine Helper-Klasse, die eine googleMap für einen bestimmten Ort als Bild holt.
 * 
 * <pre>
 * public class MapActor extends Actor
 * {
 *     Map map;
 *     public MapActor() 
 *     {
 *         map = new Map("Brazil");
 *         setImage(map.getImage());
 *     }
 *     
 *     public void act() 
 *     {
 *         map.zoomIn(1);
 *         setImage(map.getImage());
 *     }
 *     
 *     public void setType(String type) 
 *     {
 *         map.setType(type);
 *         setImage(map.getImage());
 *     }
 * }
 * </pre>
 * 
 * @author amjad
 * @version 2.0
 */
public class Map {

    private final String urlBase = "http://maps.googleapis.com/maps/api/staticmap?sensor=false";
    
    private int zoom;
    private int width;
    private int height;
    private String location;

    private GreenfootImage image;
    
    private enum MapType { ROADMAP, SATELLITE, HYBRID, TERRAIN;}
    private MapType type = MapType.ROADMAP;
    
    /**
     * Konstruiert ein Map_objket, das eine Karte eines angegebenen Orts aufbaut,
     * z.B."-15.800513, -47.91378" oder "Brooklyn Bridge, New York, NY".
     * Die Felder für die Breite und Höhe werden standardmäßig mit 600 x 400 gesetzt.
     * Das Zoom-Feld erhält den Standardwert 5.
     * 
     *  @param location  der Ort dient als Mittelpunkt der Karte.
     */
    public Map(String location)
    {
        this(location, 600, 400, 5);
    }
    
    /**
     * Konstruiert ein Map_objket, das eine Karte eines angegebenen Orts aufbaut,
     * z.B."-15.800513, -47.91378" oder "Brooklyn Bridge, New York, NY".
     * 
     *  @param location  'location' ist der Mittelpunkt der Karte.
     *  @param width     Breite des Bilds.
     *  @param height    Höhe des Bilds.
     *  @param zoom      Zoomfaktor der Karte [0-20].
     */
    public Map(String location, int width, int height, int zoom)
    {
        this.location = location;
        this.width = width;
        this.height = height;
        setZoom(zoom);
    }
    
    /**
     * Setzt den Zoomfaktor, stellt dabei sicher, dass er aus dem Bereich [0-20] stammt.
     * 
     * @param value  der Zoomfaktor.
     */
    public void setZoom(int value)
    {
        zoom = value;
        if (zoom > 20) {
            zoom = 20;
        }
        if (zoom < 0) {
            zoom = 0;
        }
        buildImage();
    }
    
    /**
     * Bereitet die URL der Karte vor und holt ein Bild dieser Karte.
     */
    private void buildImage()
    {
        String urlAddress = urlBase;
        urlAddress += "&center=" + location.replace(" ", "+");
        urlAddress += "&size=" + width + "x" + height;
        urlAddress += "&zoom=" + zoom;
        urlAddress += "&maptype=" + type.toString().toLowerCase();
        
        image = new GreenfootImage(urlAddress);
    }
    
    /**
     * Setzt den Ort.
     * 
     * @param location  der neue Ort.
     */
    public void setLocation(String location)
    {
        this.location = location;
        buildImage();
    }
    
    /**
     * Liefert die Karte als GreenfootImage.
     * 
     * @return GreenfootImage der Karte. 
     */
    public GreenfootImage getImage()
    {
        return image;
    }
    
    /**
     * Setzt die Kartenart: "Roadmap", "Satellite", "Hybrid" oder "Terrain".
     * 
     * @param type  die Kartenart.
     * @exception  falls der übergebene Parameter keiner der vordefinierten Typen ist.
     */
    public void setType(String type)
    {
        try {
            this.type = MapType.valueOf(type.toUpperCase());
        }
        catch(IllegalArgumentException ex) {
            System.err.println(type + " ist keine gültige Kartenart. Bitte verwende: Roadmap, Satellite, Hybrid or Terrain");
        }
        buildImage();
    }
    
    /**
     * Erhöht den Zoomfaktor.
     * 
     * @param value  die Veränderung des Zoomfaktors.
     */
    public void zoomIn(int value)
    {
        setZoom(zoom + value);
    }
    
    /**
     * Erniedrigt den Zoomfaktor.
     * 
     * @param value  die Veränderung des Zoomfaktors.
     */
    public void zoomOut(int value)
    {
        setZoom(zoom - value);
    }
}
