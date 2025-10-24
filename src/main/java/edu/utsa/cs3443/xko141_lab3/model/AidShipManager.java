package edu.utsa.cs3443.xko141_lab3.model;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages AidShip data loaded from a CSV file in the resources folder.
 * Supports loading, finding, deleting, and listing ships.
 *
 * Author: Andres Chavez
 * Course: CS3443
 * Lab 3 - Aid Ship Management System
 */
public class AidShipManager {

    private ArrayList<AidShip> aidShips = new ArrayList<>();


    public void loadAidShips(String resourcePath) throws IOException {
        aidShips.clear();

        InputStream input = getClass().getResourceAsStream(resourcePath);
        if (input == null) {
            throw new FileNotFoundException("Could not find resource: " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // skip the header
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 8) continue;

                AidShip ship = new AidShip(
                        parts[0].trim(),
                        parts[1].trim(),
                        Double.parseDouble(parts[2].trim()),
                        Integer.parseInt(parts[3].trim()),
                        parts[4].trim(),
                        parts[5].trim(),
                        Integer.parseInt(parts[6].trim()),
                        Boolean.parseBoolean(parts[7].trim())
                );
                aidShips.add(ship);
            }
        }
    }


    /**
     * Finds a ship by registration number.
     */
    public AidShip findAidShip(String regNum) {
        for (AidShip ship : aidShips) {
            if (ship.getRegistrationNumber().equalsIgnoreCase(regNum)) {
                return ship;
            }
        }
        return null;
    }

    /**
     * Deletes a ship by registration number.
     */
    public boolean deleteAidShip(String regNum) throws IOException {
        AidShip found = findAidShip(regNum);

        if (found != null) {
            aidShips.remove(found);
            return true;
        }
        return false;

    }




    /**
     * Returns all loaded ships.
     */
    public ArrayList<AidShip> getAidShipList() {
        return aidShips;
    }
}
