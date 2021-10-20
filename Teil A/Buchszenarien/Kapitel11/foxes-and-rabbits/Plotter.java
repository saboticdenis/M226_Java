import greenfoot.*;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.List;

/**
 * Plotter ist ein Akteur, der den Graphen einer Population aus zwei Greenfoot-Actor-Klassen zeichnet.
 * Üblicherweise wird nur ein einzelner Akteur aus dieser Klasse erzeugt.
 * 
 * @author M Kölling
 * @version 1.0
 */
public class Plotter extends Actor
{
    private static final Color LIGHT_GRAY = new Color(0, 0, 0, 40);

    private static JFrame frame;
    private static GraphPanel graph;
    private static JLabel stepLabel;
    private static JLabel count1Label;
    private static JLabel count2Label;
    
    private World world;
    private int step;
    private Class class1;
    private Class class2;
    
    /**
     * Konstruktor.
     * 
     * @param width die Breite des Plotter-Fensters (in Pixeln).
     * @param height die Breite des Plotter-Fensters (in Pixeln).
     * @param startMax der anfängliche Maximalwert für die y-Achse.
     * @param world das Weltobjekt.
     * @param class1 die erste zu zeichnende Klasse.
     * @param width die zweite zu zeichnende Klasse.
     */
    public Plotter(int width, int height, int startMax, World world, Class class1, Class class2)
    {
        this.world = world;
        this.class1 = class1;
        this.class2 = class2;
        if (frame == null) {
            frame = makeFrame(width, height, startMax);
        }
        else {
            graph.newRun();
        }
        step = 0;
        updateGraph();
        setImage(new GreenfootImage(1,1));
    }
    
    /**
     * Aktualisiert den Plotter-Graphen.
     */
    public void act() 
    {
        step++;
        updateGraph();
    }
    
    /**
     * Aktualisiert den Graphen in unserem Fenster, berücksichtigt die beiden überwachten Klassen.
     * Beendet die Simulation, wenn eine auf null abfällt.
     */
    private void updateGraph()
    {
        List class1Objects = world.getObjects(class1);
        List class2Objects = world.getObjects(class2);
        graph.update(step, class1Objects.size(), class2Objects.size());
        if (class1Objects.size()==0 || class2Objects.size()==0) {
            Greenfoot.stop();
        }
    }
    
