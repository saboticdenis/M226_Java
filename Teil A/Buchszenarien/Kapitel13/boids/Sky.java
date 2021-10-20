import greenfoot.*; 
import java.awt.Color;

/**
 * Eine Welt für die Boids.
 * 
 * @author Poul Henriksen 
 * @version 2.0
 */
public class Sky extends World
{

    /**
     * Konstruktor für Objekte der Klasse Sky. 
     */
    public Sky()
    {    
        super(800, 600, 1);
        getBackground().setColor(new Color(220,220,100));
        
        getBackground().fill();
        populateTrees(100);
        populateBoids(50);
    }
    
    public void populateBoids(int number) {
        for(int i=0; i < number; i++) {            
             int x = (int) (Math.random() * getWidth());          
             int y = (int) (Math.random() * getHeight());
             Boid b = new Boid();
             addObject(b, x, y);
        }
    }
    
    public void populateTrees(int number) {
        // Größe des Blocks in Pixel
        int blockSize = 70; 
        // Bäume pro Block
        int treesPrBlock = 10;
        // Wie eng die Bäume in einem Block stehen. Je höher die Zahl, desto enger stehen sie.
        int blockCoherence = 1;
        for(int block = 0; block < number / treesPrBlock; block++) {           
             int x = Greenfoot.getRandomNumber(getWidth()/blockSize) * blockSize;   
             int y = Greenfoot.getRandomNumber(getHeight()/blockSize) * blockSize;  
             for(int t = 0; t < treesPrBlock; t++) {
                 int dx = Greenfoot.getRandomNumber(blockSize/blockCoherence);
                 int dy = Greenfoot.getRandomNumber(blockSize/blockCoherence);
                 Tree b = new Tree();
                 addObject(b, x  + dx, y + dy);                
             }    
        }        
    }
    
    
}
