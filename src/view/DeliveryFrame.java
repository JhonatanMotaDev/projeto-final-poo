package view;

import controller.OrderController;
import enums.OrderStatus;
import model.Order;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Tela de gerenciamento de entregas
 * Demonstra atualização de status e uso da interface Deliverable
 */
public class DeliveryFrame extends JFrame {
    private OrderController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<OrderStatus> cmbStatus;

    public DeliveryFrame() {
        controller = new OrderController();
        
        setTitle("Gerenciar Entregas");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel superior
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder("Atualizar Status"));
        
        topPanel.add(new JLabel("Novo Status:"));
        cmbStatus = new JComboBox<>(OrderStatus.values());
        topPanel.add(cmbStatus);
        
        JButton btnUpdate = new JButton("Atualizar Status");
        btnUpdate.setBackground(new Color(255, 193, 7));
        btnUpdate.addActionListener(e -> updateStatus());
        topPanel.add(btnUpdate);
        
        JButton btnRefresh = new JButton("Atualizar Lista");
        btnRefresh.setBackground(new Color(0, 123, 255));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.addActionListener(e -> loadOrders());
        topPanel.add(btnRefresh);

        // Tabela
        String[] columns = {"ID", "Cliente", "Restaurante", "Entregador", "Total", "Status", "Pagamento"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Pedidos"));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadOrders();
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
