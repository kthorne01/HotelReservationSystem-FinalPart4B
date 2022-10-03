package model;

import java.util.ArrayList;
import java.util.Date;

public class Room implements IRoom {
    private String roomNumber;
    private Double roomPrice;
    private RoomType roomType;
    private ArrayList<RoomDates> bookedDates;

    public Room(String roomNumber, Double roomPrice, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        bookedDates = new ArrayList<RoomDates>();
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public void setBookedDates(Date checkInDate, Date checkOutDate) {
        RoomDates bookDate = new RoomDates(checkInDate, checkOutDate);
        this.bookedDates.add(bookDate);
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return this.roomType;
    }

    @Override
    public ArrayList<RoomDates> getBookedDates() {
        return bookedDates;
    }

    @Override
    public boolean isFree(Date checkInDate, Date checkOutDate) {
        for (int i = 0; i < bookedDates.size(); i++){
            //3 conditions
            //either in between (partially inside the booked dates) or containing the already booked
            // dates (bounding the booked dates from both sides)
            if ((bookedDates.get(i).getCheckInDate().compareTo(checkInDate) <= 0 && bookedDates.get(i).getCheckOutDate().compareTo(checkInDate) >= 0)
                || (bookedDates.get(i).getCheckInDate().compareTo(checkOutDate) <= 0 && bookedDates.get(i).getCheckOutDate().compareTo(checkOutDate) >= 0)
                || (bookedDates.get(i).getCheckInDate().compareTo(checkInDate) >= 0 && bookedDates.get(i).getCheckOutDate().compareTo(checkOutDate) <= 0)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-10s", this.roomNumber, this.roomPrice, this.roomType);
    }
}
