package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {

    int spawnCount = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root);

        Canvas canvas = new Canvas(500,800);
        root.getChildren().add(canvas);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();

        ///////////////////////////////////////
        Ship ship = new Ship(200,700);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        TimeManager tm = new TimeManager();
        GameManager gm = new GameManager();


        StaticSprite space = new StaticSprite("images/space.png", 0, 0);


        ArrayList<String> input = new ArrayList<>();

        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();

                    // only add once... prevent duplicates
                    if ( !input.contains(code)) {
                        input.add(code);
                    }
                });

        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double ts = tm.timeStamp(); //ts is the time of the last frame
                double t = tm.timeSinceStart(); //t is the time since the program started

                //This is showing the value of sin of t and cos of t since the program started.
                //You probably don't really need this for anything...
                String stat = "t: " + String.format("%.2f",t) + " | sin(t): " + String.format("%.2f",Math.sin(t)) + " | cos(t): " + String.format("%.2f",Math.cos(t));

                //Using the sin and cos values of t
                double x = 350 + 240 * Math.cos(t);
                double y = 250 + 80 * Math.sin(t);

                gc.drawImage(space.getImage(), space.getX1(), space.getY1()); //Draws the space background
//                gc.fillText(stat, 150, 100); //Draws the current text
//                gc.strokeText(stat, 150, 100); //And the outline
                gc.drawImage(ship.getFrame(), ship.getX(), ship.getY()); //Draws the ship

                for(int i = 0; i < gm.enemy.size(); i++) {
                    gc.drawImage(gm.enemy.get(i).getFrame(), gm.enemy.get(i).getX(), gm.enemy.get(i).getY());
                }
                for(int i = 0; i < gm.bullets.size(); i++) {
                    gc.drawImage(gm.bullets.get(i).getFrame(), gm.bullets.get(i).getX(), gm.bullets.get(i).getY());
                }

                spawnCount++;
                System.out.println(spawnCount);

                if(spawnCount == 1) {
                    gm.spawnEnemy();
                }
                if(spawnCount > 100){
                    spawnCount = 0;
                }
                for(int i = 0; i < gm.enemy.size(); i++) {
                    gm.moveEnemy(gm.enemy.get(i));
                }
                for(int i = 0; i < gm.bullets.size(); i++) {
                    gm.moveBulet(gm.bullets.get(i));
                }

                if (ship.getX() <= 0) {
                    ship.moveX(20 * ts);
                }
                if (ship.getX() >= 400) {
                    ship.moveX(-20 * ts);
                }

                //This will take input and check for collisions if the UFO is 'alive'
                if(ship.isAlive() && ship.getX() > 0 && ship.getX() < 400) {


                    if (input.contains("A")) {
                        ship.moveX(-180 * ts);
                    }
                    if (input.contains("D")) {
                        ship.moveX(180 * ts);
                    }

                    if (input.contains("SPACE") && spawnCount % 20 == 0) {
                        gm.spawnBullet(ship.getX());
                    }

                    //Checks to see if the UFO hits Earth. If it does, BOOM!
//                    if ( ship.collisionSide(enemy) != 'n') {
//                        ship.boom();
//                    }

                    //Checks to see if the UFO hits the block, and if so, from which side.
                    //Hitting the block will stop the UFOs momentum in that direction.
                    //Instead of stopping the momentum, you could bounce, or reverse the UFO momentum
                    //by using the 'bounce' methods instead of the resetAccel methods.
//                    switch (ufo.collisionSide(block)){
//                        case 'r':
//                            ufo.setX(block.getX1() - ufo.getWidth());
//                            ufo.resetAccelX();
//                            break;
//                        case 'l':
//                            ufo.setX((block.getX2()));
//                            ufo.resetAccelX();
//                            break;
//                        case 'b':
//                            ufo.setY(block.getY1() - ufo.getHeight());
//                            ufo.resetAccelY();
//                            break;
//                        case 't':
//                            ufo.setY((block.getY2()));
//                            ufo.resetAccelY();
//                    }

                }
            }
        }.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
