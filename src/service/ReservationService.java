package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import ui.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public final class ReservationService {
    private Collection<Reservation> reservations;
    private Collection<IRoom> rooms;

    public ReservationService() {
        this.reservations = new ArrayList<>();
        this.rooms = new HashSet<>();
    }

    public void addRoom(IRoom room) {
        for (IRoom r: rooms){

            if (r.getRoomNumber().equalsIgnoreCase(room.getRoomNumber()))
                return;
        }
        this.rooms.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : this.rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        if (checker(room, checkInDate, checkOutDate)) {
            Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
            room.setBookedDates(checkInDate, checkOutDate);
            this.reservations.add(reservation);
            return reservation;
        }
        return null;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> freeRooms = new ArrayList<>();
        for (IRoom room : rooms) {
            if (room.isFree(checkInDate, checkOutDate)) {
                freeRooms.add(room);
            }
        }
        return freeRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : this.reservations) {
            if (reservation.getCustomer().equals(customer)) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    public void printAllReservation() {
        for (Reservation reservation : this.reservations) {
            printReservation(reservation);
        }
    }

    public Collection<IRoom> getRooms() {
        return rooms;
    }

    private boolean checker(IRoom room, Date checkInDate, Date checkOutDate) {
        return room.isFree(checkInDate, checkOutDate) && findRooms(checkInDate, checkOutDate).contains(room);
    }

    void printReservation(Reservation reservation){
        System.out.println(reservation);
    }

    public static ReservationService getInstance() {
        return new ReservationService();
    }
}
