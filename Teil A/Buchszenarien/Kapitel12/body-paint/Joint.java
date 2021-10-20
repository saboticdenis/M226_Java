import java.util.Comparator;

/**
 * Daten für einen Joint-Punkt im Skelett eines protokollierten Benutzers.
 */
public class Joint
{
    /**
     * Gesamtzahl der Joint-Punkte, die vom Sensor erfasst werden.
     */
    public static final int NUM_JOINTS = 15;

    /**
     * Der Joint-Punkt des Kopfs (Zentrum vom Kopf).
     */
    public static final int HEAD = 0;
    /**
     * Der Joint-Punkt des Halses (dies ist festgelegt als Mittelpunkt zwischen den beiden Schultern).
     */
    public static final int NECK = 1;
    /**
     * Der Joint-Punkt des Rumpfes (dies ist festgelegt als Mittelpunkt zwischen den beiden Schultern und den Hüften).
     */
    public static final int TORSO = 2;
    
    /**
     * Der Abstand für LEFT-Joint-Punkte. Du kannst diese und die Konstanten RIGHT,
     * SHOULDER, ELBOW, HAND, HIP, KNEE und FOOT verwenden, um beide Seiten innerhalb eines Codes zu bearbeiten. 
     * So haben wir beispielsweise das Strichmännchen gezeichnet:
     * 
     * for (int side : new int[] {Joint.LEFT, Joint.RIGHT})
       {
            connect(img, Joint.NECK, side + Joint.SHOULDER);
            connect(img, side + Joint.SHOULDER, side + Joint.ELBOW);
            connect(img, side + Joint.ELBOW, side + Joint.HAND);
            
            connect(img, side + Joint.HIP, side + Joint.KNEE);
            connect(img, side + Joint.KNEE, side + Joint.FOOT);
       }
     * 
     * Wenn Du dies unsinnig findest oder nicht benötigst, kannst du auch die Konstanten
     * LEFT_HAND, RIGHT_KNEE etc. verwenden.
     */
    public static final int LEFT = 0;
    public static final int RIGHT = 3;
    public static final int SHOULDER = 3;
    public static final int ELBOW = 4;
    public static final int HAND = 5;
    public static final int HIP = 9;
    public static final int KNEE = 10;
    public static final int FOOT = 11;

    /**
     * Die linke Schulter. Beachte, dass dies nicht deine echte linke Schulter ist, sondern weil die Anzeige
     * in Kinect-Szenarios immer ein Spiegelbild von dir ist, wird dies auf der rechten Seite des Bildschirms erscheinen. 
     * Das gilt auch für alle anderen Joint-Punkte.
     */
    public static final int LEFT_SHOULDER = LEFT+SHOULDER;
    public static final int LEFT_ELBOW = LEFT+ELBOW;
    public static final int LEFT_HAND = LEFT+HAND;
    public static final int LEFT_HIP = LEFT+HIP;
    public static final int LEFT_KNEE = LEFT+KNEE;
    public static final int LEFT_FOOT = LEFT+FOOT;

    public static final int RIGHT_SHOULDER = RIGHT+SHOULDER;
    public static final int RIGHT_ELBOW = RIGHT+ELBOW;
    public static final int RIGHT_HAND = RIGHT+HAND;
    public static final int RIGHT_HIP = RIGHT+HIP;
    public static final int RIGHT_KNEE = RIGHT+KNEE;
    public static final int RIGHT_FOOT = RIGHT+FOOT;
    
    
    private final int joint;
    private final float confidence;
    private final Point3D positionWorld;
    private final Point3D positionScreen;
    
    /**
     * Nur für den internen Gebrauch.
     */
    public Joint(int joint, float confidence, Point3D positionWorld, Point3D positionScreen)
    {
        this.joint = joint;
        this.confidence = confidence;
        this.positionWorld = positionWorld;
        this.positionScreen = positionScreen;
    }
    
    /**
     * Liefert den Joint-Index für diesen Joint-Punkt (z.B. Joint.HEAD or JOINT.LEFT_HAND)
     */
    public int getJointIndex()
    {
        return joint;
    }
    
    /**
     * Liefert den Vertrauenswert zu diesem Joint-Punkt (eine Zahl zwischen 0 und 1, wobei 0 am wenigstens vertrauenswürdig und 1 am vertrauenswürdigsten bedeutet).
     * 
     * Der Kinect-Sensor gibt sein Vetrauen in die Position eines Joint-Punkts an. Wenn du hinter einen Tisch gehst,
     * wird der Vertrauenswert deiner nun nicht sichtbaren Knie und Füße entsprechend sinken.
     * 
     * Im Moment ignorieren alle Methoden (z.B. minJointBy) diesen Wert, falls du ihn also benutzen möchtest, 
     * musst du den Code dafür selbst schreiben. Es sieht so aus, als würde der Wert zur Zeit auch nicht von 
     * von OpenNI korrekt unterstützt. 
     */
    public float getConfidence()
    {
        return confidence;
    }
    
    /**
     * Liefert die x-Koordinate dieses Joint-Punkts auf dem Bildschirm.
     */
    public int getX()
    {
        return Math.round(getScreenPosition().getX());
    }
    
    /**
     * Liefert die y-Koordinate dieses Joint-Punkts auf dem Bildschirm.
     */
    public int getY()
    {
        return Math.round(getScreenPosition().getY());
    }
    
