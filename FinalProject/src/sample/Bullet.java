package sample;

import javafx.scene.image.Image;

public class Bullet {
    private Image spriteBullet;
    private boolean alive;

    private double x, y;

    public Bullet(int x, int y){
        spriteBullet = new Image("images/bullet.png");
        this.x = x;
        this.y = y;
        alive = true;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getX2() { return x + spriteBullet.getWidth(); }
    public double getY2() { return y + spriteBullet.getHeight(); }
    public double getWidth() { return spriteBullet.getWidth(); }
    public double getHeight() { return spriteBullet.getHeight();}
    public Image getFrame(){ return spriteBullet; }
    public void move(double amount) { y = y - amount; }

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

}
