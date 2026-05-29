package view;

import controller.DriverController;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.DeliveryDriver;

public class DriverFrame extends JFrame {
    private DriverController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtPhone, txtAddress, txtVehicle, txtPlate;
    
    private static final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private static final Color SECONDARY_COLOR = new Color(241, 245, 249);
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94);
    private static final Color TEXT_COLOR = new Color(15, 23, 42);

    public DriverFrame() {
        controller = new DriverController();
        
        setTitle("Gerenciar Entregadores");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(SECONDARY_COLOR);

        JPanel headerPanel = createHeader();
        add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setBackground(SECONDARY_COLOR);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = createFormPanel();
        contentPanel.add(formPanel, BorderLayout.NORTH);

        JPanel tablePanel = createTablePanel();
        contentPanel.add(tablePanel, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        loadDrivers();
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(1100, 70));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(226, 232, 240)));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        titlePanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Gerenciar Entregadores");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);

        JLabel subtitleLabel = new JLabel("Cadastre e gerencie os entregadores do sistema");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(100, 116, 139));

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, -5));
        textPanel.setBackground(Color.WHITE);
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);

        titlePanel.add(textPanel);

        header.add(titlePanel, BorderLayout.WEST);

        return header;
    }

    private JPanel createFormPanel() {
        JPanel formCard = new JPanel(new BorderLayout());
        formCard.setBackground(Color.WHITE);
        formCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel formTitle = new JLabel("Cadastrar Novo Entregador");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formTitle.setForeground(TEXT_COLOR);
        formTitle.setBorder(new EmptyBorder(0, 0, 15, 0));

        JPanel fieldsPanel = new JPanel(new GridLayout(3, 4, 15, 15));
        fieldsPanel.setBackground(Color.WHITE);

        fieldsPanel.add(createLabel("Nome Completo:"));
        txtName = createTextField("Digite o nome completo");
        fieldsPanel.add(txtName);

        fieldsPanel.add(createLabel("Telefone:"));
        txtPhone = createTextField("(00) 00000-0000");
        fieldsPanel.add(txtPhone);

        fieldsPanel.add(createLabel("Endereço:"));
        txtAddress = createTextField("Rua, número, bairro");
        fieldsPanel.add(txtAddress);

        fieldsPanel.add(createLabel("Tipo de Veículo:"));
        txtVehicle = createTextField("Ex: Moto, Carro, Bicicleta");
        fieldsPanel.add(txtVehicle);

        fieldsPanel.add(createLabel("Placa do Veículo:"));
        txtPlate = createTextField("ABC-1234");
        fieldsPanel.add(txtPlate);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        JButton btnClear = createSecondaryButton("Limpar");
        btnClear.addActionListener(e -> clearForm());

        JButton btnSave = createPrimaryButton("Salvar Entregador");
        btnSave.addActionListener(e -> saveDriver());

        buttonsPanel.add(btnClear);
        buttonsPanel.add(btnSave);

        JPanel formContent = new JPanel(new BorderLayout(0, 15));
        formContent.setBackground(Color.WHITE);
        formContent.add(formTitle, BorderLayout.NORTH);
        formContent.add(fieldsPanel, BorderLayout.CENTER);
        formContent.add(buttonsPanel, BorderLayout.SOUTH);

        formCard.add(formContent);

        return formCard;
    }

    private JPanel createTablePanel() {
        JPanel tableCard = new JPanel(new BorderLayout());
        tableCard.setBackground(Color.WHITE);
        tableCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel tableTitle = new JLabel("Entregadores Cadastrados");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableTitle.setForeground(TEXT_COLOR);
        tableTitle.setBorder(new EmptyBorder(0, 0, 15, 0));

        String[] columns = {"ID", "Nome", "Telefone", "Veículo", "Placa", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(35);
        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(TEXT_COLOR);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(SECONDARY_COLOR);
        header.setForeground(TEXT_COLOR);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240), 1));

        tableCard.add(tableTitle, BorderLayout.NORTH);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        return tableCard;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(TEXT_COLOR);
        return label;
    }

    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
            new EmptyBorder(8, 12, 8, 12)
        ));
        textField.setToolTipText(placeholder);
        return textField;
    }

    private JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(SUCCESS_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }

    private JButton createSecondaryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(SECONDARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(140, 40));
        return button;
    }

    private void saveDriver() {
        if (txtName.getText().trim().isEmpty() || txtPhone.getText().trim().isEmpty() || 
            txtAddress.getText().trim().isEmpty() || txtVehicle.getText().trim().isEmpty() || 
            txtPlate.getText().trim().isEmpty()) {
            showError("Por favor, preencha todos os campos!");
            return;
        }

        controller.createDriver(
            txtName.getText().trim(), 
            txtPhone.getText().trim(), 
            txtAddress.getText().trim(), 
            txtVehicle.getText().trim(), 
            txtPlate.getText().trim()
        );
        
        showSuccess("Entregador cadastrado com sucesso!");
        clearForm();
        loadDrivers();
    }

    private void clearForm() {
        txtName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtVehicle.setText("");
        txtPlate.setText("");
        txtName.requestFocus();
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
                driver.isAvailable() ? "Disponivel" : "Ocupado"
            });
        }
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
