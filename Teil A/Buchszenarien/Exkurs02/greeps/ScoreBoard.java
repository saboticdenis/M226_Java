import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

/**
 * Die Klasse ScoreBoard zeigt die Ergebnisse auf dem Bildschirm an. Sie kann ein wenig Text
 * und mehrere Zahlen anzeigen.
 * 
 * @author M Kölling
 * @version 1.0.1
 */
public class ScoreBoard extends Actor
{
    public static final float FONT_SIZE = 48.0f;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    
    public ScoreBoard()
    {
        makeImage("Punktestände", "Punkte: ", "niemand", 100);
    }

    /**
     * Erzeugt eine Punktestandsanzeige für ein Zwischenergebnis.
     */
    public ScoreBoard(String title, String text, String prefix, int map, int[] scores)
    {
        makeImage(title, text, prefix, scores[map]);
        addMapScores(map, scores);
    }
    
    /**
     * Erzeugt eine Punktestandsanzeige für das endgültige Ergebnis.
     */
    public ScoreBoard(String title, String text, String prefix, int[] scores)
    {
        int total = 0;
        for (int val : scores) {
            total += val;
        }
        makeImage(title, text, prefix, total);
        addMapScores(scores.length-1, scores);
        printResultToTerminal(title, scores);
    }
    
    /**
     * Erstellt das Bild für die Punktestandsanzeige.
     */
    private void makeImage(String title, String text, String prefix, int score)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, WIDTH-10, HEIGHT-10);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(title, 60, 100);
        image.drawString(text, 60, 220);
        image.drawString(prefix + score, 60, 320);
        setImage(image);
    }
    
    /**
     * Trage die Punktestände für die einzelnen Karten in die Punktestandsanzeige ein.
     * 'scores' ist ein Feld, in dem alle Punktestände gespeichert sind,
     * 'mapNo' ist die Nummer der aktuellen Karte (Feldeinträge jenseits dieses
     * Wertes sind nicht gültig).
     */
    private void addMapScores(int mapNo, int[] scores)
    {
        GreenfootImage image = getImage();
        Font font = image.getFont();
        font = font.deriveFont(20.0f);
        image.setFont(font);
        image.setColor(Color.WHITE);
        for (int i = 0; i <= mapNo; i++) {
            image.drawString("Karte " + (i+1) + ": " + scores[i], 460, 80+(i*28));
        }
    }
    
    private void printResultToTerminal(String name, int[] scores)
    {
        Calendar now = Calendar.getInstance();
        String time = now.get(Calendar.HOUR_OF_DAY) + ":";
        int min = now.get(Calendar.MINUTE);
        if(min < 10)
            time += "0" + min;
        else
            time += min;
        System.out.print(time + ":  [");
        int total = 0;
        for (int score : scores) {
            total += score;
            if (score < 10)
                System.out.print("  " + score);
            else
                System.out.print(" " + score);
        }
        System.out.println("]  " + total + "  -- " + name );
    }
}
