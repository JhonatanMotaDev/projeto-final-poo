package view;

import javax.swing.*;
import java.awt.*;

/**
 * Tela principal do sistema - Menu principal
 * Demonstra uso de JFrame, JPanel, JButton
 */
public class MainFrame extends JFrame {
    
    public MainFrame() {
        setTitle("Sistema de Delivery - POO");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel superior com título
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(220, 53, 69));
        topPanel.setPreferredSize(new Dimension(900, 80));
        JLabel titleLabel = new JLabel("Sistema de Delivery");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);

        // Painel lateral com menu
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(52, 58, 64));
        sidePanel.setPreferredSize(new Dimension(200, 520));
        sidePanel.setLayout(new GridLayout(8, 1, 5, 5));

        // Botões do menu
        JButton btnCustomers = createMenuButton("Clientes");
        JButton btnDrivers = createMenuButton("Entregadores");
        JButton btnRestaurants = createMenuButton("Restaurantes");
        JButton btnOrders = createMenuButton("Pedidos");
        JButton btnDeliveries = createMenuButton("Entregas");
        JButton btnReports = createMenuButton("Relatórios");
        JButton btnAbout = createMenuButton("Sobre");
        JButton btnExit = createMenuButton("Sair");

        sidePanel.add(btnCustomers);
        sidePanel.add(btnDrivers);
        sidePanel.add(btnRestaurants);
        sidePanel.add(btnOrders);
        sidePanel.add(btnDeliveries);
        sidePanel.add(btnReports);
        sidePanel.add(btnAbout);
        sidePanel.add(btnExit);

        // Painel central
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BorderLayout());
        
        JLabel welcomeLabel = new JLabel("<html><center><h1>Bem-vindo ao Sistema de Delivery</h1>" +
                "<p>Selecione uma opção no menu lateral</p></center></html>", SwingConstants.CENTER);
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Adiciona painéis ao frame
        add(topPanel, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        // Ações dos botões
        btnCustomers.addActionListener(e -> openCustomerScreen());
        btnDrivers.addActionListener(e -> openDriverScreen());
        btnRestaurants.addActionListener(e -> openRestaurantScreen());
        btnOrders.addActionListener(e -> openOrderScreen());
        btnDeliveries.addActionListener(e -> openDeliveryScreen());
        btnReports.addActionListener(e -> openReportScreen());
        btnAbout.addActionListener(e -> showAbout());
        btnExit.addActionListener(e -> System.exit(0));
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(40, 167, 69));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    private void openCustomerScreen() {
        new CustomerFrame().setVisible(true);
    }

    private void openDriverScreen() {
        new DriverFrame().setVisible(true);
    }

    private void openRestaurantScreen() {
        new RestaurantFrame().setVisible(true);
    }

    private void openOrderScreen() {
        new OrderFrame().setVisible(true);
    }

    private void openDeliveryScreen() {
        new DeliveryFrame().setVisible(true);
    }

    private void openReportScreen() {
        new ReportFrame().setVisible(true);
    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this,
                "Sistema de Delivery - POO\n\n" +
                "Projeto Acadêmico\n" +
                "Demonstra conceitos de:\n" +
                "- Classes e Objetos\n" +
                "- Encapsulamento\n" +
                "- Herança\n" +
                "- Polimorfismo\n" +
                "- Classe Abstrata\n" +
                "- Interface\n" +
                "- Enum\n" +
                "- Associação\n" +
                "- MVC Pattern",
                "Sobre o Sistema",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
