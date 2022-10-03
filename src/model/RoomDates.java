package model;

import java.util.Date;

public class RoomDates {

    private Date checkInDate, checkOutDate;

    public RoomDates(Date checkIn, Date checkOut){
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
    }

    //to get check in date
    public Date getCheckInDate() {
        return checkInDate;
    }
    //to get check out date
    public Date getCheckOutDate() {
        return checkOutDate;
    }
}
