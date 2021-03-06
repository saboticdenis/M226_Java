import greenfoot.*;



/**
 * Das ScoreBoard wird verwendet, um die Ergebnisse auf dem Bildschirm anzuzeigen. 
 * Es kann etwas Text und einige Zahlen anzeigen.
 * 
 * @author M Kölling
 * @version 1.0
 */
public class ScoreBoard extends Actor
{
    public static final float FONT_SIZE = 48.0f;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    
    /**
     * Erzeugt zum Testen eine Anzeigetafel mit Platzhalterwerten.
     */
    public ScoreBoard()
    {
        this(100);
    }

    /**
     * Erzeugt eine Anzeigetafel für das endgültige Ergebnis.
     */
    public ScoreBoard(int score)
    {
        makeImage("Game Over", "Score: ", score);
    }

    /**
     * Erzeugt das Bild der Anzeigetafel.
     */
    private void makeImage(String title, String prefix, int score)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(new Color(255,255,255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, WIDTH-10, HEIGHT-10);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(title, 60, 100);
        image.drawString(prefix + score, 60, 200);
        setImage(image);
    }
}
