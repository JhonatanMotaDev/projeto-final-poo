package model;

import enums.OrderStatus;
import interfaces.Deliverable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Order - representa um pedido
 * Demonstra: Implementação de Interface, Associação entre classes
 */
public class Order implements Deliverable {
    private int id;
    // Associações: Order tem Customer, Restaurant, DeliveryDriver, Products e Payment
    private Customer customer;
    private Restaurant restaurant;
    private DeliveryDriver driver;
    private List<Product> products;
    private OrderStatus status;
    private double totalValue;
    private Payment payment;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;

    // Construtor
    public Order(int id, Customer customer, Restaurant restaurant) {
        this.id = id;
        this.customer = customer;
        this.restaurant = restaurant;
        this.products = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.totalValue = 0.0;
        this.orderDate = LocalDateTime.now();
    }

    // Implementação da interface Deliverable
    @Override
    public void startDelivery() {
        this.status = OrderStatus.OUT_FOR_DELIVERY;
        System.out.println("Pedido #" + id + " saiu para entrega!");
    }

    @Override
    public void finishDelivery() {
        this.status = OrderStatus.DELIVERED;
        this.deliveryDate = LocalDateTime.now();
        if (driver != null) {
            driver.setAvailable(true);
        }
        System.out.println("Pedido #" + id + " foi entregue!");
    }

    @Override
    public void updateStatus(String newStatus) {
        try {
            this.status = OrderStatus.valueOf(newStatus);
        } catch (IllegalArgumentException e) {
            System.out.println("Status inválido!");
        }
    }

    // Método para adicionar produto ao pedido
    public void addProduct(Product product) {
        this.products.add(product);
        calculateTotal();
    }

    // Método para calcular o total do pedido
    public void calculateTotal() {
        this.totalValue = 0.0;
        for (Product product : products) {
            this.totalValue += product.getPrice();
        }
    }

    // Método para atribuir entregador
    public void assignDriver(DeliveryDriver driver) {
        this.driver = driver;
        driver.setAvailable(false);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public DeliveryDriver getDriver() {
        return driver;
    }

    public void setDriver(DeliveryDriver driver) {
        this.driver = driver;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("Pedido #%d - %s - R$ %.2f - %s",
                id, customer.getName(), totalValue, status.getDescription());
    }
}
