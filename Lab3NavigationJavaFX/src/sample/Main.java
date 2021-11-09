package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        // -- Setting the stage --
        AnchorPane root = new AnchorPane();
        stage.setTitle("Navigation");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();

        // -- Setting the actors --

        // -- Adding functionality --

        // -- Adding all to the stage --
    }


    public static void main(String[] args) {
        launch(args);
    }
}
