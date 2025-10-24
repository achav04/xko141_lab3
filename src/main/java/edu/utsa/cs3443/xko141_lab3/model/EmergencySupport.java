package edu.utsa.cs3443.xko141_lab3.model;

public interface EmergencySupport {
    void deployAid();
    String getEmergencyReadinessReport();
}




//Interface: EmergencySupport: Represents any unit capable of providing aid, and defined as having:
//a deployAid(), which takes no parameters and does not return anything. Each class that implements this interface must provide its own implementation. Each implementation should print a message showing how aid is deployed, and update attributes such as supplies or medicine stock. Examples:
//An AidShip might unload food, water, and medical supplies.
//A RescueHelicopter might airlift survivors or drop medical kits.
//A MobileClinicTruck might provide on-site medical treatment.
//a getEmergencyReadinessReport(), which takes no parameters and returns a String.
