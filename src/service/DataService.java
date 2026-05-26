package service;

import model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DataService - Gerencia os dados da aplicação
 * Simula um banco de dados usando ArrayLists
 */
public class DataService {
    private static DataService instance;
    
    // ArrayLists para armazenamento
    private List<Customer> customers;
    private List<DeliveryDriver> drivers;
    private List<Administrator> administrators;
    private List<Restaurant> restaurants;
    private List<Order> orders;
    
    // Contadores para IDs
    private int customerIdCounter = 1;
    private int driverIdCounter = 1;
    private int adminIdCounter = 1;
    private int restaurantIdCounter = 1;
    private int productIdCounter = 1;
    private int orderIdCounter = 1;
    private int paymentIdCounter = 1;

    // Singleton pattern
    private DataService() {
        customers = new ArrayList<>();
        drivers = new ArrayList<>();
        administrators = new ArrayList<>();
        restaurants = new ArrayList<>();
        orders = new ArrayList<>();
        initializeSampleData();
    }

    public static DataService getInstance() {
        if (instance == null) {
            instance = new DataService();
        }
        return instance;
    }

    // Inicializa dados de exemplo
    private void initializeSampleData() {
        // Adiciona um administrador padrão
        Administrator admin = new Administrator(adminIdCounter++, "Admin", "11999999999", 
                "Rua Admin, 123", "admin", "admin123");
        administrators.add(admin);
    }

    // Métodos para Customer
    public void addCustomer(Customer customer) {
        customer.setId(customerIdCounter++);
        customers.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    // Métodos para DeliveryDriver
    public void addDriver(DeliveryDriver driver) {
        driver.setId(driverIdCounter++);
        drivers.add(driver);
    }

    public List<DeliveryDriver> getAllDrivers() {
        return drivers;
    }

    public List<DeliveryDriver> getAvailableDrivers() {
        List<DeliveryDriver> available = new ArrayList<>();
        for (DeliveryDriver driver : drivers) {
            if (driver.isAvailable()) {
                available.add(driver);
            }
        }
        return available;
    }

    // Métodos para Administrator
    public void addAdministrator(Administrator administrator) {
        administrator.setId(adminIdCounter++);
        administrators.add(administrator);
    }

    public List<Administrator> getAllAdministrators() {
        return administrators;
    }

    // Métodos para Restaurant
    public void addRestaurant(Restaurant restaurant) {
        restaurant.setId(restaurantIdCounter++);
        restaurants.add(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    // Métodos para Order
    public void addOrder(Order order) {
        order.setId(orderIdCounter++);
        orders.add(order);
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    // Métodos para gerar IDs
    public int getNextProductId() {
        return productIdCounter++;
    }

    public int getNextPaymentId() {
        return paymentIdCounter++;
    }

    // Método para limpar todos os dados
    public void clearAllData() {
        customers.clear();
        drivers.clear();
        restaurants.clear();
        orders.clear();
        customerIdCounter = 1;
        driverIdCounter = 1;
        restaurantIdCounter = 1;
        orderIdCounter = 1;
        productIdCounter = 1;
        paymentIdCounter = 1;
    }
}
