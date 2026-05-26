package view;

import controller.CustomerController;
import controller.DriverController;
import controller.OrderController;
import controller.RestaurantController;
import enums.PaymentType;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tela de criação de pedidos
 * Demonstra uso de JComboBox e interação entre objetos
 */
public class OrderFrame extends JFrame {
    private OrderController orderController;
    private CustomerController customerController;
    private RestaurantController restaurantController;
    private DriverController driverController;
    
    private JComboBox<Customer> cmbCustomer;
    private JComboBox<Restaurant> cmbRestaurant;
    private JComboBox<Product> cmbProduct;
    private JComboBox<DeliveryDriver> cmbDriver;
    private JComboBox<PaymentType> cmbPayment;
    private JList<Product> listProducts;
    private DefaultListModel<Product> listModel;
    private JLabel lblTotal;
    private List<Product> selectedProducts;

    public OrderFrame() {
        orderController = new OrderController();
        customerController = new CustomerController();
        restaurantController = new RestaurantController();
        driverController = new DriverController();
        selectedProducts = new ArrayList<>();
        
        setTitle("Criar Pedido");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel principal
        JPanel mainPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Cliente
        mainPanel.add(new JLabel("Cliente:"));
        cmbCustomer = new JComboBox<>();
        loadCustomers();
        mainPanel.add(cmbCustomer);
        
        // Restaurante
        mainPanel.add(new JLabel("Restaurante:"));
        cmbRestaurant = new JComboBox<>();
        cmbRestaurant.addActionListener(e -> loadProducts());
        loadRestaurants();
        mainPanel.add(cmbRestaurant);
        
        // Produto
        mainPanel.add(new JLabel("Produto:"));
        cmbProduct = new JComboBox<>();
        mainPanel.add(cmbProduct);
        
        // Botão adicionar produto
        mainPanel.add(new JLabel(""));
        JButton btnAddProduct = new JButton("Adicionar Produto");
        btnAddProduct.setBackground(new Color(0, 123, 255));
        btnAddProduct.setForeground(Color.WHITE);
        btnAddProduct.addActionListener(e -> addProduct());
        mainPanel.add(btnAddProduct);
        
        // Lista de produtos
        mainPanel.add(new JLabel("Produtos no Pedido:"));
        listModel = new DefaultListModel<>();
        listProducts = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listProducts);
        mainPanel.add(scrollPane);
        
        // Entregador
        mainPanel.add(new JLabel("Entregador:"));
        cmbDriver = new JComboBox<>();
        loadDrivers();
        mainPanel.add(cmbDriver);
        
        // Forma de pagamento
        mainPanel.add(new JLabel("Pagamento:"));
        cmbPayment = new JComboBox<>(PaymentType.values());
        mainPanel.add(cmbPayment);
        
        // Total
        mainPanel.add(new JLabel("Total:"));
        lblTotal = new JLabel("R$ 0,00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(lblTotal);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnCreate = new JButton("Criar Pedido");
        btnCreate.setBackground(new Color(40, 167, 69));
        btnCreate.setForeground(Color.WHITE);
        btnCreate.setPreferredSize(new Dimension(150, 40));
        btnCreate.addActionListener(e -> createOrder());
        
        JButton btnClear = new JButton("Limpar");
        btnClear.setPreferredSize(new Dimension(150, 40));
        btnClear.addActionListener(e -> clearForm());
        
        buttonPanel.add(btnCreate);
        buttonPanel.add(btnClear);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadCustomers() {
        cmbCustomer.removeAllItems();
        for (Customer customer : customerController.getAllCustomers()) {
            cmbCustomer.addItem(customer);
        }
    }

    private void loadRestaurants() {
        cmbRestaurant.removeAllItems();
        for (Restaurant restaurant : restaurantController.getAllRestaurants()) {
            cmbRestaurant.addItem(restaurant);
        }
    }

    private void loadProducts() {
        cmbProduct.removeAllItems();
        Restaurant restaurant = (Restaurant) cmbRestaurant.getSelectedItem();
        if (restaurant != null) {
            for (Product product : restaurant.getProducts()) {
                cmbProduct.addItem(product);
            }
        }
    }

    private void loadDrivers() {
        cmbDriver.removeAllItems();
        for (DeliveryDriver driver : driverController.getAvailableDrivers()) {
            cmbDriver.addItem(driver);
        }
    }

    private void addProduct() {
        Product product = (Product) cmbProduct.getSelectedItem();
        if (product != null) {
            selectedProducts.add(product);
            listModel.addElement(product);
            updateTotal();
        }
    }

    private void updateTotal() {
        double total = 0.0;
        for (Product product : selectedProducts) {
            total += product.getPrice();
        }
        lblTotal.setText(String.format("R$ %.2f", total));
    }

    private void createOrder() {
        Customer customer = (Customer) cmbCustomer.getSelectedItem();
        Restaurant restaurant = (Restaurant) cmbRestaurant.getSelectedItem();
        DeliveryDriver driver = (DeliveryDriver) cmbDriver.getSelectedItem();
        PaymentType paymentType = (PaymentType) cmbPayment.getSelectedItem();

        if (customer == null || restaurant == null || selectedProducts.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Selecione cliente, restaurante e adicione produtos!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cria o pedido
        Order order = orderController.createOrder(customer, restaurant);
        
        // Adiciona produtos
        for (Product product : selectedProducts) {
            orderController.addProductToOrder(order, product);
        }
        
        // Atribui entregador se selecionado
        if (driver != null) {
            orderController.assignDriverToOrder(order, driver);
        }
        
        // Processa pagamento
        orderController.processPayment(order, paymentType);

        JOptionPane.showMessageDialog(this, 
            String.format("Pedido #%d criado com sucesso!\nTotal: R$ %.2f", 
                         order.getId(), order.getTotalValue()),
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        clearForm();
    }

    private void clearForm() {
        selectedProducts.clear();
        listModel.clear();
        lblTotal.setText("R$ 0,00");
        cmbCustomer.setSelectedIndex(-1);
        cmbRestaurant.setSelectedIndex(-1);
        cmbProduct.removeAllItems();
        loadDrivers();
    }
}
