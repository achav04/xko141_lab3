package edu.utsa.cs3443.xko141_lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX entry point for the Aid Ship Management System.
 *
 * Author: Andres Chavez
 * Course: CS3443
 * Lab 3 - Aid Ship Management System
 */
public class AidShipApplication extends Application {

    public static void main(String[] args) {
        launch(args); // DO NOT REMOVE — still required by JavaFX
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/edu/utsa/cs3443/xko141_lab3/layouts/main-screen.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setTitle("The Global Emergency Response Organization");
        stage.setScene(scene);
        stage.show();
    }
}
