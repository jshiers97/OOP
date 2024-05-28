package org.example;


public class Main {
    public static void main(String[] args) throws ParkingFullException, InvalidVehicleNumberException {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(3, 5);
        Vehicle test1=new Vehicle("10", VehicleSize.TWOWHEELER);
        Ticket ticket=parkingLot.park(test1);
        System.out.println(ticket);
        int cost1=parkingLot.unPark(ticket, new TwoWheelerWeekendChargeStrategy());
        System.out.println(cost1);
    }
}