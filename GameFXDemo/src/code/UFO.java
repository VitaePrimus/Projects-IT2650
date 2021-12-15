package code;

import javafx.scene.image.Image;

public class UFO {
    private Image[] frames; //An array of images to be cycled through for an animated sprite
    private Image dead; //A single image
    private double duration; //The time in seconds for each frame of animation in the cycle
    private boolean alive; //Turns false if the UFO blows up.

    private double x, y; //Current location of hte top left corner of the UFO
    private double accX, accY; //Moves the UFO if more of an acceleration based movement is used (like the Asteroids ship)

    //Here is the constructor that takes a starting X and Y location
    public UFO(double x, double y){
        this.duration = .05;
        frames = new Image[6];
        for (int i = 0; i < 6; i++) {
            frames[i] = new Image("images/ufo/saucer" + (i + 1) + ".png");
        }
        dead = new Image("images/boom.png");
        this.x = x;
        this.y = y;
        alive = true;
        accX = 0;
        accY = 0;
    }

    //These are basically the 'getters' for this class
    public double getX() { return x; }
    public double getY() { return y; }
    public double getX2() { return x + frames[0].getWidth(); }
    public double getY2() { return y + frames[0].getHeight(); }
    public double getWidth() { return frames[0].getWidth(); }
    public double getHeight() { return frames[0].getHeight();}

    public boolean isAlive() { return alive; }

    //These are simple setters and mutators for the class
    //It would strange to use both moveX/Y and accelX/Y, but both options have been programmed in.
    public void boom() { alive = false; }

    public void moveX(double amount) { x = x + amount; }
    public void moveY(double amount) { y = y + amount; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void accelX(double amount) { accX += amount; }
    public void accelY(double amount) { accY += amount; }

    public void bounceX() { accX = -accX; }
    public void bounceY() { accY = -accY; }

    public void resetAccelX() { accX = 0; }
    public void resetAccelY() { accY = 0; }

    //This method moves the ship based on the current values of accelX/Y
    public void AccelMove()
    {
        x += accX;
        y += accY;
    }

    //This method returns the current frame of animation.
    //This isn't a particularly flexible or powerful animation technique, but we are running out of class sections.
    public Image getFrame(double time){
        if(alive) {
            int index = (int) ((time % (frames.length * duration)) / duration);
            return frames[index];
        }
        else
        {
            return dead;
        }
    }

    //This method checks for any collision with a staticSprite object
    public boolean collision(StaticSprite sprite)
    {
        boolean hit = false;
        if(this.getX2() > sprite.getX1() &&
                this.x < sprite.getX2() &&
                this.getY2() > sprite.getY1()
                && this.y < sprite.getY2())
        {
            hit = true;
        }
        return hit;
    }

    //This method checks for a collision, and also from what side of the UFO the collision happened
    //It returns a char of either n, t, b, r, l
    //n: none, t: top, b: bottom, r: right side, l: left side
    //Char works well enough here. A enum could have been used too.
    public char collisionSide(StaticSprite sprite)
    {
        char result = 'n';
        if(collision(sprite))
        {
            double r = this.getX2() - sprite.getX1();
            double l = sprite.getX2() - this.x;
            double b = this.getY2() - sprite.getY1();
            double t = sprite.getY2() - this.y;

            if (r < l && r < b && r < t) { result = 'r'; }
            else if (l < b && l < t) { result = 'l'; }
            else if (b < t) { result = 'b'; }
            else { result = 't'; }
        }
        return result;
    }
}
