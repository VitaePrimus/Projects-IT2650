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

    public void moveEnemy(Enemy enemy, double ts){
        enemy.move(200 * ts);
    }
    public void moveBulet(Bullet bullet, double ts){
        bullet.move(700 * ts);
    }

    public void spawnBullet(double shipPos){
        bullets.add(new Bullet((int)(shipPos + 47.0), 700));
    }

//    public Enemy getEnemy(){
//        for(int x = 0; x < enemy.size(); x++) {
//            return enemy.get(x);
//        }
//        return enemy.get
//    }
}
