package edu.utsa.cs3443.xko141_lab3;

import edu.utsa.cs3443.xko141_lab3.model.AidShip;
import edu.utsa.cs3443.xko141_lab3.model.AidShipManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the main-screen.fxml view.
 * Handles all user interactions for the Aid Ship Management System.
 *
 * Author: Andres Chavez
 * Course: CS3443
 * Lab 3 - Aid Ship Management System
 */
public class MainScreenController {

    @FXML private ImageView imageView;
    @FXML private Label appTitle;
    @FXML private Button btnList;
    @FXML private RadioButton btnFind;
    @FXML private RadioButton btnDelete;
    @FXML private TextField txtRegistrationNum;
    @FXML private TextArea txtList;
    @FXML private Button btnGo;

    private AidShipManager manager;

    @FXML
    public void initialize() throws IOException {
        manager = new AidShipManager();

        manager.loadAidShips("/edu/utsa/cs3443/xko141_lab3/data/aid_ships.csv");

        // Load logo
        try {
            Image logoImage = new Image(
                    getClass().getResourceAsStream("/edu/utsa/cs3443/xko141_lab3/images/logo.png")
            );
            imageView.setImage(logoImage);
        } catch (Exception e) {
            printShip("Logo image not found.");
        }
        txtList.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 12;");

        appTitle.setText("The Global Emergency Response Organization");

    }

    @FXML
    private void onGo() throws IOException {
        String regNumber = txtRegistrationNum.getText().trim();

        if (regNumber.isEmpty()) {
            printShip("Please enter a registration number first.");
            return;
        }

        if (btnFind.isSelected()) {
            findShip(regNumber);
        } else if (btnDelete.isSelected()) {
            deleteShip(regNumber);
        } else {
            printShip("Please select an action (Find or Delete).");
        }
    }

    /**
     * Searches for a ship by registration number.
     */
    private void findShip(String regNumber) {
        AidShip ship = manager.findAidShip(regNumber);
        if (ship != null) {
            printShip("Ship found:\n" + ship.toString());
        } else {
            printShip("No ship found with registration number: " + regNumber);
        }
    }

    /**
     * Deletes a ship by registration number.
     */
    private void deleteShip(String regNumber) throws IOException {
        boolean deleted = manager.deleteAidShip(regNumber);
        if (deleted) {
            printShip("Ship with registration number " + regNumber + " was deleted.");
        } else {
            printShip("No ship found with registration number " + regNumber);
        }
    }

    /**
     * Utility method to show a popup alert.
     */
    private void printShip(String message) {
        txtList.appendText(message);
    }

    @FXML
    private void onList() {
        ArrayList<AidShip> ships = manager.getAidShipList();

        if (ships.isEmpty()) {
            printShip("No ships found in the database.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (AidShip s : ships) {
            sb.append(s.toString()).append("\n");
        }
        printShip("----------------------------------------------------------------------------------------------------------------------------\n");
        printShip(String.format(
                "|%-15s |%-15s |%-10s |%-10s |%-22s |%-22s |%-10s |%-13s\n",
                "Name", "Registration", "Tonnage", "Crew Size", "Current Port",
                "Aid Type", "Supplies", "Helipad"
        ));
        printShip("----------------------------------------------------------------------------------------------------------------------------\n");
        printShip(sb.toString());
    }
}
