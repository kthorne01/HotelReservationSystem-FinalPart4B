package model;

import java.util.ArrayList;
import java.util.Date;

public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree(Date checkInDate, Date checkOutDate);
    public ArrayList<RoomDates> getBookedDates();
    public void setBookedDates(Date checkInDate, Date checkOutDate);
}
