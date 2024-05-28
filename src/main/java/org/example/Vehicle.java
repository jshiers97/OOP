package org.example;

public class Vehicle {
    private String vehicleNumber;
    private VehicleSize vehicleSize;

    public Vehicle(String vehicleNumber, VehicleSize vehicleSize){
        this.vehicleNumber=vehicleNumber;
        this.vehicleSize=vehicleSize;
    }

    public VehicleSize getVehicleSize(){
        return this.vehicleSize;
    }
    public String getVehicleNumber(){
        return this.vehicleNumber;
    }

    public void setVehicleSize(VehicleSize v1){
        this.vehicleSize=v1;
    }


}
