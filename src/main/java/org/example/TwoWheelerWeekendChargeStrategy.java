package org.example;

public class TwoWheelerWeekendChargeStrategy implements ParkingChargeStrategy{
    @Override
    public int getCharge(int parkHours){
        if(parkHours<1){
            return 15;
        }
        else{
            return 15 * parkHours;
        }
    }
}
