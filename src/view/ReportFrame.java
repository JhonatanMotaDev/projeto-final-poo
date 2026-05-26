package view;

import controller.CustomerController;
import controller.DriverController;
import controller.OrderController;
import controller.RestaurantController;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Tela de relatórios do sistema
 */
public class ReportFrame extends JFrame {
    private CustomerController customerController;
    private DriverController driverController;
    private RestaurantController restaurantController;
    private OrderController orderController;
    
    private JTable table;
    private DefaultTableModel tableModel;

    public ReportFrame() {
        customerController = new CustomerController();
        driverController = new DriverController();
        restaurantController = new RestaurantController();
        orderController = new OrderController();
        
        setTitle("Relatórios");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Selecione o Relatório"));
        
        JButton btnCustomers = new JButton("Clientes");
        btnCustomers.addActionListener(e -> showCustomersReport());
        
        JButton btnDrivers = new JButton("Entregadores");
        btnDrivers.addActionListener(e -> showDriversReport());
        
        JButton btnRestaurants = new JButton("Restaurantes");
        btnRestaurants.addActionListener(e -> showRestaurantsReport());
        
        JButton btnOrders = new JButton("Pedidos");
        btnOrders.addActionListener(e -> showOrdersReport());
        
        JButton btnStatistics = new JButton("Estatísticas");
        btnStatistics.addActionListener(e -> showStatistics());
        
        buttonPanel.add(btnCustomers);
        buttonPanel.add(btnDrivers);
        buttonPanel.add(btnRestaurants);
        buttonPanel.add(btnOrders);
        buttonPanel.add(btnStatistics);

        // Tabela
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void showCustomersReport() {
        String[] columns = {"ID", "Nome", "Telefone", "Email", "Endereço"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0);
        
        for (Customer customer : customerController.getAllCustomers()) {
            tableModel.addRow(new Object[]{
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress()
            });
        }
    }

    private void showDriversReport() {
        String[] columns = {"ID", "Nome", "Telefone", "Veículo", "Placa", "Status"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0);
        
        for (DeliveryDriver driver : driverController.getAllDrivers()) {
            tableModel.addRow(new Object[]{
                driver.getId(),
                driver.getName(),
                driver.getPhone(),
                driver.getVehicleType(),
                driver.getLicensePlate(),
                driver.isAvailable() ? "Disponível" : "Ocupado"
            });
        }
    }

    private void showRestaurantsReport() {
        String[] columns = {"ID", "Nome", "Categoria", "Telefone", "Produtos"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0);
        
        for (Restaurant restaurant : restaurantController.getAllRestaurants()) {
            tableModel.addRow(new Object[]{
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getCategory(),
                restaurant.getPhone(),
                restaurant.getProducts().size()
            });
        }
    }

    private void showOrdersReport() {
        String[] columns = {"ID", "Cliente", "Restaurante", "Produtos", "Total", "Status"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0);
        
        for (Order order : orderController.getAllOrders()) {
            tableModel.addRow(new Object[]{
                order.getId(),
                order.getCustomer().getName(),
                order.getRestaurant().getName(),
                order.getProducts().size(),
                String.format("R$ %.2f", order.getTotalValue()),
                order.getStatus().getDescription()
            });
        }
    }

    private void showStatistics() {
        int totalCustomers = customerController.getAllCustomers().size();
        int totalDrivers = driverController.getAllDrivers().size();
        int totalRestaurants = restaurantController.getAllRestaurants().size();
        int totalOrders = orderController.getAllOrders().size();
        
        double totalRevenue = 0.0;
        for (Order order : orderController.getAllOrders()) {
            totalRevenue += order.getTotalValue();
        }

        String message = String.format(
            "ESTATÍSTICAS DO SISTEMA\n\n" +
            "Total de Clientes: %d\n" +
            "Total de Entregadores: %d\n" +
            "Total de Restaurantes: %d\n" +
            "Total de Pedidos: %d\n" +
            "Receita Total: R$ %.2f\n" +
            "Ticket Médio: R$ %.2f",
            totalCustomers, totalDrivers, totalRestaurants, totalOrders,
            totalRevenue, totalOrders > 0 ? totalRevenue / totalOrders : 0.0
        );

        JOptionPane.showMessageDialog(this, message, "Estatísticas", JOptionPane.INFORMATION_MESSAGE);
    }
}
