package view;

import controller.OrderController;
import enums.OrderStatus;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import model.Order;

public class DeliveryFrame extends JFrame {
    private static final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private static final Color SECONDARY_COLOR = new Color(241, 245, 249);
    private static final Color TEXT_COLOR = new Color(15, 23, 42);
    private static final Color WARNING_COLOR = new Color(251, 191, 36);
    private static final Color TABLE_HEADER_BG = new Color(241, 245, 249);
    
    private OrderController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<OrderStatus> cmbStatus;

    public DeliveryFrame() {
        controller = new OrderController();
        
        setTitle("Gerenciar Entregas");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(SECONDARY_COLOR);

        JPanel header = createHeader("Entregas", "Gerencie o status das entregas");
        add(header, BorderLayout.NORTH);

        JPanel topPanel = createControlPanel();
        add(topPanel, BorderLayout.CENTER);

        loadOrders();
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

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(SECONDARY_COLOR);
        
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        controlPanel.setBackground(SECONDARY_COLOR);
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        controlPanel.add(createLabel("Novo Status:"));
        cmbStatus = new JComboBox<>(OrderStatus.values());
        cmbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbStatus.setBackground(Color.WHITE);
        cmbStatus.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        controlPanel.add(cmbStatus);
        
        JButton btnUpdate = createButton("Atualizar Status", WARNING_COLOR);
        btnUpdate.addActionListener(e -> updateStatus());
        controlPanel.add(btnUpdate);
        
        JButton btnRefresh = createButton("Atualizar Lista", PRIMARY_COLOR);
        btnRefresh.addActionListener(e -> loadOrders());
        controlPanel.add(btnRefresh);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(SECONDARY_COLOR);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblTableTitle = new JLabel("Pedidos");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTableTitle.setForeground(TEXT_COLOR);
        tablePanel.add(lblTableTitle, BorderLayout.NORTH);
        
        String[] columns = {"ID", "Cliente", "Restaurante", "Entregador", "Total", "Status", "Pagamento"};
        tableModel = new DefaultTableModel(columns, 0) {
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

        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(TEXT_COLOR);
        return label;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(160, 35));
        return button;
    }

    private JTable createTable(DefaultTableModel model) {
        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                if (isCellSelected(row, column)) {
                    comp.setBackground(new Color(25, 118, 210));
                    comp.setForeground(Color.WHITE);
                } else {
                    comp.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 247, 250));
                    comp.setForeground(TEXT_COLOR);
                }
                if (comp instanceof JComponent) {
                    ((JComponent) comp).setBorder(BorderFactory.createEmptyBorder());
                }
                return comp;
            }
        };
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        table.setSelectionBackground(new Color(25, 118, 210));
        table.setSelectionForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(TABLE_HEADER_BG);
        table.getTableHeader().setForeground(TEXT_COLOR);
        return table;
    }

    private void updateStatus() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int orderId = (int) tableModel.getValueAt(selectedRow, 0);
        Order order = controller.getOrderById(orderId);
        OrderStatus newStatus = (OrderStatus) cmbStatus.getSelectedItem();

        if (order != null && newStatus != null) {
            controller.updateOrderStatus(order, newStatus);
            JOptionPane.showMessageDialog(this, 
                "Status atualizado para: " + newStatus.getDescription(),
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            loadOrders();
        }
    }

    private void loadOrders() {
        tableModel.setRowCount(0);
        for (Order order : controller.getAllOrders()) {
            String driverName = order.getDriver() != null ? order.getDriver().getName() : "Não atribuído";
            String paymentStatus = order.getPayment() != null && order.getPayment().isConfirmed() 
                                 ? "Confirmado" : "Pendente";
            
            tableModel.addRow(new Object[]{
                order.getId(),
                order.getCustomer().getName(),
                order.getRestaurant().getName(),
                driverName,
                String.format("R$ %.2f", order.getTotalValue()),
                order.getStatus().getDescription(),
                paymentStatus
            });
        }
    }
}
