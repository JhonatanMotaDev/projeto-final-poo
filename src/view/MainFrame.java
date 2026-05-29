package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
    
    private static final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private static final Color SECONDARY_COLOR = new Color(241, 245, 249);
    private static final Color SIDEBAR_COLOR = new Color(30, 41, 59);
    private static final Color SIDEBAR_HOVER = new Color(55, 71, 79);
    private static final Color SIDEBAR_ACTIVE = new Color(25, 118, 210);
    private static final Color SIDEBAR_TEXT = new Color(236, 239, 241);
    private static final Color SIDEBAR_SEPARATOR = new Color(69, 90, 100);
    private static final Color TEXT_COLOR = new Color(15, 23, 42);
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94);
    private static final Color DANGER_COLOR = new Color(239, 68, 68);
    
    private JButton activeMenuButton = null;
    
    public MainFrame() {
        setTitle("Sistema de Delivery - POO");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(SECONDARY_COLOR);

        JPanel topPanel = createTopPanel();
        
        JPanel sidePanel = createSidePanel();
        
        JPanel centerPanel = createCenterPanel();

        add(topPanel, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(1100, 70));
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(226, 232, 240)));
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Sistema de Delivery");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);
        
        JLabel subtitleLabel = new JLabel("Gerenciamento Completo");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(100, 116, 139));
        
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, -5));
        textPanel.setBackground(Color.WHITE);
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);
        
        titlePanel.add(textPanel);
        
        topPanel.add(titlePanel, BorderLayout.WEST);
        
        return topPanel;
    }
    
    private JPanel createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(SIDEBAR_COLOR);
        sidePanel.setPreferredSize(new Dimension(220, 630));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        JLabel menuLabel = new JLabel("MENU PRINCIPAL");
        menuLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        menuLabel.setForeground(new Color(148, 163, 184));
        menuLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuLabel.setBorder(new EmptyBorder(0, 16, 15, 0));
        sidePanel.add(menuLabel);
        
        sidePanel.add(createMenuButton("Clientes", "", this::openCustomerScreen));
        sidePanel.add(createMenuButton("Entregadores", "", this::openDriverScreen));
        sidePanel.add(createMenuButton("Restaurantes", "", this::openRestaurantScreen));
        sidePanel.add(createMenuButton("Criar Pedido", "", this::openOrderScreen));
        sidePanel.add(createMenuButton("Entregas", "", this::openDeliveryScreen));
        sidePanel.add(createMenuButton("Relatórios", "", this::openReportScreen));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 8)));
        
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(220, 1));
        separator.setForeground(new Color(84, 110, 122));
        separator.setBackground(new Color(84, 110, 122));
        sidePanel.add(separator);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 8)));
        
        sidePanel.add(createMenuButton("Sobre", "", this::showAbout));
        sidePanel.add(createMenuButton("Sair", "", () -> System.exit(0)));
        
        sidePanel.add(Box.createVerticalGlue());
        
        return sidePanel;
    }
    
    private JButton createMenuButton(String text, String description, Runnable action) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setMaximumSize(new Dimension(220, 40));
        button.setPreferredSize(new Dimension(220, 40));
        button.setBackground(SIDEBAR_COLOR);
        button.setForeground(SIDEBAR_TEXT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(new EmptyBorder(12, 16, 12, 16));
        
        JLabel titleLabel = new JLabel(text);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 13));
        titleLabel.setForeground(SIDEBAR_TEXT);
        
        button.add(titleLabel, BorderLayout.CENTER);
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button != activeMenuButton) {
                    button.setBackground(SIDEBAR_HOVER);
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (button != activeMenuButton) {
                    button.setBackground(SIDEBAR_COLOR);
                }
            }
        });
        
        button.addActionListener(e -> {
            setActiveMenuButton(button, titleLabel);
            action.run();
        });
        
        return button;
    }
    
    private void setActiveMenuButton(JButton button, JLabel titleLabel) {
        if (activeMenuButton != null && activeMenuButton != button) {
            activeMenuButton.setBackground(SIDEBAR_COLOR);
            Component comp = activeMenuButton.getComponent(0);
            if (comp instanceof JLabel) {
                ((JLabel) comp).setForeground(SIDEBAR_TEXT);
            }
        }
        
        activeMenuButton = button;
        button.setBackground(SIDEBAR_ACTIVE);
        titleLabel.setForeground(Color.WHITE);
    }
    
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(SECONDARY_COLOR);
        centerPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        JPanel welcomeCard = new JPanel();
        welcomeCard.setLayout(new BoxLayout(welcomeCard, BoxLayout.Y_AXIS));
        welcomeCard.setBackground(Color.WHITE);
        welcomeCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
            new EmptyBorder(40, 40, 40, 40)
        ));
        
        JLabel welcomeTitle = new JLabel("Bem-vindo ao Sistema de Delivery", SwingConstants.CENTER);
        welcomeTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeTitle.setForeground(TEXT_COLOR);
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel welcomeSubtitle = new JLabel("Selecione uma opção no menu lateral para começar", SwingConstants.CENTER);
        welcomeSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        welcomeSubtitle.setForeground(new Color(100, 116, 139));
        welcomeSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        welcomeCard.add(welcomeTitle);
        welcomeCard.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomeCard.add(welcomeSubtitle);
        welcomeCard.add(Box.createRigidArea(new Dimension(0, 30)));
        
        JPanel shortcutsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        shortcutsPanel.setBackground(Color.WHITE);
        shortcutsPanel.setOpaque(false);
        
        shortcutsPanel.add(createShortcutCard("Novo Pedido", "Criar um novo pedido", this::openOrderScreen));
        shortcutsPanel.add(createShortcutCard("Clientes", "Gerenciar clientes", this::openCustomerScreen));
        shortcutsPanel.add(createShortcutCard("Relatorios", "Ver estatisticas", this::openReportScreen));
        
        welcomeCard.add(shortcutsPanel);
        
        centerPanel.add(welcomeCard, BorderLayout.CENTER);
        
        return centerPanel;
    }
    
    private JPanel createShortcutCard(String title, String description, Runnable action) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(SECONDARY_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel descLabel = new JLabel(description, SwingConstants.CENTER);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(new Color(100, 116, 139));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(descLabel);
        
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(226, 232, 240));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(SECONDARY_COLOR);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                action.run();
            }
        });
        
        return card;
    }

    private void openCustomerScreen() {
        CustomerFrame frame = new CustomerFrame();
        frame.setVisible(true);
    }

    private void openDriverScreen() {
        DriverFrame frame = new DriverFrame();
        frame.setVisible(true);
    }

    private void openRestaurantScreen() {
        RestaurantFrame frame = new RestaurantFrame();
        frame.setVisible(true);
    }

    private void openOrderScreen() {
        OrderFrame frame = new OrderFrame();
        frame.setVisible(true);
    }

    private void openDeliveryScreen() {
        DeliveryFrame frame = new DeliveryFrame();
        frame.setVisible(true);
    }

    private void openReportScreen() {
        ReportFrame frame = new ReportFrame();
        frame.setVisible(true);
    }

    private void showAbout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel title = new JLabel("Sistema de Delivery - POO");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitle = new JLabel("Projeto Acadêmico");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(new Color(100, 116, 139));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JTextArea concepts = new JTextArea(
            "\nConceitos de POO implementados:\n\n" +
            "• Classes e Objetos\n" +
            "• Encapsulamento\n" +
            "• Herança\n" +
            "• Polimorfismo\n" +
            "• Classe Abstrata\n" +
            "• Interface\n" +
            "• Enum\n" +
            "• Associação\n" +
            "• Padrão MVC"
        );
        concepts.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        concepts.setEditable(false);
        concepts.setOpaque(false);
        concepts.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(subtitle);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(concepts);
        
        JOptionPane.showMessageDialog(this, panel, "Sobre o Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
