package edu.utsa.cs3443.xko141_lab3;

import edu.utsa.cs3443.xko141_lab3.model.AidShip;
import edu.utsa.cs3443.xko141_lab3.model.AidShipManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Controller for the main-screen.fxml view.
 * Handles all user interactions for the Aid Ship Management System.
 *
 * Author: Andres Chavez
 * Course: CS3443
 * Lab 3 - Aid Ship Management System
 */
public class MainScreenController {

    @FXML private ImageView imgLogo;
    @FXML private Label lblAppTitle;
    @FXML private Button btnListShips;
    @FXML private Button btnGo;
    @FXML private TextArea txtRegistrationNumber;
    @FXML private RadioButton rdoFind;
    @FXML private RadioButton rdoDelete;
    @FXML private ToggleGroup actionGroup;


    private AidShipManager manager;

    /**
     * Called automatically when the FXML is loaded.
     * Initializes UI components and loads data.
     */
    @FXML
    public void initialize() throws IOException {
        manager = new AidShipManager();

        // Initialize ToggleGroup for radio buttons
        actionGroup = new ToggleGroup();
        rdoFind.setToggleGroup(actionGroup);
        rdoDelete.setToggleGroup(actionGroup);
        rdoFind.setSelected(true); // Set default selection

        // Rest of your initialization code...
        manager.loadAidShips("/edu/utsa/cs3443/xko141_lab3/data/aid_ships.csv");

        // Load logo image from resources
        try {
            Image logoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/edu/utsa/cs3443/xko141_lab3/images/logo.png"
            )));
            imgLogo.setImage(logoImage);
        } catch (Exception e) {
            showAlert("Logo image not found.", AlertType.WARNING);
        }

        lblAppTitle.setText("The Global Emergency Response Organization");
        btnListShips.setOnAction(e -> listAllShips());
        btnGo.setOnAction(e -> {
            try {
                performAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
































    /**
     * Displays all ships in a simple dialog box.
     */
    private void listAllShips() {
        ArrayList<AidShip> ships = manager.getAidShipList();

        if (ships.isEmpty()) {
            showAlert("No ships found in the database.", AlertType.INFORMATION);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (AidShip s : ships) {
            sb.append(s.toString()).append("\n");
        }

        showAlert(sb.toString(), AlertType.INFORMATION);
    }

    /**
     * Executes Find or Delete depending on the selected radio button.
     */
    private void performAction() throws IOException {
        String regNumber = txtRegistrationNumber.getText().trim();

        if (regNumber.isEmpty()) {
            showAlert("Please enter a registration number first.", AlertType.WARNING);
            return;
        }

        if (rdoFind.isSelected()) {
            findShip(regNumber);
        } else if (rdoDelete.isSelected()) {
            deleteShip(regNumber);
        } else {
            showAlert("Please select an action (Find or Delete).", AlertType.WARNING);
        }
    }

    /**
     * Searches for a ship by registration number.
     */
    private void findShip(String regNumber) {
        AidShip ship = manager.findAidShip(regNumber);
        if (ship != null) {
            showAlert("Ship found:\n" + ship.toString(), AlertType.INFORMATION);
        } else {
            showAlert("No ship found with registration number: " + regNumber, AlertType.ERROR);
        }
    }

    /**
     * Deletes a ship by registration number.
     */
    private void deleteShip(String regNumber) throws IOException {
        String dataPath = "data/aid_ships.csv";
        boolean deleted = manager.deleteAidShip(regNumber);
        if (deleted) {
            showAlert("Ship with registration number " + regNumber + " was deleted.", AlertType.INFORMATION);
        } else {
            showAlert("No ship found with registration number " + regNumber, AlertType.ERROR);
        }
    }

    /**
     * Utility method to show a popup alert.
     */
    private void showAlert(String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Aid Ship Manager");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
