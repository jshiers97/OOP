package org.example;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class ParkingLot implements Parking{
    private static ParkingLot parkingLot;
    private final List<Slot> twoWheelerSlots;
    private final List<Slot> fourWheelerSlots;

    private ParkingLot(){
        this.twoWheelerSlots=new ArrayList<>();
        this.fourWheelerSlots=new ArrayList<>();
    }

    public static ParkingLot getParkingLot() {
        if (parkingLot == null)
            parkingLot = new ParkingLot();
        return parkingLot;
    }
    public boolean initializeParkingSlots(int numberOfTwoWheelerSlots, int numberOfFourWheelerSlots){
        for(int i=0; i<numberOfTwoWheelerSlots; i++){
            twoWheelerSlots.add(new Slot(i));
        }
        for(int i=0; i<numberOfFourWheelerSlots; i++){
            fourWheelerSlots.add(new Slot(i));
        }
        return true;
    }

    public Ticket park(Vehicle vehicle) throws ParkingFullException {
        Slot nextAvailable;
        if(vehicle.getVehicleSize().equals(VehicleSize.TWOWHEELER)){
            nextAvailable=getNextAvailbleTwoWheelerSlot();
        }
        else{
            nextAvailable=getNextAvailbleFourWheelerSlot();
        }
        nextAvailable.occupySlot(vehicle);
        Ticket ticket=new Ticket(nextAvailable.getSlotNumber(), vehicle.getVehicleNumber(), vehicle.getVehicleSize(), new Date());
        return ticket;
    }
    private Slot getNextAvailbleTwoWheelerSlot() throws ParkingFullException{
        for(Slot slot: twoWheelerSlots){
            if(slot.isVacant()){
                return slot;
            }
        }
        throw new ParkingFullException("No Empty Slot Available");
    }

    private Slot getNextAvailbleFourWheelerSlot() throws ParkingFullException{
        for(Slot slot: fourWheelerSlots){
            if(slot.isVacant()){
                return slot;
            }
        }
        throw new ParkingFullException("No Empty Slot Available");
    }

    public int unPark(Ticket ticket, ParkingChargeStrategy parkingChargeStrategy) throws InvalidVehicleNumberException{
        int costByHours=0;
        Slot slot;
        try{
            if(ticket.getVehicleSize().equals(VehicleSize.TWOWHEELER)){
                slot=getTwoWheelerSlotByVehicleNumber(ticket.getVehicleNumber());
            }
            else{
                slot=getFourWheelerSlotByVehicleNumber(ticket.getVehicleNumber());
            }
            slot.vacateSlot();
            int hours=getHoursParked(ticket.getDate(), new Date());
            costByHours=parkingChargeStrategy.getCharge(hours);
        }
        catch(InvalidVehicleNumberException invalidVehicleNumber){
            System.out.println(invalidVehicleNumber.getMessage());
            throw invalidVehicleNumber;
        }
        return costByHours;
    }

    private int getHoursParked(Date start, Date end){
        long secs = (end.getTime() - start.getTime()) / 1000;
        int hours = (int) (secs / 3600);
        return hours;
    }

    private Slot getTwoWheelerSlotByVehicleNumber(String vehicleNumber) throws InvalidVehicleNumberException{
        for (Slot slot : twoWheelerSlots) {
            Vehicle vehicle = slot.getParkVehicle();
            if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return slot;
            }
        }
        throw new InvalidVehicleNumberException("Two wheeler with registration " + vehicleNumber + " not found.");
    }

    private Slot getFourWheelerSlotByVehicleNumber(String vehicleNumber) throws InvalidVehicleNumberException{
        for (Slot slot : fourWheelerSlots) {
            Vehicle vehicle = slot.getParkVehicle();
            if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return slot;
            }
        }
        throw new InvalidVehicleNumberException("Four wheeler with registration " + vehicleNumber + " not found.");
    }
}
