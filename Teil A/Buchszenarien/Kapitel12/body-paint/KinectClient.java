import greenfoot.*;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Ein Client, der Daten vom Kinect-Server über ein Socket einliest.
 */
public class KinectClient  
{
    private SocketChannel socket; //null, wenn wir nicht verbunden sind
    private String host;
    private HashMap<Integer, UserData> users = new HashMap<Integer, UserData>();
    private final GreenfootImage thumbnail;
    private final GreenfootImage combinedUserImage;
    private short maxDepth = 0;
    private final short[] depthArray; // null, wenn wir es nicht verwenden
    private final int thumbnailWidth;
    private final int thumbnailHeight;
    private final ByteBuffer thumbnailDirectBuffer;
    
    /** Die Breite des Kinect-Bilds, wenn nicht skaliert */
    public static final int DEFAULT_IMAGE_WIDTH = 640;
    /** Die Höhe des Kinect-Bilds, wenn nicht skaliert */
    public static final int DEFAULT_IMAGE_HEIGHT = 480;
    
    /**
     * Erzeugt ein KinectClient, der versucht, sich mit einem Server auf der lokalen Maschine zu verbinden,
     * und der ein Bild in voller Größe (640 by 480) aus der Kinect-Eingabe verwendet und 
     * keine Tiefeninformation erhält.
     * 
     * Du kannst nach der Konstruktion die Methode isConnected() verwenden, um zu prüfen, ob sich der Client
     * mit dem Server verbinden konnte.
     */
    public KinectClient()
    {
        this(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT, false);
    }
    
    /**
     * Erzeugt einen KinectClient, der versucht, sich mit einem Server auf der lokalen Maschine zu verbinden.
     * 
     * Der ersten beiden Parameter legen die Breite und die Höhe des Thumbnails fest. Diese Werte sollten
     * 640, 480 für das Bild in voller Größe oder eine exakte Division sein, wenn du nur ein kleineres Bild
     * benötigst (dies kann sich positiv auf die Leistung auswirken) wie 320, 240 oder 80, 60.
     *
     * Der dritte Parameter gibt an, ob Tiefeninformationen für jedes Pixel verfügbar gemacht werden.
     * Es wird empfohlen, dass du "false" übergibst (da dies Ressourcen schont), außer du weißt,
     * dass du diese haben möchtest.
     * 
     * Du kannst nach der Konstruktion die Methode isConnected() verwenden, um zu prüfen, ob sich der Client
     * mit dem Server verbinden konnte.
     */
    public KinectClient(int thumbnailWidth, int thumbnailHeight, boolean depth)
    {
        this(thumbnailWidth, thumbnailHeight, depth, "127.0.0.1");
    }

    
    /**
     * Erzeugt einen KinectClient, der versucht, sich mit dem angegebenen Server zu verbinden.
     * ("127.0.0.1" als Standard).
     * 
     * Der ersten beiden Parameter legen die Breite und die Höhe des Thumbnails fest. Diese Werte sollten
     * 640, 480 für das Bild in voller Größe oder eine exakte Division sein, wenn du nur ein kleineres Bild
     * benötigst (dies kann sich positiv auf die Leistung auswirken) wie 320, 240 oder 80, 60.
     *
     * Der dritte Parameter gibt an, ob Tiefeninformationen für jedes Pixel verfügbar gemacht werden.
     * Es wird empfohlen, dass du "false" übergibst (da dies Ressourcen schont), außer du weißt,
     * dass du diese haben möchtest.
     * 
     * Du kannst nach der Konstruktion die Methode isConnected() verwenden, um zu prüfen, ob sich der Client
     * mit dem Server verbinden konnte.
     */
    public KinectClient(int thumbnailWidth, int thumbnailHeight, boolean depth, String host)
    {
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
        this.host = host;
        if (thumbnailWidth != 0 && thumbnailHeight != 0)
        {
            this.thumbnail = new GreenfootImage(thumbnailWidth, thumbnailHeight);
            this.combinedUserImage = new GreenfootImage(thumbnailWidth, thumbnailHeight);
            this.thumbnailDirectBuffer = ByteBuffer.allocateDirect(thumbnailWidth * thumbnailHeight * 4);
        }
        else
        {
            this.thumbnail = null;
            this.combinedUserImage = null;
            this.thumbnailDirectBuffer = null;
        }

        if (depth)
        {
            this.depthArray = new short[DEFAULT_IMAGE_HEIGHT * DEFAULT_IMAGE_WIDTH];
        }
        else
        {
            this.depthArray = null;
        }

        
        try
        {
            connect();
        }
        catch (IOException e)
        {
            //System.err.println("Error connecting to server:");
            //e.printStackTrace();
        }
    }
    
