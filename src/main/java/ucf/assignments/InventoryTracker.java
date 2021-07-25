/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Orion Kinsler
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class InventoryTracker extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        GuiManager controller = new GuiManager();
        loader.setController(controller);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryTracker.fxml")));

        Scene appScene = new Scene(root);

        primaryStage.setTitle("Inventory Tracker");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(appScene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        InventoryTracker.launch(args);
    }
}
