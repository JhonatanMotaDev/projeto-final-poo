package view;

import controller.RestaurantController;
import model.Product;
import model.Restaurant;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Tela de gerenciamento de restaurantes e produtos
 */
public class RestaurantFrame extends JFrame {
    private RestaurantController controller;
    private JTable tableRestaurants, tableProducts;
    private DefaultTableModel restaurantModel, productModel;
    private JTextField txtName, txtCategory, txtAddress, txtPhone;
    private JTextField txtProductName, txtPrice, txtDescription, txtProductCategory;
    private JComboBox<Restaurant> cmbRestaurant;

    public RestaurantFrame() {
        controller = new RestaurantController();
        
        setTitle("Gerenciar Restaurantes");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel principal com abas
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Aba de restaurantes
        JPanel restaurantPanel = createRestaurantPanel();
        tabbedPane.addTab("Restaurantes", restaurantPanel);
        
        // Aba de produtos
        JPanel productPanel = createProductPanel();
        tabbedPane.addTab("Produtos", productPanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createRestaurantPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        // Formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Cadastrar Restaurante"));
        
        formPanel.add(new JLabel("Nome:"));
        txtName = new JTextField();
        formPanel.add(txtName);
        
        formPanel.add(new JLabel("Categoria:"));
        txtCategory = new JTextField();
        formPanel.add(txtCategory);
        
        formPanel.add(new JLabel("Endereço:"));
        txtAddress = new JTextField();
        formPanel.add(txtAddress);
        
        formPanel.add(new JLabel("Telefone:"));
        txtPhone = new JTextField();
        formPanel.add(txtPhone);
        
        JButton btnSave = new JButton("Salvar");
        btnSave.setBackground(new Color(40, 167, 69));
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(e -> saveRestaurant());
        formPanel.add(btnSave);
        
        JButton btnClear = new JButton("Limpar");
        btnClear.addActionListener(e -> clearRestaurantForm());
        formPanel.add(btnClear);

        // Tabela
        String[] columns = {"ID", "Nome", "Categoria", "Telefone", "Produtos"};
        restaurantModel = new DefaultTableModel(columns, 0);
        tableRestaurants = new JTable(restaurantModel);
        JScrollPane scrollPane = new JScrollPane(tableRestaurants);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Restaurantes Cadastrados"));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createProductPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        // Formulário
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Adicionar Produto"));
        
        formPanel.add(new JLabel("Restaurante:"));
        cmbRestaurant = new JComboBox<>();
        formPanel.add(cmbRestaurant);
        
        formPanel.add(new JLabel("Nome do Produto:"));
        txtProductName = new JTextField();
        formPanel.add(txtProductName);
        
        formPanel.add(new JLabel("Preço:"));
        txtPrice = new JTextField();
        formPanel.add(txtPrice);
        
        formPanel.add(new JLabel("Descrição:"));
        txtDescription = new JTextField();
        formPanel.add(txtDescription);
        
        formPanel.add(new JLabel("Categoria:"));
        txtProductCategory = new JTextField();
        formPanel.add(txtProductCategory);
        
        JButton btnSave = new JButton("Adicionar Produto");
        btnSave.setBackground(new Color(40, 167, 69));
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(e -> saveProduct());
        formPanel.add(btnSave);
        
        JButton btnRefresh = new JButton("Atualizar Lista");
        btnRefresh.addActionListener(e -> loadProducts());
        formPanel.add(btnRefresh);

        // Tabela
        String[] columns = {"Restaurante", "Produto", "Preço", "Categoria", "Descrição"};
        productModel = new DefaultTableModel(columns, 0);
        tableProducts = new JTable(productModel);
        JScrollPane scrollPane = new JScrollPane(tableProducts);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Produtos Cadastrados"));

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private void saveRestaurant() {
        if (txtName.getText().isEmpty() || txtCategory.getText().isEmpty() || 
            txtAddress.getText().isEmpty() || txtPhone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        controller.createRestaurant(txtName.getText(), txtCategory.getText(), 
                                   txtAddress.getText(), txtPhone.getText());
        
        JOptionPane.showMessageDialog(this, "Restaurante cadastrado com sucesso!");
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

        if (txtProductName.getText().isEmpty() || txtPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha os campos obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(txtPrice.getText());
            controller.addProductToRestaurant(restaurant, txtProductName.getText(), price, 
                                            txtDescription.getText(), txtProductCategory.getText());
            
            JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
            clearProductForm();
            loadProducts();
            loadRestaurants();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
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
