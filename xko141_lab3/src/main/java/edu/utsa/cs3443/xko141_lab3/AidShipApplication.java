package edu.utsa.cs3443.xko141_lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AidShipApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                AidShipApplication.class.getResource("/edu/utsa/cs3443/xko141_lab3/layouts/main-screen.fxml")
        );
        Scene scene = new Scene(loader.load(), 700, 500);
        stage.setTitle("The Global Emergency Response Organization");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

