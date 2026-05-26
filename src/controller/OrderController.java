package controller;

import enums.OrderStatus;
import enums.PaymentType;
import model.*;
import service.DataService;
import java.util.List;

/**
 * Controller para gerenciar operações de Order
 */
public class OrderController {
    private DataService dataService;

    public OrderController() {
        this.dataService = DataService.getInstance();
    }

    public Order createOrder(Customer customer, Restaurant restaurant) {
        Order order = new Order(0, customer, restaurant);
        dataService.addOrder(order);
        return order;
    }

    public void addProductToOrder(Order order, Product product) {
        order.addProduct(product);
    }

    public void assignDriverToOrder(Order order, DeliveryDriver driver) {
        order.assignDriver(driver);
    }

    public void updateOrderStatus(Order order, OrderStatus status) {
        order.setStatus(status);
        
        // Implementa lógica da interface Deliverable
        if (status == OrderStatus.OUT_FOR_DELIVERY) {
            order.startDelivery();
        } else if (status == OrderStatus.DELIVERED) {
            order.finishDelivery();
        }
    }

    public void processPayment(Order order, PaymentType paymentType) {
        Payment payment = new Payment(dataService.getNextPaymentId(), paymentType, order.getTotalValue());
        payment.confirmPayment();
        order.setPayment(payment);
    }

    public List<Order> getAllOrders() {
        return dataService.getAllOrders();
    }

    public Order getOrderById(int id) {
        return dataService.getOrderById(id);
    }
}
