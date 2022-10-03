package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import ui.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private ReservationService reservationsService;
    private CustomerService customersService;

    public HotelResource(ReservationService reservationsService, CustomerService customersService) {
        this.reservationsService = reservationsService;
        this.customersService = customersService;
    }

    public Customer getCustomer(String email) {
        return this.customersService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        this.customersService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return this.reservationsService.getARoom(roomNumber);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return this.reservationsService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservation(String customerEmail) {
        return this.reservationsService.getCustomersReservation(this.customersService.getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return this.reservationsService.findRooms(checkInDate, checkOutDate);
    }
}
