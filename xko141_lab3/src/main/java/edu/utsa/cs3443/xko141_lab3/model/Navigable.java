package edu.utsa.cs3443.xko141_lab3.model;

public interface Navigable {
    void navigateTo(String location);
    void setCurrentSpeed(double speed);
    double getCurrentSpeed();
}




//Interface: Navigable: Represents all vehicles that can move/navigate, and defined as having:
//a navigateTo() method, which takes a location representing the ships destination (String) as a parameter and does not return anything. Each class that implements this interface must provide its own implementation. Since this is a simulation, an implementation could:
//Print a message showing the responder’s movement toward the destination.
//Update the responder’s current speed to reflect that it is in motion
//Optionally update other relevant attributes depending on the responder type (for example, a helicopter could also change its altitude).

//a setCurrentSpeed() method, which takes a speed (double) as a parameter and does not return anything.
//a getCurrentSpeed() method, which takes no parameters and returns the current speed (double).