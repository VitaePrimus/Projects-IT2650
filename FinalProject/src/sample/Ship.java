package sample;

import javafx.scene.image.Image;

public class Ship {
    private Image spriteShip;
    private boolean alive;

    private double x, y;

    public Ship(double x, double y){
        spriteShip = new Image("images/ship.png");
        this.x = x;
        this.y = y;
        alive = true;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getX2() { return x + spriteShip.getWidth(); }
    public double getY2() { return y + spriteShip.getHeight(); }
    public double getWidth() { return spriteShip.getWidth(); }
    public double getHeight() { return spriteShip.getHeight();}
    public Image getFrame(){ return spriteShip; }


        public boolean isAlive() { return alive; }

    public void boom() { alive = false; }

    public void moveX(double amount) { x = x + amount; }
    public void moveY(double amount) { y = y + amount; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

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