    private void connect() throws IOException
    {
        try
        {
            socket = SocketChannel.open();
            socket.socket().setReceiveBufferSize(1048576);
            socket.connect(new InetSocketAddress(host, 0x2020));
            socket.configureBlocking(false);
            sendRequestForMore();
        }
        catch (IOException e)
        {
            socket = null;
            throw e;
        }
    }
    
    /**
     * Verbindung zum Server trennen.
     * 
     * Es ist eine gute Idee, diese Methode aufzurufen, wenn du weißt, dass die diesen KinectClient nicht wieder verwendest.
     */
    public void disconnect()
    {
        if (socket == null)
            return;
        
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
        }
        socket = null;
    }

    // Sendet Anfrage an den Server für mehr Daten.
    // Dieses Senden hält auch den Server davon ab, den Client zu abzuhängen und das Socket zu fluten.
    private void sendRequestForMore() throws IOException
    {
        ByteBuffer buf = ByteBuffer.allocate(12);
        buf.clear();
        // Wir senden 2, wenn wir Tiefendaten wünschen, andernfalls 1:
        buf.putInt(depthArray == null ? (int)1 : (int)2);
        buf.putInt(thumbnailWidth);
        buf.putInt(thumbnailHeight);
        buf.flip();
        socket.write(buf);
    }

    // Liest ein Point3D-Objekt vom Socket und skaliert es entsprechend dem Parameter.
    private static Point3D readPoint3D(ByteBuffer input) throws IOException
    {
        float x = input.getFloat();
        float y = input.getFloat();
        float z = input.getFloat();
        return new Point3D(x, y, z);
    }
    
    // Liefert die angegebene Anzahl an Bytes vom Socket in einen übergegebenen ByteBuffer.
    private ByteBuffer fillBufferFromSocket(int total) throws IOException
    {
        return fillBufferFromSocket(ByteBuffer.allocate(total), total);
    }
    
    private ByteBuffer fillBufferFromSocket(ByteBuffer buf, int total) throws IOException
    {
        buf.clear();
        while (total > 0)
        {
            int n = socket.read(buf);
            if (n == -1) throw new IOException("EOF");
            total -= n;
        }
        buf.flip();
        return buf;
    }
    
    private void bufferToImage(IntBuffer buf, BufferedImage img, int x, int y, int amount)
    {
        try {
            buf.get(((DataBufferInt)img.getRaster().getDataBuffer()).getData(), y*thumbnailWidth + x, amount);
        } catch (Exception e) {
            int[] thumbnailArray = new int[amount];
            buf.get(thumbnailArray);
            //Zuerst die erste Scanzeile:
            if (x != 0) {
                img.setRGB(x, y, thumbnailWidth - x, 1, thumbnailArray, 0, thumbnailWidth);
                amount -= thumbnailWidth - x;
                y += 1;
            }
            //Dann alle vollstänigen Scan-Zeilen in der Mitte:
            if (amount >= thumbnailWidth)
            {
                img.setRGB(0, y, thumbnailWidth, amount / thumbnailWidth, thumbnailArray, thumbnailArray.length - amount, thumbnailWidth);
                y += amount / thumbnailWidth;
                amount = amount % thumbnailWidth;
            }
            //Dann die letzte Scan-Zeile:
            if (amount > 0)
            {
                img.setRGB(0, y, amount, 1, thumbnailArray, thumbnailArray.length - amount, thumbnailWidth);
            }
        }
    }
       
    private static class ImageAndBounds
    {
        public GreenfootImage img;
        public int minX, maxX, minY, maxY;
        
        public ImageAndBounds(int w, int h)
        {
            img = new GreenfootImage(w, h);
            minX = w; maxX = 0; 
            minY = h; maxY = 0;
        }
    }
    
    /**
     * Versucht, die letzte Aktualisierung vom Server zu lesen.
     * 
     * Sollte nur einmal pro Frame aufgerufen werden (typischerweise in der act()-Methode der Welt), da kein Punkt zwei Aktualisierung pro Frame erhält.
     * Wenn wir aktuell nicht verbunden sind, macht diese Methode nichts.
     */
    public void update()
    {
        try {
            if (socket == null)
                return;
            
            Selector sel = Selector.open();
            socket.register(sel, SelectionKey.OP_READ, null);
            // Nur Lesen, wenn Daten verfügbar sind:
            if (sel.selectNow() > 0)
            {
                // Allen vollständigen Nachrichten ist ihre Größe vorangestellt:
                ByteBuffer buf = fillBufferFromSocket(4);
                long startTime = System.currentTimeMillis();
                int msgSize = buf.getInt();
                if (msgSize == 0)
                {
                    throw new IOException("Dein Kinect-Server ist zu alt, es wird mindestens Version 1.1 benötigt");
                }
                buf = fillBufferFromSocket(msgSize);
                
                // Dann die Anzahl der Benutzer:
                int numUsers = buf.getInt();
                Set<Integer> presentUsers = new HashSet<Integer>();
                for (int i = 0; i < numUsers; i++)
                {
                    // Jeder Benutzer hat eine ID, gefolgt vom Zustand
                    int id = buf.getInt();
                    UserData u = users.get(id);
                    if (u == null)
                    {
                        u = new UserData(id);
                        users.put(id, u);
                    }
                    presentUsers.add(id);
                    
                    int state = buf.getInt();
                    u.setState(state);

                    // Wenn sie protokolliert werden, senden wir ihre Skelett-Daten:
                    if (u.isTracking())
                    {
                        for (int j = 0; j < Joint.NUM_JOINTS; j++)
                        {
                            float conf = buf.getFloat();
                            Point3D posWorld = readPoint3D(buf);
                            Point3D posScreen = readPoint3D(buf);
                            u.setJoint(j, new Joint(j, conf, posWorld, posScreen));
                        }
                    }                    
                }
                // Entferne jeden Nutzer, von dem wir keine Daten mehr bekommen:
                Set<Integer> oldUsers = users.keySet();
                oldUsers.retainAll(presentUsers);
                
                if (thumbnailWidth != 0 && thumbnailHeight != 0)
                {
                    
                    // Lies die Laber der Benutzer:
                    buf = fillBufferFromSocket(4);
                    ShortBuffer userLabelBuffer = fillBufferFromSocket(buf.getInt()).asShortBuffer();
                                    
                    // Lies das Thumbnail:
                    IntBuffer thumbnailBuffer = fillBufferFromSocket(thumbnailDirectBuffer, thumbnailWidth * thumbnailHeight * 4).asIntBuffer();
                    bufferToImage(thumbnailBuffer, thumbnail.getAwtImage(), 0, 0, thumbnailWidth * thumbnailHeight);
                    
                    // Erstelle die Benutzerbilder:
                    short curValue = 0;
                    short curCount = 0;
                    HashMap<Integer, ImageAndBounds> userImages = new HashMap<Integer, ImageAndBounds>();
                    for (UserData u : users.values())
                    {
                        userImages.put(u.getId(), new ImageAndBounds(thumbnailWidth, thumbnailHeight));
                    }
                    combinedUserImage.clear();
                    thumbnailBuffer.rewind();
                    int pos = 0;
                    while (pos < thumbnailHeight*thumbnailWidth)
                    {
                        curValue = userLabelBuffer.get();
                        if ((curValue & 0x8000) != 0)
                        {
                            curCount = (short)(curValue & 0x7FFF);
                            curValue = userLabelBuffer.get();
                        }
                        else
                        {
                            curCount = 1;
                        }
                            
                        //Platziere curValue:
                        if (curValue != 0)
                        {
                            final int x = pos % thumbnailWidth;
                            final int y = pos / thumbnailWidth;
                            thumbnailBuffer.position(pos);
                            bufferToImage(thumbnailBuffer, combinedUserImage.getAwtImage(), x, y, curCount);
                            ImageAndBounds iab = userImages.get((int)curValue);
                            thumbnailBuffer.position(pos);
                            if (iab != null)
                            {
                                bufferToImage(thumbnailBuffer, iab.img.getAwtImage(), x, y, curCount);
                                iab.minX = Math.min(iab.minX, x);
                                iab.maxX = Math.max(iab.maxX, x);
                                iab.minY = Math.min(iab.minY, y);
                                iab.maxY = Math.max(iab.maxY, y);
                            }
                        }
                        pos += curCount;
                    }
                    for (UserData u : users.values())
                    {
                        ImageAndBounds iab = userImages.get(u.getId());
                        if (iab.maxX >= iab.minX)
                        {
                            GreenfootImage img = new GreenfootImage(iab.maxX - iab.minX + 1, iab.maxY - iab.minY + 1);
                            img.drawImage(iab.img, -iab.minX, -iab.minY);
        
                            u.setImage(img, iab.minX, iab.minY);
                        }
                        else u.setImage(null, 0, 0);
                    }
                }

                if (depthArray != null)
                {
                    ShortBuffer depthBuffer = fillBufferFromSocket((1 + DEFAULT_IMAGE_WIDTH * DEFAULT_IMAGE_HEIGHT) * 2).asShortBuffer();
                    maxDepth = depthBuffer.get();
                    depthBuffer.get(depthArray);
                }
                
                // Und frage nach mehr Daten, die für den nächsten Rahmen bereit sind;
                // wir sollten dies einmal pro erhaltenem Rahmen senden, dient als Bestätigung:
                sendRequestForMore();
            }
            sel.close();
        }
        catch (IOException e)
        {
            socket = null;
            //e.printStackTrace();
        }
    }
    
    /**
     * Gibt an, ob der Client aktuell mit dem Server verbunden ist.
     * 
     * Falls "true" zurückgegeben wird, ist das keine Garantie dafür, das er verbunden bleibt
     * (zum Beispiel könnte das Socket fallengelassen werden und wir haben es noch nicht bemerkt),
     * aber falls "false" zurückgegeben wird, dann ist sicher, dass wir nicht verbunden sind.
     */
    public boolean isConnected()
    {
        return socket != null;
    }
    
    /**
     * Liefert die Nutzerdaten von allen Benutzern, die vom Sensor erkannt werden
     * (unabhängig davon, ob ihre Skeletts aktuell aufgezeichnet werden).
     * 
     * Gibt niemals null zurück, aber eventuell ein leeres Feld.
     */
    public UserData[] getUsers()
    {
        return users.values().toArray(new UserData[0]);
    }
    
    /**
     * Liefert die Nutzerdaten für eine bestimmte Nutzer-ID.
     * 
     * Gibt null zurück, wenn es für diesen Nutzer keine Daten gibt.
     */
    public UserData getUser(int userId)
    {
        return users.get(userId);
    }

    /**
     * Liefert ein Bild davon, was der Sensor aktuell sieht.
     * Dieses Thumbnail hat eine fixe Größe, festgelegt durch die Parameter,
     * die dem KinectClient-Konstruktor übergeben werden (640 * 480, falls nicht angegeben).
     * 
     * Diese Methode kann null zurückgeben, wenn der Client nicht verbunden ist oder wenn die Aktualisierung 
     * seit Verbindungsbeginn keinen Rahmen oder keine Daten erfolgreich gelesen hat.
     */
    public GreenfootImage getThumbnail()
    {
        if (thumbnail == null)
            return null;
        else
        {
            return new GreenfootImage(thumbnail);
        }
    }
    
    /**
     * Liefert ein Bild aller Nutzer, die der Sensor aktuell sieht.
     * 
     * Dieses Bild ist im Wesentlichen das Bild von getThumbnail() (hat auch dieselbe Größe), in dem aber alle Pixel,
     * die aktuell nicht von Benutzern besetzt sind, transparent sind.
     * 
     * Diese Methode kann null zurückgeben, wenn der Client nicht verbunden ist oder wenn die Aktualisierung 
     * seit Verbindungsbeginn keinen Rahmen oder keine Daten erfolgreich gelesen hat.
     */
    public GreenfootImage getCombinedUserImage()
    {
        if (thumbnail == null)
            return null;
        else
            return new GreenfootImage(combinedUserImage);
    }

    /**
     * Liefert das Tiefenfeld (oder null, falls es das nicht gibt).
     * Die Inhalte werden jede Aktualisierung verändern, daher solltest du eine Kopie anlegen, 
     * wenn du es in mehreren Rahmen verwenden möchtest, und du solltest seine Inhalte nicht verändern.
     * Die Zahlen sollten im Bereich von 0 bis einschließlich getMaxDepth() liegen.  
     */
    public short[] getRawDepth()
    {
        return depthArray;
    }

    /**
     * Liefert den maximalen Tiefenwert (falls Tiefe eingestellt ist, 0, wenn es ein Problem gibt).
     * Du solltest dich darauf verlassen können, dass diese Zahl ab der ersten Aktualisierung stabil ist. 
     */
    public short getMaxDepth()
    {
        return maxDepth;
    }
}
