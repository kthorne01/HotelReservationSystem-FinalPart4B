package ui;

import api.AdminResource;
import api.HotelResource;
import model.*;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Driver {
    private static final String ADMIN_NAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    public static void main(String[] args) throws ParseException {
        Scanner input = new Scanner(System.in);

        CustomerService customerService = new CustomerService();
        ReservationService reservationService = new ReservationService();
        AdminResource adminResource = new AdminResource(reservationService, customerService);
        HotelResource hotelResource = new HotelResource(reservationService, customerService);

        while (true) {
            MainMenu.mainMenu();
            int choice = checkInput(input);
            if (choice == 1) {
                System.out.print("Enter Your Email: eg. name@domain.com ");
                String email = input.nextLine();
                if (adminResource.getAllCustomers().contains(hotelResource.getCustomer(email))) {

                    //addedd
                    System.out.print("Enter check in date (dd/MM/yyyy): ");
                    String checkInDate = input.nextLine();
                    System.out.print("Enter check out date (dd/MM/yyyy): ");
                    String checkOutDate = input.nextLine();
                    Collection<IRoom> freeRooms = hotelResource.findARoom(new SimpleDateFormat("dd/MM/yyyy").parse(checkInDate),
                            new SimpleDateFormat("dd/MM/yyyy").parse(checkOutDate));

                    if (freeRooms.size() > 0) {
                        System.out.println("The following rooms are available on given check-in and check-out dates:");
                        for (IRoom room: freeRooms){
                            System.out.println("Room # " + room.getRoomNumber());
                        }
                        //addedd finished
                        System.out.print("Enter Room number: ");
                        String roomNumber = input.nextLine();
                        if (hotelResource.getRoom(roomNumber) != null) {
                            //show booked dates
                            //if(hotelResource.getRoom(roomNumber).getBookedDates().size() > 0) {
                            //System.out.println("The room is already booked on following days:");
                            //}
                            //for (RoomDates dates: hotelResource.getRoom(roomNumber).getBookedDates()){
                            //System.out.println(dates.getCheckInDate() + " - " + dates.getCheckOutDate());
                            //}

                            //System.out.print("Enter check in date (dd/MM/yyyy): ");
                            //String checkInDate = input.nextLine();
                            //System.out.print("Enter check out date (dd/MM/yyyy): ");
                            //String checkOutDate = input.nextLine();
                            Reservation reservation = hotelResource.reserveARoom(
                                    hotelResource.getCustomer(email),
                                    hotelResource.getRoom(roomNumber),
                                    new SimpleDateFormat("dd/MM/yyyy").parse(checkInDate),
                                    new SimpleDateFormat("dd/MM/yyyy").parse(checkOutDate)
                            );
                            if (reservation != null) {
                                System.out.println("Reservation Successful");
                            } else {
                                System.out.println("Room not free");
                                System.out.println("Reservation Failed");
                            }
                        } else System.out.println("Room not found");
                    }
                    else{
                        System.out.println("Sorry !! No room available on the given date");
                    }

                } else System.out.println("Customer not found");

            } else if (choice == 2) {
                System.out.print("Enter Email: eg. name@domain.com ");
                String email = input.nextLine();
                Collection<Reservation> list = hotelResource.getCustomersReservation(email);
                for (Reservation reservation : list) {
                    System.out.println(reservation);
                }
            } else if (choice == 3) {
                System.out.print("Enter First Name: ");
                String fName = input.nextLine();
                System.out.print("Enter Last Name: ");
                String lName = input.nextLine();
                System.out.print("Enter Email Address: eg. name@domain.com ");
                String email = input.nextLine();
                hotelResource.createACustomer(email, fName, lName);
            } else if (choice == 4) {
                System.out.print("Enter name: ");
                String name = input.nextLine();
                System.out.print("Enter password: ");
                String password = input.nextLine();
                if (!name.equalsIgnoreCase(ADMIN_NAME) && password.equals(ADMIN_PASSWORD)) {
                    System.out.println("Invalid credentials");
                } else {
                    loop:
                    while (true) {
                        AdminMenu.adminMenu();
                        System.out.print("Choice > ");
                        int choiceAdmin = checkInput(input);
                        switch (choiceAdmin) {
                            case 1: {
                                for (Customer customer : adminResource.getAllCustomers())
                                    System.out.println(customer);
                                break;
                            }
                            case 2: {
                                for (IRoom room : adminResource.getAllRooms())
                                    System.out.println(room);
                                break;
                            }
                            case 3:
                                adminResource.displayAllReservations();
                                break;
                            case 4: {
                                System.out.print("Room Number: ");
                                String roomNumber = input.nextLine();
                                if (hotelResource.getRoom(roomNumber) == null){
                                    System.out.print("Room Price: ");
                                    Double roomPrice = Double.parseDouble(input.nextLine());
                                    System.out.print("Room Type: ");
                                    String roomTypeS = input.nextLine();
                                    RoomType type = null;
                                    for (RoomType roomType : RoomType.values()) {
                                        if (roomType.toString().equalsIgnoreCase(roomTypeS)) {
                                            type = roomType;
                                            break;
                                        }
                                    }
                                    IRoom room = new Room(roomNumber, roomPrice, type);
                                    System.out.println("Room created");
                                    adminResource.addRoom(room);
                                }else
                                    System.out.println("Room with given Number already Exist");
                                break;
                            }
                            case 5:
                                break loop;
                            default:
                                System.out.println("Invalid choice");
                        }
                    }
                }
            } else if (choice == 5)
                break;
            else
                System.out.println("Wrong Choice :(");
        }
    }

    private static int checkInput(Scanner input) {
        int choiceAdmin;
        while (true) {
            System.out.print("Choice > ");
            String s = input.nextLine();
            if (isInteger(s)) {
                choiceAdmin = Integer.parseInt(s);
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        return choiceAdmin;
    }

    //check if string can convert to integer or not
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
