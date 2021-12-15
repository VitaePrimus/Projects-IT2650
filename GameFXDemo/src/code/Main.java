package code;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        //Set up the group, scene, and stage (group is a generic 'pane' so to speak)
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        //Create the canvas and add it to the group.
        //For a canvas, everything is aligned to the top left corner, which is (0, 0).
        Canvas canvas = new Canvas(800, 600); //800 X 600 is the canvas size
        root.getChildren().add(canvas);

        //The GraphicsContext will be used to 'paint' things on the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.AQUA); //Sets the fill color for generic shapes and text
        gc.setStroke(Color.MIDNIGHTBLUE); //Sets an outline color for generic shapes and text
        gc.setLineWidth(1); //Sets the outline size
        gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32)); //Sets a font

        //This ArrayList of string is going to store which keys from the keyboard are currently being pressed.
        ArrayList<String> input = new ArrayList<>();

        //This block of code detects when a key is pressed and adds it to the list, so long as it isn't already added.
        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();

                    // only add once... prevent duplicates
                    if ( !input.contains(code)) {
                        input.add(code);
                    }
                });

        //This block of code detects when a key has been released and removes it from the list.
        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });

        //Set up the StaticSprite objects
        StaticSprite space = new StaticSprite("images/space.png", 0, 0);
        StaticSprite sun = new StaticSprite("images/sun.png", 300, 200);
        StaticSprite earth = new StaticSprite("images/earth.png", 590, 250);
        StaticSprite block = new StaticSprite("images/block.png", 740, 20);

        //Create the UFO object
        UFO ufo = new UFO(20, 20);

        //Create the time manager object to be used by the AnimationTimer
        TimeManager tm = new TimeManager();

        //Starts the game cycle (more or less infinite loop?)
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
                earth.setNewLocation(x, y);

                gc.drawImage(space.getImage(), space.getX1(), space.getY1()); //Draws the space background

                gc.fillText(stat, 150, 100); //Draws the current text
                gc.strokeText(stat, 150, 100); //And the outline

                gc.drawImage(ufo.getFrame(t), ufo.getX(), ufo.getY()); //Draws the UFO

                gc.drawImage(block.getImage(), block.getX1(), block.getY1()); //Draws a random block

                //This if/else draws the earth and sun.
                //Depending on where the earth is, it will either draw the earth on top, or the sun
                //Whichever is drawn later goes 'on top'
                if(Math.sin(t) < 0) {
                    gc.drawImage(earth.getImage(), earth.getX1(), earth.getY1());
                    gc.drawImage(sun.getImage(), sun.getX1(), sun.getY1());
                }
                else {
                    gc.drawImage(sun.getImage(), sun.getX1(), sun.getY1());
                    gc.drawImage(earth.getImage(), earth.getX1(), earth.getY1());
                }

                //This will take input and check for collisions if the UFO is 'alive'
                if(ufo.isAlive()) {

//                    //This commented out example shows basic movement
//                    //This movement is some number of pixels, in this case 100, in a second
//
//                    if (input.contains("W")) {
//                        ufo.moveY(-100 * ts);
//                    }
//                    if (input.contains("S")) {
//                        ufo.moveY(100 * ts);
//                    }
//                    if (input.contains("A")) {
//                        ufo.moveX(-100 * ts);
//                    }
//                    if (input.contains("D")) {
//                        ufo.moveX(100 * ts);
//                    }


                    //This example below moves the UFO based on more of an acceleration
                    //This moves the object more like the movement in the game Asteroids
                    if (input.contains("W")) {
                        ufo.accelY(-5 * ts);
                    }
                    if (input.contains("S")) {
                        ufo.accelY(5 * ts);
                    }
                    if (input.contains("A")) {
                        ufo.accelX(-5 * ts);
                    }
                    if (input.contains("D")) {
                        ufo.accelX(5 * ts);
                    }
                    ufo.AccelMove();

                    //Checks to see if the UFO hits Earth. If it does, BOOM!
                    if ( ufo.collisionSide(earth) != 'n') {
                        ufo.boom();
                    }

                    //Checks to see if the UFO hits the block, and if so, from which side.
                    //Hitting the block will stop the UFOs momentum in that direction.
                    //Instead of stopping the momentum, you could bounce, or reverse the UFO momentum
                    //by using the 'bounce' methods instead of the resetAccel methods.
                    switch (ufo.collisionSide(block)){
                        case 'r':
                            ufo.setX(block.getX1() - ufo.getWidth());
                            ufo.resetAccelX();
                            break;
                        case 'l':
                            ufo.setX((block.getX2()));
                            ufo.resetAccelX();
                            break;
                        case 'b':
                            ufo.setY(block.getY1() - ufo.getHeight());
                            ufo.resetAccelY();
                            break;
                        case 't':
                            ufo.setY((block.getY2()));
                            ufo.resetAccelY();
                    }

                }
            }
        }.start();

        //Puts Game Demo in the title bar of the window, and shows the window
        primaryStage.setTitle("Game Demo");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
