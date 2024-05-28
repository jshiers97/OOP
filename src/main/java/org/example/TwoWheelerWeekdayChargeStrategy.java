package org.example;

public class TwoWheelerWeekdayChargeStrategy implements ParkingChargeStrategy{
    @Override
    public int getCharge(int parkHours){
        if(parkHours < 1){
            return 10;
        }
        else{
            return parkHours * 10;
        }
    }
}
