package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        // -- Setting the stage --
        Location cleveland = new Location("Cleveland", 0, 0, true);
        Location np = new Location("North Pole", 500, -100, true);
        Location ch = new Location("Campbell Hill", -50, -50, false);
        Location tokyo = new Location("Tokyo", -100, 1000, true);
        Location chicago = new Location("Chicago", -200, 75, true);
        Location epa = new Location("Erie, PA", 150, 0, true);
        Location anf = new Location("Allegheny Nation Forest", 300, -75, false);

        AnchorPane root = new AnchorPane();
        AnchorPane pCreateHelicopter = new AnchorPane();
        AnchorPane pAddLocation = new AnchorPane();

        Scene scene = new Scene(root, 500,600);
        Scene sCreateHelicopter = new Scene(pCreateHelicopter,340,300);
        Scene sAddLocation = new Scene(pAddLocation, 340, 300);

        stage.setTitle("Navigation");
        stage.setScene(sCreateHelicopter);
        stage.show();


        // -- Setting the actors --
        Label lCurrentFuel = new Label();
        ListView<Location> location = new ListView<>();
        Button bFly = new Button();
            bFly.setText("Fly");
        Button createHelicopter = new Button();
            createHelicopter.setText("Create");
        Button bRefuel = new Button();
            bRefuel.setText("Refuel");
        Button bAddLocation = new Button();
            bAddLocation.setText("Add Location");
        Button bConfirmLocation = new Button();
            bConfirmLocation.setText("Confirm Location");
        Button bRemoveLocation = new Button();
            bRemoveLocation.setText("Remove Location");


        TextField tMaxFuel = new TextField();
        TextField tMpg = new TextField();
        TextField tName = new TextField();
        TextField tXcor = new TextField();
        TextField tYcor = new TextField();

        CheckBox cFuel = new CheckBox();

        Label lMaxFuel = new Label();
            lMaxFuel.setText("Enter Max Fuel");
        Label lMpg = new Label();
            lMpg.setText("Enter MPG");
        Label lCreate = new Label();
            lCreate.setText("Create a Helicopter");
        Label lLocation = new Label();
        Label lName = new Label();
            lName.setText("Enter Location Name");
        Label lXcor = new Label();
            lXcor.setText("Enter X Coordinates");
        Label lYcor = new Label();
            lYcor.setText("Enter Y Coordinates");
        Label lFuel = new Label();
            lFuel.setText("Has Fuel?");
        Label lAddLocation = new Label();
            lAddLocation.setText("Add a Location");


        location.getItems().add(cleveland);
        location.getItems().add(np);
        location.getItems().add(ch);
        location.getItems().add(tokyo);
        location.getItems().add(chicago);
        location.getItems().add(epa);
        location.getItems().add(anf);

        Helicopter helicopter = new Helicopter(cleveland, 0, 0);

        // -- Adding functionality --

        createHelicopter.setOnAction((ActionEvent event) -> {
            try {
                helicopter.setMaxFuel(Double.parseDouble(tMaxFuel.getText()));
                helicopter.refuel();
                helicopter.setMpg(Double.parseDouble(tMpg.getText()));
                lLocation.setText(helicopter.getLocation());
                System.out.println(tMaxFuel.getText());
                System.out.println(helicopter.getFuel());
                lCurrentFuel.setText("Fuel: " + helicopter.getFuel() + "/" + helicopter.getMaxFuel());
                stage.setScene(scene);
            }
            catch(NumberFormatException e)
            {
                lCreate.setText("Type in Numbers");
            }
        });

        bFly.setOnAction((ActionEvent event) -> {
            if(helicopter.fly(location.getSelectionModel().getSelectedItem())) {
                lLocation.setText("Your current location is " + helicopter.getLocation());
            }
            else if(helicopter.getLocationClass() == location.getSelectionModel().getSelectedItem()){
                lLocation.setText("You are currently here");
            }
            else{
                lLocation.setText("Not Enough Fuel");
            }
            lCurrentFuel.setText("Fuel: " + helicopter.getFuel() + "/" + helicopter.getMaxFuel());
        });

        bRefuel.setOnAction((ActionEvent event) -> {
            if(helicopter.getLocationClass().getFuel()) {
                helicopter.refuel();
                lLocation.setText("Refueled. Current Fuel: " + helicopter.getFuel());
            }
            else{
                lLocation.setText("No Fuel Here");
            }
            lCurrentFuel.setText("Fuel: " + helicopter.getFuel() + "/" + helicopter.getMaxFuel());
        });

        bAddLocation.setOnAction((ActionEvent event) -> {
            stage.setScene(sAddLocation);
        });

        bRemoveLocation.setOnAction((ActionEvent event) -> {
            location.getItems().remove(location.getSelectionModel().getSelectedItem());
        });

        bConfirmLocation.setOnAction((ActionEvent event) -> {
            try {
                location.getItems().add(new Location(tName.getText(),
                        Integer.parseInt(tXcor.getText()),
                        Integer.parseInt(tYcor.getText()),
                        cFuel.isSelected()));
                stage.setScene(scene);
            }
            catch(NumberFormatException e)
            {
                lAddLocation.setText("Type in Numbers");
            }
        });

        // -- Adding all to the stage --
        AnchorPane.setLeftAnchor(location, 40.0);
        AnchorPane.setTopAnchor(location,20.0);

        AnchorPane.setLeftAnchor(lLocation, 40.0);
        AnchorPane.setBottomAnchor(lLocation,40.0);

        AnchorPane.setLeftAnchor(lCurrentFuel, 40.0);
        AnchorPane.setBottomAnchor(lCurrentFuel,80.0);

        AnchorPane.setRightAnchor(bFly, 40.0);
        AnchorPane.setTopAnchor(bFly,40.0);

        AnchorPane.setRightAnchor(bRefuel, 40.0);
        AnchorPane.setTopAnchor(bRefuel,80.0);

        AnchorPane.setRightAnchor(bAddLocation, 40.0);
        AnchorPane.setTopAnchor(bAddLocation,120.0);

        AnchorPane.setRightAnchor(bRemoveLocation, 40.0);
        AnchorPane.setTopAnchor(bRemoveLocation,160.0);

        AnchorPane.setLeftAnchor(lCreate, 40.0);
        AnchorPane.setTopAnchor(lCreate,20.0);

        AnchorPane.setLeftAnchor(lMaxFuel, 40.0);
        AnchorPane.setTopAnchor(lMaxFuel,60.0);

        AnchorPane.setLeftAnchor(lMpg, 40.0);
        AnchorPane.setTopAnchor(lMpg,100.0);

        AnchorPane.setLeftAnchor(tMaxFuel, 140.0);
        AnchorPane.setTopAnchor(tMaxFuel,60.0);

        AnchorPane.setLeftAnchor(tMpg, 140.0);
        AnchorPane.setTopAnchor(tMpg,100.0);

        AnchorPane.setLeftAnchor(createHelicopter, 40.0);
        AnchorPane.setTopAnchor(createHelicopter,140.0);

        AnchorPane.setLeftAnchor(lAddLocation, 40.0);
        AnchorPane.setTopAnchor(lAddLocation,20.0);

        AnchorPane.setLeftAnchor(lName, 40.0);
        AnchorPane.setTopAnchor(lName,60.0);

        AnchorPane.setLeftAnchor(lXcor, 40.0);
        AnchorPane.setTopAnchor(lXcor,100.0);

        AnchorPane.setLeftAnchor(lYcor, 40.0);
        AnchorPane.setTopAnchor(lYcor,140.0);

        AnchorPane.setLeftAnchor(lFuel, 40.0);
        AnchorPane.setTopAnchor(lFuel,180.0);

        AnchorPane.setLeftAnchor(tName, 160.0);
        AnchorPane.setTopAnchor(tName,60.0);

        AnchorPane.setLeftAnchor(tXcor, 160.0);
        AnchorPane.setTopAnchor(tXcor,100.0);

        AnchorPane.setLeftAnchor(tYcor, 160.0);
        AnchorPane.setTopAnchor(tYcor,140.0);

        AnchorPane.setLeftAnchor(cFuel, 160.0);
        AnchorPane.setTopAnchor(cFuel,180.0);

        AnchorPane.setLeftAnchor(bConfirmLocation, 160.0);
        AnchorPane.setTopAnchor(bConfirmLocation,220.0);

        root.getChildren().addAll(lLocation, bFly, location, bRefuel, lCurrentFuel, bAddLocation, bRemoveLocation);
        pCreateHelicopter.getChildren().addAll(tMpg,tMaxFuel,createHelicopter, lMpg,lMaxFuel,lCreate);
        pAddLocation.getChildren().addAll(lName, lXcor, lYcor, lAddLocation, tName, tXcor, tYcor, bConfirmLocation, cFuel, lFuel);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
