import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Honcho here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Honcho extends PhysShooter
{
    
    int dir = 1;
    boolean flipped = false;
    int frame = 0;
    GreenfootSound fly = new GreenfootSound("fly.wav");
    
    /**
     * Act - do whatever the Honcho wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        frame += 1;
        int temp = getY();
        if(dir == 1 && flipped == true) {
            getImage().mirrorHorizontally();
            flipped = false;
        }
        else if(dir == -1 && flipped == false) {
            getImage().mirrorHorizontally();
            flipped = true;
        }
        
        List<Player> players = getObjectsInRange(getWorld().getHeight(), Player.class);
        if(players != null && players.size() > 0 && frame%40 == 0 &&
           Greenfoot.getRandomNumber(100) > 65 && frame % 20 == 0) {
               shoot(players.get(0).getX(), players.get(0).getY(), new Laser(0, 0));
        }
        
        if(frame % 2 == 1) {
            if (dir == 1) {
                moveRight();
            }
            else {
                moveLeft();
            }
        }
        
        if(frame % 15 == 0) {
            fly.setVolume(60);
           fly.play(); 
        }
        
        if(frame%60 == 0){
            sunburst();
        }

        if(getX() < 20) {
            dir = 1;
        }
        if(getX() > getWorld().getWidth() - 20) {
            dir = -1;
        }
        super.act();
        a = 0;
    }
    
    public void sunburst(){
        int baseAng = Greenfoot.getRandomNumber(359);
        int num = 10;
        
        for(int i=0; i<num; i++){
            getWorld().addObject(new sunBullet(1, 10, 0.04, baseAng + 360*i/num, new GreenfootImage("images/bullet1.png"), this), getX(), getY());
        }
    }
    
    public void die() {
        GameWorld.nextWorld();
    }
    
    public void hit() {
        super.hit();
        GreenfootSound sound = new GreenfootSound("honchoLol.wav");
                  sound.play(); 
    }
    
}