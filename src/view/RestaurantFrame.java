package view;

import controller.RestaurantController;
import model.Product;
import model.Restaurant;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Tela de gerenciamento de restaurantes e produtos
 * Design moderno e clean
 */
public class RestaurantFrame extends JFrame {
    private static final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private static final Color SECONDARY_COLOR = new Color(241, 245, 249);
    private static final Color TEXT_COLOR = new Color(15, 23, 42);
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94);
    private static final Color TABLE_HEADER_BG = new Color(241, 245, 249);
    
    private RestaurantController controller;
    private JTable tableRestaurants, tableProducts;
    private DefaultTableModel restaurantModel, productModel;
    private JTextField txtName, txtCategory, txtAddress, txtPhone;
    private JTextField txtProductName, txtPrice, txtDescription, txtProductCategory;
    private JComboBox<Restaurant> cmbRestaurant;

    public RestaurantFrame() {
        controller = new RestaurantController();
        
        setTitle("Gerenciar Restaurantes");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(SECONDARY_COLOR);

        // Header
        JPanel header = createHeader("Restaurantes 🍽️", "Gerencie seus restaurantes e produtos");
        add(header, BorderLayout.NORTH);

        // Painel principal com abas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        // Aba de restaurantes
        JPanel restaurantPanel = createRestaurantPanel();
        tabbedPane.addTab("Restaurantes", restaurantPanel);
        
        // Aba de produtos
        JPanel productPanel = createProductPanel();
        tabbedPane.addTab("Produtos", productPanel);

        add(tabbedPane, BorderLayout.CENTER);
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

    private JPanel createRestaurantPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(SECONDARY_COLOR);
        
        // Formulário em card
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 12, 12));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        formPanel.setBackground(SECONDARY_COLOR);
        
        JLabel lblTitle = new JLabel("Cadastrar Restaurante");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(TEXT_COLOR);
        formPanel.add(lblTitle);
        formPanel.add(new JLabel());
        
        formPanel.add(createLabel("Nome:"));
        txtName = createTextField();
        formPanel.add(txtName);
        
        formPanel.add(createLabel("Categoria:"));
        txtCategory = createTextField();
        formPanel.add(txtCategory);
        
        formPanel.add(createLabel("Endereço:"));
        txtAddress = createTextField();
        formPanel.add(txtAddress);
        
        formPanel.add(createLabel("Telefone:"));
        txtPhone = createTextField();
        formPanel.add(txtPhone);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setBackground(SECONDARY_COLOR);
        
        JButton btnSave = createButton("Salvar", SUCCESS_COLOR);
        btnSave.addActionListener(e -> saveRestaurant());
        buttonPanel.add(btnSave);
        
        JButton btnClear = createButton("Limpar", new Color(100, 116, 139));
        btnClear.addActionListener(e -> clearRestaurantForm());
        buttonPanel.add(btnClear);
        
        formPanel.add(buttonPanel);
        formPanel.add(new JLabel());

        // Tabela em card
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(SECONDARY_COLOR);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblTableTitle = new JLabel("Restaurantes Cadastrados");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTableTitle.setForeground(TEXT_COLOR);
        tablePanel.add(lblTableTitle, BorderLayout.NORTH);
        
        String[] columns = {"ID", "Nome", "Categoria", "Telefone", "Produtos"};
        restaurantModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableRestaurants = createTable(restaurantModel);
        JScrollPane scrollPane = new JScrollPane(tableRestaurants);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(SECONDARY_COLOR);
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        
        loadRestaurants();
        
        return panel;
    }

    private JPanel createProductPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(SECONDARY_COLOR);
        
        // Formulário em card
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 12, 12));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        formPanel.setBackground(SECONDARY_COLOR);
        
        JLabel lblTitle = new JLabel("Adicionar Produto");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(TEXT_COLOR);
        formPanel.add(lblTitle);
        formPanel.add(new JLabel());
        
        formPanel.add(createLabel("Restaurante:"));
        cmbRestaurant = new JComboBox<>();
        cmbRestaurant.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbRestaurant.setBackground(Color.WHITE);
        cmbRestaurant.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        formPanel.add(cmbRestaurant);
        
        formPanel.add(createLabel("Nome do Produto:"));
        txtProductName = createTextField();
        formPanel.add(txtProductName);
        
        formPanel.add(createLabel("Preço:"));
        txtPrice = createTextField();
        formPanel.add(txtPrice);
        
        formPanel.add(createLabel("Descrição:"));
        txtDescription = createTextField();
        formPanel.add(txtDescription);
        
        formPanel.add(createLabel("Categoria:"));
        txtProductCategory = createTextField();
        formPanel.add(txtProductCategory);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setBackground(SECONDARY_COLOR);
        
        JButton btnSave = createButton("Adicionar Produto", SUCCESS_COLOR);
        btnSave.addActionListener(e -> saveProduct());
        buttonPanel.add(btnSave);
        
        JButton btnRefresh = createButton("Atualizar Lista", PRIMARY_COLOR);
        btnRefresh.addActionListener(e -> loadProducts());
        buttonPanel.add(btnRefresh);
        
        formPanel.add(buttonPanel);
        formPanel.add(new JLabel());

        // Tabela em card
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(SECONDARY_COLOR);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblTableTitle = new JLabel("Produtos Cadastrados");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTableTitle.setForeground(TEXT_COLOR);
        tablePanel.add(lblTableTitle, BorderLayout.NORTH);
        
        String[] columns = {"Restaurante", "Produto", "Preço", "Categoria", "Descrição"};
        productModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableProducts = createTable(productModel);
        JScrollPane scrollPane = new JScrollPane(tableProducts);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(SECONDARY_COLOR);
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);
        
        loadRestaurants();
        updateRestaurantComboBox();
        
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(TEXT_COLOR);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        field.setPreferredSize(new Dimension(0, 35));
        return field;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 35));
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

    private void saveRestaurant() {
        if (txtName.getText().trim().isEmpty() || txtCategory.getText().trim().isEmpty() || 
            txtAddress.getText().trim().isEmpty() || txtPhone.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        controller.createRestaurant(txtName.getText().trim(), txtCategory.getText().trim(), 
                                   txtAddress.getText().trim(), txtPhone.getText().trim());
        
        JOptionPane.showMessageDialog(this, "Restaurante cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        clearRestaurantForm();
        loadRestaurants();
        updateRestaurantComboBox();
    }

    private void saveProduct() {
        Restaurant restaurant = (Restaurant) cmbRestaurant.getSelectedItem();
        if (restaurant == null) {
            JOptionPane.showMessageDialog(this, "Selecione um restaurante!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (txtProductName.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String priceText = txtPrice.getText().trim().replace(",", ".");
            double price = Double.parseDouble(priceText);
            controller.addProductToRestaurant(restaurant, txtProductName.getText().trim(), price, 
                                            txtDescription.getText().trim(), txtProductCategory.getText().trim());
            
            JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clearProductForm();
            loadProducts();
            loadRestaurants();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço inválido! Use formato como: 10.90 ou 10,90", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearRestaurantForm() {
        txtName.setText("");
        txtCategory.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
    }

    private void clearProductForm() {
        txtProductName.setText("");
        txtPrice.setText("");
        txtDescription.setText("");
        txtProductCategory.setText("");
    }

    private void loadRestaurants() {
        restaurantModel.setRowCount(0);
        for (Restaurant restaurant : controller.getAllRestaurants()) {
            restaurantModel.addRow(new Object[]{
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getCategory(),
                restaurant.getPhone(),
                restaurant.getProducts().size()
            });
        }
    }

    private void loadProducts() {
        productModel.setRowCount(0);
        for (Restaurant restaurant : controller.getAllRestaurants()) {
            for (Product product : restaurant.getProducts()) {
                productModel.addRow(new Object[]{
                    restaurant.getName(),
                    product.getName(),
                    String.format("R$ %.2f", product.getPrice()),
                    product.getCategory(),
                    product.getDescription()
                });
            }
        }
    }

    private void updateRestaurantComboBox() {
        cmbRestaurant.removeAllItems();
        for (Restaurant restaurant : controller.getAllRestaurants()) {
            cmbRestaurant.addItem(restaurant);
        }
    }
}
