package sample;

import javafx.scene.image.Image;

import java.util.Random;

public class Enemy {
    private Image spriteEnemy;
    private boolean alive;

    private double x, y;

    public Enemy(int x, int y){
        spriteEnemy = new Image("images/asteroid.png");
        this.x = x;
        this.y = y;
        alive = true;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getX2() { return x + spriteEnemy.getWidth(); }
    public double getY2() { return y + spriteEnemy.getHeight(); }
    public double getWidth() { return spriteEnemy.getWidth(); }
    public double getHeight() { return spriteEnemy.getHeight();}
    public Image getFrame(){ return spriteEnemy; }

    public void move(double amount) { y = y + amount; }


//    public void createEnemy(){
//
//        if(randomSet == 0){
//            this.x = 0;
//            this.y = 0;
//        }
//        else if(randomSet == 1){
//            this.x = 100;
//            this.y = 0;
//        }
//        else if(randomSet == 2){
//            this.x = 200;
//            this.y = 0;
//        }
//        else if(randomSet == 3){
//            this.x = 300;
//            this.y = 0;
//        }
//        else{
//            this.x = 400;
//            this.y = 0;
//        }
//
//    }

}
