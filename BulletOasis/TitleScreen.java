import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends GameWorld
{
    public TitleScreen() {
        super(600, 400, 1, true);
        prepare();
    }
    
    public void act() {
        super.act();
        if(bg == null || bg.isPlaying() == false) {
            bg = new GreenfootSound("Drankin_Song_titleScreen.mp3");
            bg.playLoop();
        }
        if(Greenfoot.mouseClicked(null)){
            GameWorld.restart();
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
