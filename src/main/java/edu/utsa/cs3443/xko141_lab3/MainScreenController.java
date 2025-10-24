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
            print("Logo image not found.");
        }
        txtList.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 12;");

        appTitle.setText("The Global Emergency Response Organization");

    }

    @FXML
    private void onGo() throws IOException {
        String regNumber = txtRegistrationNum.getText().trim();

        if (regNumber.isEmpty()) {
            print("Please enter a registration number first.");
            return;
        }

        if (btnFind.isSelected()) {
            findShip(regNumber);
        } else if (btnDelete.isSelected()) {
            deleteShip(regNumber);
        } else {
            print("Please select an action (Find or Delete).");
        }
    }

    /**
     * Searches for a ship by registration number.
     */
    private void findShip(String regNumber) {
        AidShip ship = manager.findAidShip(regNumber);
        if (ship != null) {
            print(
                    "Aid Ship Card:"+
                    "\n----------------------------------------------------------------------"+
                    "\nName:                  " +ship.getName()+
                    "\nRegistration Number:   " +ship.getRegistrationNumber()+
                    "\nTonnage:               " +ship.getTonnage()+
                    "\nCrew Size:             " +ship.getCrewSize()+
                    "\nCurrent Port:          " +ship.getCurrentPort()+
                    "\nAid Type:              " +ship.getAidType()+
                    "\nSupplies On Board:     " +ship.getSuppliesOnBoard()+
                    "\nHelipad:               " +(ship.isHasHelipad() ? "Available" : "Not Available")+
                    "\n----------------------------------------------------------------------"
            );
        } else {
            print("No ship found with registration number: " + regNumber + "\n");
        }
    }

    /**
     * Deletes a ship by registration number.
     */
    private void deleteShip(String regNumber) throws IOException {
        boolean deleted = manager.deleteAidShip(regNumber);

        if (deleted) {
            print("Ship with registration number " + regNumber + " was deleted.");
        } else {
            print("No ship found with registration number " + regNumber);
        }

    }

    private void print(String message){
        txtList.setText(message);
    }


// print all ships
    @FXML
    private void onList() {
        ArrayList<AidShip> ships = manager.getAidShipList();

        if (ships.isEmpty()) {
            printShipList("No ships found in the database.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (AidShip s : ships) {
            sb.append(s.toString()).append("\n");
        }

        print("");

        printShipList("AidShipCoordinator List has " + ships.size() + " responders\n");

        printShipList("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        printShipList(String.format(
                "|%-22s |%-22s |%-22s |%-22s |%-22s |%-22s |%-22s |%-22s\n",
                "Name", "Registration", "Tonnage", "Crew Size", "Current Port",
                "Aid Type", "Supplies", "Helipad"
        ));
        printShipList("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        printShipList(sb.toString());
    }

    private void printShipList(String message) {
        txtList.appendText(message);
    }
}
