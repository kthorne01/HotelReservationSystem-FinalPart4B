package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import ui.Driver;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private ReservationService reservationsService;
    private CustomerService customersService;

    public AdminResource(ReservationService reservationsService, CustomerService customersService) {
        this.reservationsService = reservationsService;
        this.customersService = customersService;
    }

    public Customer getCustomer(String customerEmail){
        return this.customersService.getCustomer(customerEmail);
    }

    public void addRoom(IRoom room){
        this.reservationsService.addRoom(room);
    }

    public Collection<IRoom> getAllRooms(){
        return this.reservationsService.getRooms();
    }

    public Collection<Customer> getAllCustomers(){
        return this.customersService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationsService.printAllReservation();
    }
}


