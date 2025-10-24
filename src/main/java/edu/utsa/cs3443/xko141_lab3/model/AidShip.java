package edu.utsa.cs3443.xko141_lab3.model;

public class AidShip extends Ship implements EmergencySupport {
    // attributes
    private String aidType;
    private int suppliesOnBoard;
    private boolean hasHelipad;


    // constructor
    public AidShip(String name, String registrationNumber, double tonnage, int crewSize, String currentPort, String aidType, int suppliesOnBoard, boolean hasHelipad) {
            super(name, registrationNumber, tonnage, crewSize, 0, currentPort);
            this.aidType = aidType;
            this.suppliesOnBoard = suppliesOnBoard;
            this.hasHelipad = hasHelipad;
    }

    // use dock from ship and unload supplies
    public void dock(String port){
        super.dock(port);
        unloadSupplies();
    }

    // set supplies on board to 0
    private void  unloadSupplies(){
        suppliesOnBoard = 0;
    }

    // print out info of ship deploying aid and set supplies to 0 after
    public void deployAid() {
        System.out.println("Aid Ship " + name + " is deploying " + suppliesOnBoard + " units of " + aidType + " aid.");
        unloadSupplies();
    }

    // return readiness report
    public String getEmergencyReadinessReport() {
        return "Aid Ship Emergency Report:\nName: " + name + "\nAid Type: " + aidType + "\nSupplies on Board: " + suppliesOnBoard + "\nHelipad: " +
                (hasHelipad ? "Available" : "Not Available");
    }

    // toString prints aid ship attributes
    public String toString() {
        return "Aid Ship " + super.toString() + ", aidType=" + aidType + ", suppliesOnBoard=" + suppliesOnBoard + ", hasHelipad=" + hasHelipad + " ]";
    }

    //getters and setters
    public String getAidType() {
        return aidType;
    }

    public void setAidType(String aidType) {
        this.aidType = aidType;
    }

    public int getSuppliesOnBoard() {
        return suppliesOnBoard;
    }

    public void setSuppliesOnBoard(int suppliesOnBoard) {
        this.suppliesOnBoard = suppliesOnBoard;
    }

    public boolean isHasHelipad() {
        return hasHelipad;
    }

    public void setHasHelipad(boolean hasHelipad) {
        this.hasHelipad = hasHelipad;
    }

}


//Concrete Class: AidShip: Represents an aid ship, extends the Ship class, implements the EmergencySupport interface, and defined as having:
//Attributes: aidType (String), suppliesOnBoard (int), hasHelipad (boolean).
//a constructor that initializes all instance variables, and any other constructors as needed.
//a dock() methods that extends the behavior of the dock() method in the parent class by unloading the supplies (see next bullet point).
//a private unloadSupplies() method, which takes no parameters and does not return anything. The method updates the supplies on board.
//a toString() method to return all object information as a String.
//getters and setters for all variables.