    /**
     * Liefert die Position dieses Joint-Punkts auf dem Bildschirm als 3-D-Punkt.
     * 
     * x- und y-Koordinate sind wie üblich, die z-Koordinate ist eine Tiefenkoordinate, mit der du messen kannst, 
     * wie nah der Joint-Punkt dem Sensor ist (niedrige Werte sind näher am Sensor).
     */
    public Point3D getScreenPosition()
    {
        return positionScreen;
    }

    /**
     * Liefert die physische Position des Joint-Punkts in der Welt als 3-D-Punkt.
     */    
    public Point3D getPhysicalPosition()
    {
        return positionWorld;
    }

    public String toString()
    {
        return positionScreen.toString();
    }

    /**
     * Liefert den kleinsten Joint-Punkt bezüglich des angegebenen Vergleichswerts. Du kannst 
     * diesen in den Methoden compareX(), compareY() und compareZ() verwenden, um die extremsten 
     * Joint-Punkte in einer bestimmten Dimension zu finden.
     * 
     * Zum Beispiel liefert minJointBy(userData.getAllJoints(), compareX()) den am weitesten links befindlichen Joint-Punkt
     * (wie er auf dem Bildschirm erscheint - wahrscheinlich deine rechte Hand, wenn du mit dem Gesicht dem Sensor zugewandt bist).
     * minJointBy(userData.getAllJoints(), compareY()) liefert den höchsten Joint-Punkt
     * (y-Koordinaten werden größer, wenn du nach unten gehst, wie auf dem Bildschirm, aber entgegengesetzt zur mathematischen Konvention).
     * minJointBy(userData.getAllJoints(), compareZ()) liefert den Joint-Punkt, der dem Sensor am nächsten ist..
     * 
     * UserData.getNearestJoint() und UserData.getHighestJoint() sind nützliche Kurzformen für die 
     * Verwendung dieser Methoden für z bzw. y.
     * 
     * Beachte, dass diese Methode den Vertrauenswert, der mit jedem Joint-Punkt verbunden ist, völlig ignoriert. 
     */
    public static int minJointBy(Joint[] joints, Comparator<Joint> cmp)
    {
        Joint[] jointCopy = new Joint[joints.length];
        System.arraycopy(joints, 0, jointCopy, 0, joints.length);
        java.util.Arrays.sort(jointCopy, cmp);
        return jointCopy[0].joint;
    }
    
    /**
     * Liefert den größten Joint-Punkt bezüglich des angegebenen Vergleichswerts. Du kannst 
     * diesen in den Methoden compareX(), compareY() und compareZ() verwenden, um die extremsten 
     * Joint-Punkte in einer bestimmten Dimension zu finden.
     * 
     * Zum Beispiel liefert maxJointBy(userData.getAllJoints(), compareX()) den am weitesten rechts befindlichen Joint-Punkt
     * (wie er auf dem Bildschirm erscheint - wahrscheinlich deine linke Hand, wenn du mit dem Gesicht dem Sensor zugewandt bist).
     * maxJointBy(userData.getAllJoints(), compareY()) liefert den niedrigsten Joint-Punkt
     * (y-Koordinaten werden größer, wenn du nach unten gehst, wie auf dem Bildschirm, aber entgegengesetzt zur mathematischen Konvention).
     * maxJointBy(userData.getAllJoints(), compareZ()) liefert den Joint-Punkt, der vom Sensor am weitesten entfernt ist.

     * 
     * Beachte, dass diese Methode den Vertrauenswert, der mit jedem Joint-Punkt verbunden ist, völlig ignoriert. 
     */
    public static int maxJointBy(Joint[] joints, Comparator<Joint> cmp)
    {
        Joint[] jointCopy = new Joint[joints.length];
        System.arraycopy(joints, 0, jointCopy, 0, joints.length);
        java.util.Arrays.sort(jointCopy, cmp);
        return jointCopy[jointCopy.length - 1].joint;
    }
    
    public static Comparator<Joint> compareX()
    {
        return new XComparator();
    }
    
    public static Comparator<Joint> compareY()
    {
        return new YComparator();
    }
    
    public static Comparator<Joint> compareZ()
    {
        return new ZComparator();
    }
    
    private static class XComparator implements Comparator<Joint>
    {
        public int compare(Joint a, Joint b)
        {
            Joint aj = (Joint)a;
            Joint bj = (Joint)b;
            return Float.compare (aj.positionScreen.getX(), bj.positionScreen.getX());
        }
    }
    
    private static class YComparator implements Comparator<Joint>
    {
        public int compare(Joint a, Joint b)
        {
            Joint aj = (Joint)a;
            Joint bj = (Joint)b;
            return Float.compare (aj.positionScreen.getY(), bj.positionScreen.getY());
        }
    }
    
    private static class ZComparator implements Comparator<Joint>
    {
        public int compare(Joint a, Joint b)
        {
            Joint aj = (Joint)a;
            Joint bj = (Joint)b;
            return Float.compare (aj.positionScreen.getZ(), bj.positionScreen.getZ());
        }
    }

    /**
     * Liefert eine skalierte Kopie des Joint-Punkts, in der alle Bildschirmpositionen um den angegebenen Faktor skaliert sind.
     */
    public Joint scaledCopy(float scale)
    {
        return new Joint(joint, confidence, positionWorld, positionScreen.scaledCopy(scale));
    }
}
