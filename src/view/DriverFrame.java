package view;

import controller.DriverController;
import model.DeliveryDriver;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Tela de gerenciamento de entregadores
 */
public class DriverFrame extends JFrame {
    private DriverController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtPhone, txtAddress, txtVehicle, txtPlate;

    public DriverFrame() {
        controller = new DriverController();
        
        setTitle("Gerenciar Entregadores");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Cadastrar Entregador"));
        
        formPanel.add(new JLabel("Nome:"));
        txtName = new JTextField();
        formPanel.add(txtName);
        
        formPanel.add(new JLabel("Telefone:"));
        txtPhone = new JTextField();
        formPanel.add(txtPhone);
        
        formPanel.add(new JLabel("Endereço:"));
        txtAddress = new JTextField();
        formPanel.add(txtAddress);
        
        formPanel.add(new JLabel("Tipo de Veículo:"));
        txtVehicle = new JTextField();
        formPanel.add(txtVehicle);
        
        formPanel.add(new JLabel("Placa:"));
        txtPlate = new JTextField();
        formPanel.add(txtPlate);
        
        JButton btnSave = new JButton("Salvar");
        btnSave.setBackground(new Color(40, 167, 69));
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(e -> saveDriver());
        formPanel.add(btnSave);
        
        JButton btnClear = new JButton("Limpar");
        btnClear.addActionListener(e -> clearForm());
        formPanel.add(btnClear);

        // Painel de tabela
        String[] columns = {"ID", "Nome", "Telefone", "Veículo", "Placa", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Entregadores Cadastrados"));

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadDrivers();
    }

    private void saveDriver() {
        if (txtName.getText().isEmpty() || txtPhone.getText().isEmpty() || 
            txtAddress.getText().isEmpty() || txtVehicle.getText().isEmpty() || 
            txtPlate.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        controller.createDriver(txtName.getText(), txtPhone.getText(), 
                              txtAddress.getText(), txtVehicle.getText(), txtPlate.getText());
        
        JOptionPane.showMessageDialog(this, "Entregador cadastrado com sucesso!");
        clearForm();
        loadDrivers();
    }

    private void clearForm() {
        txtName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtVehicle.setText("");
        txtPlate.setText("");
    }

    private void loadDrivers() {
        tableModel.setRowCount(0);
        for (DeliveryDriver driver : controller.getAllDrivers()) {
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
}
