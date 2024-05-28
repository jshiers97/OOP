package org.example;

public class Slot {
    private int slotNumber;
    private boolean isEmpty;
    private Vehicle parkVehicle;

    public Slot(int slotNumber){
        this.slotNumber=slotNumber;
        isEmpty=true;
    }
    public int getSlotNumber(){
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber){
        this.slotNumber=slotNumber;
    }

    public void setEmpty(boolean isEmpty){
        this.isEmpty=isEmpty;
    }
    public Vehicle getParkVehicle(){
        return parkVehicle;
    }

    public void vacateSlot(){
        parkVehicle=null;
        this.isEmpty=true;
    }

    public void occupySlot(Vehicle parkVehicle) {
        this.parkVehicle = parkVehicle;
        this.isEmpty = false;
    }
    public boolean isVacant(){
        return isEmpty;
    }
}