    /**
     * Bereitet den Rahmen für den Graphen vor.
     */
    private JFrame makeFrame(int width, int height, int startMax)
    {
        JFrame frame = new JFrame("Greenfoot actor graph");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Container contentPane = frame.getContentPane();

        graph = new GraphPanel(width, height, startMax);
        contentPane.add(graph, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
            bottom.add(new JLabel(" Schritt:"));
            stepLabel = new JLabel("");
            bottom.add(stepLabel);
            bottom.add(new JLabel("  " + class1.getName() + ":"));
            count1Label = new JLabel("");
            bottom.add(count1Label);
            bottom.add(new JLabel("  " + class2.getName() + ":"));
            count2Label = new JLabel("");
            bottom.add(count2Label);
        contentPane.add(bottom, BorderLayout.SOUTH);
        
        frame.pack();
        frame.setVisible(true);
        
        return frame;
    }
    
    // ============================================================================
    /**
     * Eingebettete Klasse: eine Komponente zum Zeichnen des Graphen.
     */
    class GraphPanel extends JComponent
    {
        private static final double SCALE_FACTOR = 0.8;
        
        // Ein interner Bilderpuffer, der zum Zeichnen verwendet wird. Um das fertige Bild auf dem
        // Bildschirm anzuzeigen, wird dieser Bildpuffer auf den Bildschirm kopiert.
        private BufferedImage graphImage;
        private int lastVal1, lastVal2;
        private int yMax;
        
        /**
         * Erzeugt ein neues, leeres GraphPanel.
         */
        public GraphPanel(int width, int height, int startMax)
        {
            graphImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            clearImage();
            lastVal1 = height;
            lastVal2 = height;
            yMax = startMax;
        }

        /**
         * Zeigt an, dass eine neue Simulation gestartet wurde.
         */
        public void newRun()
        {
            int height = graphImage.getHeight();
            int width = graphImage.getWidth();
            
            Graphics g = graphImage.getGraphics();
            g.copyArea(4, 0, width-4, height, -4, 0);            
            g.setColor(Color.BLACK);
            g.drawLine(width-4, 0, width-4, height);
            g.drawLine(width-2, 0, width-2, height);
            lastVal1 = height;
            lastVal2 = height;
            repaint();
        }
        
        /**
         * Zeichnet einen neuen Datenpunkt.
         */
        public void update(int step, int count1, int count2)
        {
            Graphics g = graphImage.getGraphics();

            int height = graphImage.getHeight();
            int width = graphImage.getWidth();
            
            g.copyArea(1, 0, width - 1, height, -1, 0);
            
            int y = height - ((height * count1) / yMax) - 1;
            if (y<0) {
                scaleDown();
                y = height - ((height * count1) / yMax) - 1;
            }
            g.setColor(LIGHT_GRAY);
            g.drawLine(width-2, y, width-2, height);
            g.setColor(Color.RED);
            g.drawLine(width-3, lastVal1, width-2, y);
            lastVal1 = y;
            
            y = height - ((height * count2) / yMax) - 1;
            if (y<0) {
                scaleDown();
                y = height - ((height * count1) / yMax) - 1;
            }
            g.setColor(LIGHT_GRAY);
            g.drawLine(width-2, y, width-2, height);
            g.setColor(Color.BLACK);
            g.drawLine(width-3, lastVal2, width-2, y);
            lastVal2 = y;
            
            repaint();
            
            stepLabel.setText("" + step);
            count1Label.setText("" + count1);
            count2Label.setText("" + count2);
        }
        
        /**
         * Skaliert den aktuellen Graphen vertikal herunter, um oben Platz zu schaffen.
         */
        public void scaleDown()
        {
            Graphics g = graphImage.getGraphics();
            int height = graphImage.getHeight();
            int width = graphImage.getWidth();

            BufferedImage tmpImage = new BufferedImage(width, (int)(height*SCALE_FACTOR), BufferedImage.TYPE_INT_RGB);
            Graphics2D gtmp = (Graphics2D) tmpImage.getGraphics();
            
            gtmp.scale(1.0, SCALE_FACTOR);
            gtmp.drawImage(graphImage, 0, 0, null);
            
            int oldTop = (int) (height * (1.0-SCALE_FACTOR));
            
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, oldTop);
            g.drawImage(tmpImage, 0, oldTop, null);
            
            yMax = (int) (yMax / SCALE_FACTOR);
            lastVal1 = oldTop + (int) (lastVal1 * SCALE_FACTOR);
            lastVal2 = oldTop + (int) (lastVal2 * SCALE_FACTOR);
            
            repaint();
        }
        
        /**
         * Löscht die Darstellung.
         */
        public void clearImage()
        {
            Graphics g = graphImage.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, graphImage.getWidth(), graphImage.getHeight());
            repaint();
        }
        
        // Die folgenden Methoden sind Neudefinitionen von Methoden, die von
        // den Oberklassen geerbt wurden.
        
        /**
         * Teilt dem Layout-Manager mit, wie groß wir werden möchten.
         * (Diese Methode wird von Layout-Managern beim Platzieren der Komponenten benutzt.)
         * 
         * @return die bevorzugten Abmaße für diese Komponente.
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(graphImage.getWidth(), graphImage.getHeight());
        }
        
        /**
         * Diese Komponente ist undurchsichtig.
         */
        public boolean isOpaque()
        {
            return true;
        }
        
        /**
         * Diese Komponente muss neu gezeichnet werden. Kopiere das interne Bild auf den 
         * Bildschirm. (Diese Methode wird vom Swing-Bildschirm-Painter jedes Mal aufgerufen,
         * wenn dieser möchte, dass die Komponente gezeichnet wird.)
         * 
         * @param g der Grafikkontext, der zum Zeichnen in diese Komponente verwendet werden kann.
         */
        public void paintComponent(Graphics g)
        {
            Dimension size = getSize();

            if(graphImage != null) {
                g.drawImage(graphImage, 0, 0, null);
            }
        }
    }
}
