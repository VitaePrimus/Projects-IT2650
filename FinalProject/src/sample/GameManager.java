package sample;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {

    ArrayList<Enemy> enemy = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();

    public void spawnEnemy(){
        Random rng = new Random();
        int spawn;

        spawn = rng.nextInt(2);
        if (spawn == 0) {
            enemy.add(new Enemy(0, -100));
        }
        spawn = rng.nextInt(2);
        if (spawn == 1) {
            enemy.add(new Enemy(100, -100));
        }
        spawn = rng.nextInt(2);
        if (spawn == 1) {
            enemy.add(new Enemy(200, -100));
        }
        spawn = rng.nextInt(2);
        if (spawn == 1) {
            enemy.add(new Enemy(300, -100));
        }
        spawn = rng.nextInt(2);
        if (spawn == 1) {
            enemy.add(new Enemy(400, -100));
        }
    }

    public void moveEnemy(Enemy enemy){
        enemy.move(1.1);
    }
    public void moveBulet(Bullet bullet){
        bullet.move(5);
    }

    public void spawnBullet(double shipPos){
        bullets.add(new Bullet((int)(shipPos + 47.0), 700));
    }

    public void moveBullet(){

    }

//    public Enemy getEnemy(){
//        for(int x = 0; x < enemy.size(); x++) {
//            return enemy.get(x);
//        }
//        return enemy.get
//    }
}
