package view;

import controller.CustomerController;
import controller.DriverController;
import controller.OrderController;
import controller.RestaurantController;
import enums.PaymentType;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import model.*;

public class OrderFrame extends JFrame {
    private OrderController orderController;
    private CustomerController customerController;
    private RestaurantController restaurantController;
    private DriverController driverController;
    
    private JComboBox<Object> cmbCustomer;
    private JComboBox<Object> cmbRestaurant;
    private JComboBox<Object> cmbProduct;
    private JComboBox<Object> cmbDriver;
    private JComboBox<PaymentType> cmbPayment;
    private JSpinner spnQuantity;
    private JTextArea txtProducts;
    private JLabel lblTotal;
    private List<Product> selectedProducts;

    public OrderFrame() {
        orderController = new OrderController();
        customerController = new CustomerController();
        restaurantController = new RestaurantController();
        driverController = new DriverController();
        selectedProducts = new ArrayList<>();
        
        setTitle("Criar Pedido");
        setSize(520, 580);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(250, 250, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);
        
        Font labelFont = new Font("Dialog", Font.PLAIN, 12);
        Font fieldFont = new Font("Dialog", Font.PLAIN, 12);
        
        int row = 0;
        
        // Cliente
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setFont(labelFont);
        lblCliente.setPreferredSize(new Dimension(130, 25));
        mainPanel.add(lblCliente, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        cmbCustomer = new JComboBox<>();
        cmbCustomer.setFont(fieldFont);
        cmbCustomer.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        cmbCustomer.setRenderer(new PlaceholderRenderer("Selecione um cliente"));
        mainPanel.add(cmbCustomer, gbc);
        
        row++;
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        JLabel lblRestaurante = new JLabel("Restaurante:");
        lblRestaurante.setFont(labelFont);
        lblRestaurante.setPreferredSize(new Dimension(130, 25));
        mainPanel.add(lblRestaurante, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        cmbRestaurant = new JComboBox<>();
        cmbRestaurant.setFont(fieldFont);
        cmbRestaurant.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        cmbRestaurant.setRenderer(new PlaceholderRenderer("Selecione um restaurante"));
        cmbRestaurant.addActionListener(e -> loadProducts());
        mainPanel.add(cmbRestaurant, gbc);
        
        row++;
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        JLabel lblProduto = new JLabel("Produto:");
        lblProduto.setFont(labelFont);
        lblProduto.setPreferredSize(new Dimension(130, 25));
        mainPanel.add(lblProduto, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        cmbProduct = new JComboBox<>();
        cmbProduct.setFont(fieldFont);
        cmbProduct.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        cmbProduct.setRenderer(new PlaceholderRenderer("Selecione um produto"));
        mainPanel.add(cmbProduct, gbc);
        
        row++;
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setFont(labelFont);
        lblQuantidade.setPreferredSize(new Dimension(130, 25));
        mainPanel.add(lblQuantidade, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0;
        spnQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        spnQuantity.setFont(fieldFont);
        spnQuantity.setPreferredSize(new Dimension(80, 25));
        spnQuantity.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        quantityPanel.setBackground(new Color(250, 250, 250));
        quantityPanel.add(spnQuantity);
        mainPanel.add(quantityPanel, gbc);
        
        row++;
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        JButton btnAddProduct = new JButton("Adicionar Produto");
        btnAddProduct.setFont(new Font("Dialog", Font.BOLD, 12));
        btnAddProduct.setBackground(new Color(76, 175, 80));
        btnAddProduct.setForeground(Color.WHITE);
        btnAddProduct.setFocusPainted(false);
        btnAddProduct.setBorderPainted(false);
        btnAddProduct.setPreferredSize(new Dimension(0, 32));
        btnAddProduct.addActionListener(e -> addProduct());
        mainPanel.add(btnAddProduct, gbc);
        
        row++;
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        JLabel lblProdutosPedido = new JLabel("Produtos no Pedido:");
        lblProdutosPedido.setFont(labelFont);
        lblProdutosPedido.setPreferredSize(new Dimension(130, 25));
        mainPanel.add(lblProdutosPedido, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtProducts = new JTextArea(4, 20);
        txtProducts.setFont(fieldFont);
        txtProducts.setEditable(false);
        txtProducts.setBackground(new Color(245, 245, 245));
        txtProducts.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        JScrollPane scrollPane = new JScrollPane(txtProducts);
        scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        mainPanel.add(scrollPane, gbc);
        
        row++;
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        JLabel lblEntregador = new JLabel("Entregador:");
        lblEntregador.setFont(labelFont);
        lblEntregador.setPreferredSize(new Dimension(130, 25));
        mainPanel.add(lblEntregador, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        cmbDriver = new JComboBox<>();
        cmbDriver.setFont(fieldFont);
        cmbDriver.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        cmbDriver.setRenderer(new PlaceholderRenderer("Selecione um entregador"));
        mainPanel.add(cmbDriver, gbc);
        
        row++;
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        JLabel lblPagamento = new JLabel("Pagamento:");
        lblPagamento.setFont(labelFont);
        lblPagamento.setPreferredSize(new Dimension(130, 25));
        mainPanel.add(lblPagamento, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        cmbPayment = new JComboBox<>(PaymentType.values());
        cmbPayment.setFont(fieldFont);
        cmbPayment.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        mainPanel.add(cmbPayment, gbc);
        
        row++;
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        JLabel lblTotalLabel = new JLabel("Total:");
        lblTotalLabel.setFont(labelFont);
        lblTotalLabel.setPreferredSize(new Dimension(130, 25));
        mainPanel.add(lblTotalLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        lblTotal = new JLabel("R$ 0,00");
        lblTotal.setFont(new Font("Dialog", Font.BOLD, 12));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        mainPanel.add(lblTotal, gbc);
        
        loadCustomers();
        loadRestaurants();
        loadDrivers();
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBackground(new Color(250, 250, 250));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        
        JButton btnCreate = new JButton("Criar Pedido");
        btnCreate.setFont(new Font("Dialog", Font.BOLD, 13));
        btnCreate.setBackground(new Color(25, 118, 210));
        btnCreate.setForeground(Color.WHITE);
        btnCreate.setFocusPainted(false);
        btnCreate.setBorderPainted(false);
        btnCreate.setPreferredSize(new Dimension(0, 36));
        btnCreate.addActionListener(e -> createOrder());
        
        JButton btnClear = new JButton("Limpar");
        btnClear.setFont(new Font("Dialog", Font.PLAIN, 13));
        btnClear.setBackground(new Color(189, 189, 189));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.setBorderPainted(false);
        btnClear.setPreferredSize(new Dimension(0, 36));
        btnClear.addActionListener(e -> clearForm());
        
        buttonPanel.add(btnCreate);
        buttonPanel.add(btnClear);
        
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadCustomers() {
        cmbCustomer.removeAllItems();
        cmbCustomer.addItem("");
        for (Customer customer : customerController.getAllCustomers()) {
            cmbCustomer.addItem(customer);
        }
    }

    private void loadRestaurants() {
        cmbRestaurant.removeAllItems();
        cmbRestaurant.addItem("");
        for (Restaurant restaurant : restaurantController.getAllRestaurants()) {
            cmbRestaurant.addItem(restaurant);
        }
    }

    private void loadProducts() {
        cmbProduct.removeAllItems();
        cmbProduct.addItem("");
        Object selected = cmbRestaurant.getSelectedItem();
        if (selected instanceof Restaurant) {
            Restaurant restaurant = (Restaurant) selected;
            for (Product product : restaurant.getProducts()) {
                cmbProduct.addItem(product);
            }
        }
    }

    private void loadDrivers() {
        cmbDriver.removeAllItems();
        cmbDriver.addItem("");
        for (DeliveryDriver driver : driverController.getAvailableDrivers()) {
            cmbDriver.addItem(driver);
        }
    }

    private void addProduct() {
        Object selected = cmbProduct.getSelectedItem();
        if (selected instanceof Product) {
            Product product = (Product) selected;
            int quantity = (Integer) spnQuantity.getValue();
            for (int i = 0; i < quantity; i++) {
                selectedProducts.add(product);
            }
            updateProductList();
            updateTotal();
            spnQuantity.setValue(1);
        }
    }

    private void updateProductList() {
        StringBuilder sb = new StringBuilder();
        for (Product product : selectedProducts) {
            sb.append(product.getName()).append(" - R$ ").append(String.format("%.2f", product.getPrice())).append("\n");
        }
        txtProducts.setText(sb.toString());
    }

    private void updateTotal() {
        double total = 0.0;
        for (Product product : selectedProducts) {
            total += product.getPrice();
        }
        lblTotal.setText(String.format("R$ %.2f", total));
    }

    private void createOrder() {
        Object customerObj = cmbCustomer.getSelectedItem();
        Object restaurantObj = cmbRestaurant.getSelectedItem();
        Object driverObj = cmbDriver.getSelectedItem();
        PaymentType paymentType = (PaymentType) cmbPayment.getSelectedItem();

        if (!(customerObj instanceof Customer) || !(restaurantObj instanceof Restaurant) || selectedProducts.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Selecione cliente, restaurante e adicione produtos!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Customer customer = (Customer) customerObj;
        Restaurant restaurant = (Restaurant) restaurantObj;
        
        Order order = orderController.createOrder(customer, restaurant);
        
        for (Product product : selectedProducts) {
            orderController.addProductToOrder(order, product);
        }
        
        if (driverObj instanceof DeliveryDriver) {
            orderController.assignDriverToOrder(order, (DeliveryDriver) driverObj);
        }
        
        orderController.processPayment(order, paymentType);

        JOptionPane.showMessageDialog(this, 
            String.format("Pedido #%d criado com sucesso!\nTotal: R$ %.2f", 
                         order.getId(), order.getTotalValue()),
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        clearForm();
    }

    private void clearForm() {
        selectedProducts.clear();
        txtProducts.setText("");
        lblTotal.setText("R$ 0,00");
        spnQuantity.setValue(1);
        if (cmbCustomer.getItemCount() > 0) cmbCustomer.setSelectedIndex(0);
        if (cmbRestaurant.getItemCount() > 0) cmbRestaurant.setSelectedIndex(0);
        if (cmbProduct.getItemCount() > 0) cmbProduct.setSelectedIndex(0);
        if (cmbDriver.getItemCount() > 0) cmbDriver.setSelectedIndex(0);
    }

    private static class PlaceholderRenderer extends DefaultListCellRenderer {
        private String placeholder;

        public PlaceholderRenderer(String placeholder) {
            this.placeholder = placeholder;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value == null || value.toString().isEmpty()) {
                setText(placeholder);
                setForeground(Color.GRAY);
            } else {
                setText(value.toString());
                setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            }
            return c;
        }
    }
}
