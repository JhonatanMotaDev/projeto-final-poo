package view;

import controller.CustomerController;
import controller.DriverController;
import controller.OrderController;
import controller.RestaurantController;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Tela de relatórios do sistema
 * Design moderno e clean
 */
public class ReportFrame extends JFrame {
    private static final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private static final Color SECONDARY_COLOR = new Color(241, 245, 249);
    private static final Color TEXT_COLOR = new Color(15, 23, 42);
    private static final Color TABLE_HEADER_BG = new Color(241, 245, 249);
    
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
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(SECONDARY_COLOR);

        // Header
        JPanel header = createHeader("Relatórios 📊", "Visualize relatórios e estatísticas do sistema");
        add(header, BorderLayout.NORTH);

        // Painel de botões
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.CENTER);
    }

    private JPanel createHeader(String title, String subtitle) {
        JPanel header = new JPanel(new BorderLayout(15, 10));
        header.setBackground(SECONDARY_COLOR);
        header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(SECONDARY_COLOR);
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(TEXT_COLOR);
        
        JLabel lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(71, 85, 105));
        
        titlePanel.add(lblTitle, BorderLayout.NORTH);
        titlePanel.add(lblSubtitle, BorderLayout.SOUTH);
        
        header.add(titlePanel, BorderLayout.CENTER);
        
        return header;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(SECONDARY_COLOR);
        
        // Painel de botões em card
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(SECONDARY_COLOR);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        buttonPanel.add(createButton("Clientes 👥", PRIMARY_COLOR));
        buttonPanel.add(createButton("Entregadores 🛵", PRIMARY_COLOR));
        buttonPanel.add(createButton("Restaurantes 🍽️", PRIMARY_COLOR));
        buttonPanel.add(createButton("Pedidos 📦", PRIMARY_COLOR));
        buttonPanel.add(createButton("Estatísticas 📈", PRIMARY_COLOR));

        // Tabela em card
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(SECONDARY_COLOR);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblTableTitle = new JLabel("Dados");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTableTitle.setForeground(TEXT_COLOR);
        tablePanel.add(lblTableTitle, BorderLayout.NORTH);
        
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = createTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(SECONDARY_COLOR);
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        
        return panel;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(140, 40));
        
        if (text.contains("Clientes")) {
            button.addActionListener(e -> showCustomersReport());
        } else if (text.contains("Entregadores")) {
            button.addActionListener(e -> showDriversReport());
        } else if (text.contains("Restaurantes")) {
            button.addActionListener(e -> showRestaurantsReport());
        } else if (text.contains("Pedidos")) {
            button.addActionListener(e -> showOrdersReport());
        } else if (text.contains("Estatísticas")) {
            button.addActionListener(e -> showStatistics());
        }
        
        return button;
    }

    private JTable createTable(DefaultTableModel model) {
        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                comp.setBackground(row % 2 == 0 ? Color.WHITE : new Color(249, 250, 251));
                return comp;
            }
        };
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(TABLE_HEADER_BG);
        table.getTableHeader().setForeground(TEXT_COLOR);
        return table;
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
                driver.isAvailable() ? "Disponível ✅" : "Ocupado 🚫"
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
