package controller;

import model.Customer;
import service.DataService;
import java.util.List;

public class CustomerController {
    private DataService dataService;

    public CustomerController() {
        this.dataService = DataService.getInstance();
    }

    public void createCustomer(String name, String phone, String address, String email) {
        Customer customer = new Customer(0, name, phone, address, email);
        dataService.addCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return dataService.getAllCustomers();
    }

    public Customer getCustomerById(int id) {
        for (Customer customer : dataService.getAllCustomers()) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
}
