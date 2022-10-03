package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private String firstName, lastName, email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        //check if email match the regex xxx@domain.com
        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9]+.com(.[a-zA-Z0-9]+)?", email))
            throw new IllegalArgumentException("Invalid email");
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s", this.firstName, this.lastName, this.email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
