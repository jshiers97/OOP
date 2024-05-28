package org.example;

public class FourWheelerWeekDayChargeStrategy implements ParkingChargeStrategy{
    @Override
    public int getCharge(int parkHours){
        if(parkHours < 1){
            return 20;
        }
        else{
            return parkHours * 20;
        }
    }
}
