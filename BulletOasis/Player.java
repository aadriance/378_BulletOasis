import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends PhysShooter
{
    Actor subActor;
    String[] walkCycle = {"player-stand.png", "player-walk2.png", "player-walk3.png"};
    String shoot = "player-shoot.png";
    String jump = "player-jump.png";
    String shootJump = "player-jump-shoot.png";
    int step = 0;
    int dir = 1;
    int frame = 0;
    boolean flipped = false;
    boolean dead = false;
    int buccaneer = 0;
    int animDelay = 10;
    boolean reloading = false;
    int bullets = 6;
    int idleCount = 0;
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        frame += 1;
        
        if (dead) {
            if (frame == 200) {
                Greenfoot.setWorld(new GameOver());
            }
            return;
        }
        
        if (subActor == null) {
            subActor = new SubImage();
            UI ui = new UI(this);
            getWorld().addObject(subActor, getX(), getY());
            getWorld().addObject(ui, ui.getImage().getWidth()/2, ui.getImage().getHeight()/2);
            life = 10;
        }
        super.act();
        
        if (frame % animDelay == 0) { 
           flipped = false;
           if ( Greenfoot.isKeyDown("a") ||  Greenfoot.isKeyDown("d"))
           {
               step = (step + 1) % walkCycle.length;
           }
           else {
               step = 0;
           }
           
           subActor.setImage(walkCycle[step]);
           
           if (idleCount >= 200) {
            subActor.setImage("player-idle2.png");
           }
           
        }
        
        
        if(Greenfoot.mousePressed(null) && !reloading) {
            bullets -= 1;
            idleCount = 0;
            if(bullets == 0) {
                reloading = true;
            }
            frame += (frame / animDelay) * animDelay;
            flipped = false;
            shoot(Greenfoot.getMouseInfo());
            if(getOnGround()) {
               subActor.setImage(shoot); 
            }
            else {
                subActor.setImage(shootJump);
            }
            return;
        }
        
        if ( Greenfoot.isKeyDown("a") )
           {
               moveLeft();
           }
           else if ( Greenfoot.isKeyDown("d") )
           {
               moveRight();
           }
        
        if ( Greenfoot.isKeyDown("w") ) {
            flipped = false;
            frame += (frame / animDelay) * animDelay;
            jump();
            subActor.setImage(jump);
        }
        
        if ( Greenfoot.isKeyDown("r") ) {
            if (bullets < 6) {
                reloading = true;
            }
        }
        
        if(Greenfoot.getMouseInfo() != null && Greenfoot.getMouseInfo().getX() < getX()) {
            dir = -1;
        }
        else {
            dir = 1;
        }
        
        if (dir == -1 && !flipped) {
               flipped = true;
               subActor.getImage().mirrorHorizontally();
        }
        if (dir == 1 && flipped) {
               flipped = false;
               subActor.getImage().mirrorHorizontally();
        }
        
        if(frame % 100 == 0 && buccaneer < 10) {
            buccaneer += 1;
        }
        
        if(frame % 40 == 0 && reloading) {
            bullets += 1;
            if (bullets == 6) {
                reloading = false;
            }
        }
        
        if (Greenfoot.getKey() == null && !Greenfoot.mousePressed(null)) {
            idleCount += 1;
        }
        else {
            idleCount = 0;
        }

        subActor.setLocation(getX() + dir * 5, getY());
    } 
    
    public Player() {
        super();
        getImage().setTransparency(1);
    }
    
    public void die() {
       frame = 0;
       dead = true;
       setImage("player-dead.png");
       if(flipped) {
        getImage().mirrorHorizontally();
       }
       getWorld().removeObject(subActor);
    }
}
