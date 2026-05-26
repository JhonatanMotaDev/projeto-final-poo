package view;

import controller.CustomerController;
import model.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Tela de gerenciamento de clientes
 * Demonstra uso de JTable, JTextField, JButton
 */
public class CustomerFrame extends JFrame {
    private CustomerController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtPhone, txtAddress, txtEmail;

    public CustomerFrame() {
        controller = new CustomerController();
        
        setTitle("Gerenciar Clientes");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Cadastrar Cliente"));
        
        formPanel.add(new JLabel("Nome:"));
        txtName = new JTextField();
        formPanel.add(txtName);
        
        formPanel.add(new JLabel("Telefone:"));
        txtPhone = new JTextField();
        formPanel.add(txtPhone);
        
        formPanel.add(new JLabel("Endereço:"));
        txtAddress = new JTextField();
        formPanel.add(txtAddress);
        
        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);
        
        JButton btnSave = new JButton("Salvar");
        btnSave.setBackground(new Color(40, 167, 69));
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(e -> saveCustomer());
        formPanel.add(btnSave);
        
        JButton btnClear = new JButton("Limpar");
        btnClear.addActionListener(e -> clearForm());
        formPanel.add(btnClear);

        // Painel de tabela
        String[] columns = {"ID", "Nome", "Telefone", "Email", "Endereço"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Clientes Cadastrados"));

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadCustomers();
    }

    private void saveCustomer() {
        if (txtName.getText().isEmpty() || txtPhone.getText().isEmpty() || 
            txtAddress.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        controller.createCustomer(txtName.getText(), txtPhone.getText(), 
                                 txtAddress.getText(), txtEmail.getText());
        
        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
        clearForm();
        loadCustomers();
    }

    private void clearForm() {
        txtName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
    }

    private void loadCustomers() {
        tableModel.setRowCount(0);
        for (Customer customer : controller.getAllCustomers()) {
            tableModel.addRow(new Object[]{
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress()
            });
        }
    }
}
