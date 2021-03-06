import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int xWind = 0;
    private int yWind = 0; 
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 500, 1);
    }
    
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        xWind = 0;
        yWind = 0;
       
        // Add your action code here.
        if ( Greenfoot.isKeyDown("right")) 
        {
            xWind = 3;
        }
        if ( Greenfoot.isKeyDown("left")) 
        {
            xWind = -3;
        } 
        if ( Greenfoot.isKeyDown("up")) 
        {
            yWind = -3;
        }
        if ( Greenfoot.isKeyDown("down")) 
        {
            yWind = 3;
        } 
    } 
    
     /**
     * Getter von xWind
     */
    public int getxWind(){
      return xWind;
    }
     /**
     *Getter von yWind.
     */
    public int getyWind(){
      return yWind;
    }
    
    /**
     * Bereite die Welt f?r den Programmstart mit Bouncing Balls vor.
     */
    public void setupBoxBalls()
    {
        int i = 5;
        BoxBall bxball;
        while (i > 0) { 
            bxball = new BoxBall();
            addObject(bxball,Greenfoot.getRandomNumber(400)+50,Greenfoot.getRandomNumber(400)+50);
            i -= 1;
        }
    }
    
    /**
     * Bereite die Welt f?r den Programmstart mit Box Balls vor.
     */
    public void setupBouncingBalls()
    {
        int i = 5;
        BouncingBall boball;
        while (i > 0) { 
            boball = new BouncingBall();
            addObject(boball,Greenfoot.getRandomNumber(20)+20,Greenfoot.getRandomNumber(200)+50);
            i -= 1;
        }
    }

    /**
     * Bereite die Welt f?r den Programmstart mit Box Balls vor.
     */
    public void setupHitBalls()
    {
        int i = 5;
        HitBall hball;
        while (i > 0) { 
            hball = new HitBall();
            addObject(hball,Greenfoot.getRandomNumber(60)+i*80,Greenfoot.getRandomNumber(60)+i*80);
            i -= 1;
        }
    }
}
