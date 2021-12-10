package TicTacFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        AnchorPane root = new AnchorPane();

        //This is the creation of the GameManager and SoundManager objects,
        GameManager game = new GameManager();
        SoundManager sm = new SoundManager();

        AtomicInteger p1count = new AtomicInteger();
        AtomicInteger p2count = new AtomicInteger();

        TTTbutton[][] box = new TTTbutton[3][3];

        //This is how you load up an image.
        //For images, the root folder is the src folder.
        Image candy = new Image("images/candy.png");
        Image ornament = new Image("images/ornament.png");

        //This label is just a big pick square sitting behind the tic tac toe buttons.
        //The getStyleClass().add() method applies a CSS class to a javafx control object.
        Label bb = new Label();
        bb.setPrefSize(400, 400);
        bb.getStyleClass().add("bbStyle");

        //This is the label that says "Tic-Tac-Toe" in the upper right corner.
        //The lTitle style gives it the font size and color.
        Label title = new Label("Tic-Tac-Toe!");
        title.getStyleClass().add("lTitle");

        Label lWinsCount = new Label();
        lWinsCount.setText("Score: P1 - " + p1count + " P2 - " + p2count);
        lWinsCount.getStyleClass().add("lWinsCount");

        AnchorPane.setRightAnchor(title, 100.0);
        AnchorPane.setTopAnchor(title, 20.0);

        AnchorPane.setLeftAnchor(lWinsCount, 20.0);
        AnchorPane.setBottomAnchor(lWinsCount, 20.0);

        AnchorPane.setLeftAnchor(bb, 100.0);
        AnchorPane.setTopAnchor(bb, 100.0);

        root.getChildren().addAll(bb, title, lWinsCount);

        //This calls the method in the sound manager to start the background music.
        sm.startMusic();

        //This lengthy nested loop is used to build the 3X3 grid of game buttons.
        for(int x = 0; x < box.length; x++)
        {
            for(int y = 0; y < box[x].length; y++)
            {
                box[x][y] = new TTTbutton(x, y);
                box[x][y].setPrefSize(120, 120);

                //This if/else statement creates the alternating purple and green patter
                if((x + y) % 2 == 0)
                {
                    box[x][y].getStyleClass().add("color1");
                }
                else
                {
                    box[x][y].getStyleClass().add("color2");
                }

                //The next two lines of code are used to align the buttons.
                AnchorPane.setLeftAnchor(box[x][y], (110.0 + (x * 130)));
                AnchorPane.setTopAnchor(box[x][y], (110.0 + (y * 130)));
                root.getChildren().add(box[x][y]);

                //This adds the button logic.
                box[x][y].setOnAction((ActionEvent event) ->
                {
                    //This next line of code is a bit awkward.
                    //A button cannot self identify what it's array indexes are.
                    //This creates a temporary duplicate of the clicked button called click.
                    //Any changes made to the duplicated 'click' will also be made to the button that was clicked.
                    TTTbutton clicked = (TTTbutton)event.getSource();
                    System.out.println("You clicked: " + clicked.getX() + ", " + clicked.getY());

                    //Before making a move, this if statement ensures the space is empty.
                    if(game.checkSpaceEmpty(clicked.getX(), clicked.getY()) && !game.checkWin())
                    {
                        game.markSpot(clicked.getX(), clicked.getY());
                        //Using the takeTurn method, we can alternate between the two players.
                        if (game.takeTurn()) {
                            //clicked.setText("X");
                            sm.tweetSound();
                            clicked.setGraphic(new ImageView(candy));
                        } else{
                            //clicked.setText("O");
                            sm.hopSound();
                            clicked.setGraphic(new ImageView(ornament));
                        }
                    }
                    if(game.checkWin() && game.getTurn() && !game.getStop()){
                        System.out.println("p1 wins");
                        p1count.getAndIncrement(); // Had no idea what this does, p1count++ did not work, Intellij converted to this automatically
                        lWinsCount.setText("Score: P1 - " + p1count + " P2 - " + p2count);
                        game.stop();
                    }
                    else if(game.checkWin() && !game.getTurn() && !game.getStop()){
                        System.out.println("p2 wins");
                        p2count.getAndIncrement();
                        lWinsCount.setText("Score: P1 - " + p1count + " P2 - " + p2count);
                        game.stop();
                    }
                });

            }
        }

        //This is a reset button that is not yet functional.
        //As you can see, it was also styled with a CSS class.
        Button reset = new Button("Reset");
        reset.getStyleClass().add("bReset");
        reset.setPrefSize(80, 60);
        reset.setOnAction((ActionEvent event) ->{
            System.out.println("Reset pressed");
            //To remove an image from an item, you can just use the setGraphic method and set it to null.
            //For example box[0][0].setGraphic(null);
            for(int x = 0; x < box.length; x++) {
                for (int y = 0; y < box[x].length; y++) {
                    game.resetBoard(box[x][y], x, y);
                }
            }

        });

        Button mute = new Button("Mute");
        mute.getStyleClass().add("bReset");
        mute.setPrefSize(80,60);
        mute.setOnAction((ActionEvent event) ->{
            sm.muteMusic();
            System.out.println("Mute Pressed");
        });


            //This program still has some incomplete items
        //Such as checking for a winner
        //Resetting the game
        //And possibly even keeping track of wins and losses.

        AnchorPane.setRightAnchor(reset, 20.0);
        AnchorPane.setBottomAnchor(reset, 20.0);

        AnchorPane.setRightAnchor(mute, 140.0);
        AnchorPane.setBottomAnchor(mute, 20.0);

        root.getChildren().addAll(reset, mute);

        Scene scene = new Scene(root, 600, 600);

        //This line attaches the CSS file to this program.
        scene.getStylesheets().add("TicTacFX/styles.css");

        primaryStage.setTitle("Christmas Themed Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
