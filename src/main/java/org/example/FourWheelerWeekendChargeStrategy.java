package org.example;

public class FourWheelerWeekendChargeStrategy implements ParkingChargeStrategy{
    @Override
    public int getCharge(int parkHours){
        if(parkHours < 1){
            return 30;
        }
        else{
            return parkHours * 30;
        }
    }
}
