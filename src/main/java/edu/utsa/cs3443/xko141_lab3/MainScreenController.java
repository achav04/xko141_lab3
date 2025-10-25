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
 *MainScreenController controls the main-screen fxml
 *
 */
public class MainScreenController {

    /**
     * declaration of attributes
     */
    @FXML private ImageView imageView;
    @FXML private Label appTitle;
    @FXML private RadioButton btnFind;
    @FXML private RadioButton btnDelete;
    @FXML private TextField txtRegistrationNum;
    @FXML private TextArea txtList;
    @FXML private ToggleGroup grouped;

    private AidShipManager manager;

    /**
     *initializes the important resources for the fxml file
     */
    @FXML
    public void initialize() throws IOException {
        manager = new AidShipManager();
        grouped = new ToggleGroup();
        btnFind.setToggleGroup(grouped);
        btnDelete.setToggleGroup(grouped);

        manager.loadAidShips("/edu/utsa/cs3443/xko141_lab3/data/aid_ships.csv");

        // loads logo
        try {
            Image logoImage = new Image(
                    getClass().getResourceAsStream("/edu/utsa/cs3443/xko141_lab3/images/logo.png")
            );
            imageView.setImage(logoImage);
        } catch (Exception e) {
            print("Logo image not found.");
        }
        //set text font
        txtList.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 12;");

        //print title
        appTitle.setText("The Global Emergency Response Organization");

    }

    /**
     *onGo button press takes registration number in textbox and checks what radio button is clicked to either find or delete ship
     */
    @FXML
    private void onGo() throws IOException {
        String regNumber = txtRegistrationNum.getText().trim();// sets text in box to registration num

        if (regNumber.isEmpty()) {//check if text was empty
            print("Please enter a registration number first.");
            return;
        }

        if (btnFind.isSelected()) {//check button selected
            findShip(regNumber);
        } else if (btnDelete.isSelected()) {
            deleteShip(regNumber);
        } else {
            print("Please select a button, Find or Delete");
        }
    }

    /**
     * Searches for a ship by registration number.
     */
    private void findShip(String regNumber) {
        AidShip ship = manager.findAidShip(regNumber); // calls find aidship method, finds the ship and sets it to ship
        if (ship != null) {
            print(//prints info from ship
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
     *Deletes ship based of registration number
     */
    private void deleteShip(String regNumber) throws IOException {
        boolean deleted = manager.deleteAidShip(regNumber);

        if (deleted) {
            print("Ship with registration number " + regNumber + " was deleted.");
        } else {
            print("No ship found with registration number " + regNumber);
        }

    }

    // sets new text to print for new buttons
    private void print(String message){
        txtList.setText(message);
    }


    /**
     * onList buttton click list all available ships in array
      */
    @FXML
    public void onList() {
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

    /** appends text for when looping through array adds to last string
     *
     */
    private void printShipList(String message) {
        txtList.appendText(message);
    }
}
