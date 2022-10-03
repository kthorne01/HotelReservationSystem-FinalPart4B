package service;

import model.Customer;
import ui.Driver;

import java.util.ArrayList;
import java.util.Collection;

public final class CustomerService {
    private Collection<Customer> customers;

    public CustomerService() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
        System.out.println("Customer Added Successfully");
    }

    public Customer getCustomer(String customerEmail){
        for (Customer customer : customers) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return customers;
    }

    public static CustomerService getInstance() {
        return new CustomerService();
    }
}
