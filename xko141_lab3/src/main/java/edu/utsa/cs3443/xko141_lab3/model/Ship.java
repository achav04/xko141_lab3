package edu.utsa.cs3443.xko141_lab3.model;

public abstract class Ship implements Navigable {
    // attributes
    protected String name;
    protected String registrationNumber;
    protected double tonnage;
    protected int crewSize;
    protected double currentSpeed;
    protected String currentPort;

    // constructor
    public Ship(String name, String registrationNumber, double tonnage, int crewSize, double currentSpeed, String currentPort){
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.tonnage = tonnage;
        this.crewSize = crewSize;
        this.currentSpeed = currentSpeed;
        this.currentPort = currentPort;
    }

    // set dock and set speed at 0 since docked
    public void dock(String port){
        this.currentPort = port;
        this.currentSpeed = 0;
    }

    // print where ship is navigating to
    public void navigateTo(String location) {
        System.out.println(name + " is navigating to " + location);
        setCurrentSpeed(30);
    }

//common attributes for ships toString
    public String toString() {
        return "[name=" + name + ", registrationNumber=" + registrationNumber + ", tonnage=" + tonnage + ", crewSize=" + crewSize +
                ", currentSpeed=" + currentSpeed + ", currentPort=" + currentPort;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public double getTonnage() {
        return tonnage;
    }

    public void setTonnage(double tonnage) {
        this.tonnage = tonnage;
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    @Override
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    @Override
    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public String getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(String currentPort) {
        this.currentPort = currentPort;
    }
}






//Abstract Class: Ship: Represents a general ship (marine-based emergency unit), implements the Navigable interface and define as having:
//Attributes: name (String), registration number (String), tonnage (double), crew size (int), current speed (double), and current port (String).
//a constructor that initializes all instance variables, and any other constructors as needed.
//a dock() method, which takes a port as an attribute to update the current port, and updates the current speed.
//a toString() method to return all object information as a String.
//getters and setters for all variables.