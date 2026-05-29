package view;

import controller.CustomerController;
import controller.DriverController;
import controller.OrderController;
import controller.RestaurantController;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import model.*;

public class ReportFrame extends JFrame {
    private static final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private static final Color SECONDARY_COLOR = new Color(241, 245, 249);
    private static final Color TEXT_COLOR = new Color(15, 23, 42);
    private static final Color TABLE_HEADER_BG = new Color(241, 245, 249);
    
    private CustomerController customerController;
    private DriverController driverController;
    private RestaurantController restaurantController;
    private OrderController orderController;
    
    private JPanel dataPanel;
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

        JPanel header = createHeader("Relatórios", "Visualize relatórios e estatísticas do sistema");
        add(header, BorderLayout.NORTH);

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
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(SECONDARY_COLOR);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        buttonPanel.add(createButton("Clientes", PRIMARY_COLOR));
        buttonPanel.add(createButton("Entregadores", PRIMARY_COLOR));
        buttonPanel.add(createButton("Restaurantes", PRIMARY_COLOR));
        buttonPanel.add(createButton("Pedidos", PRIMARY_COLOR));
        buttonPanel.add(createButton("Estatísticas", PRIMARY_COLOR));

        dataPanel = new JPanel(new BorderLayout());
        dataPanel.setBackground(Color.WHITE);
        dataPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel lblTableTitle = new JLabel("Dados");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTableTitle.setForeground(TEXT_COLOR);
        dataPanel.add(lblTableTitle, BorderLayout.NORTH);
        
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = createTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        scrollPane.setBackground(Color.WHITE);
        
        dataPanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(dataPanel, BorderLayout.CENTER);
        
        return panel;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
        
        if (text.equals("Clientes")) {
            button.addActionListener(e -> showCustomersReport());
        } else if (text.equals("Entregadores")) {
            button.addActionListener(e -> showDriversReport());
        } else if (text.equals("Restaurantes")) {
            button.addActionListener(e -> showRestaurantsReport());
        } else if (text.equals("Pedidos")) {
            button.addActionListener(e -> showOrdersReport());
        } else if (text.equals("Estatísticas")) {
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
        String[] columns = {"ID", "Nome", "Telefone", "Veiculo", "Placa", "Status"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0);
        
        for (DeliveryDriver driver : driverController.getAllDrivers()) {
            tableModel.addRow(new Object[]{
                driver.getId(),
                driver.getName(),
                driver.getPhone(),
                driver.getVehicleType(),
                driver.getLicensePlate(),
                driver.isAvailable() ? "Disponivel" : "Ocupado"
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

        String[] columns = {"Metrica", "Valor"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0);
        
        tableModel.addRow(new Object[]{"Total de Clientes", totalCustomers});
        tableModel.addRow(new Object[]{"Total de Entregadores", totalDrivers});
        tableModel.addRow(new Object[]{"Total de Restaurantes", totalRestaurants});
        tableModel.addRow(new Object[]{"Total de Pedidos", totalOrders});
        tableModel.addRow(new Object[]{"Receita Total", String.format("R$ %.2f", totalRevenue)});
        tableModel.addRow(new Object[]{"Ticket Medio", String.format("R$ %.2f", totalOrders > 0 ? totalRevenue / totalOrders : 0.0)});
    }
}
