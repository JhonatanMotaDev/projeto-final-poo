package app;

import view.MainFrame;
import javax.swing.*;

/**
 * Classe principal do sistema
 * Ponto de entrada da aplicação
 * 
 * SISTEMA DE DELIVERY - PROJETO POO
 * 
 * Este projeto demonstra todos os conceitos de Programação Orientada a Objetos:
 * 
 * 1. CLASSES E OBJETOS
 *    - Todas as entidades são representadas por classes (Customer, Order, Restaurant, etc.)
 * 
 * 2. ENCAPSULAMENTO
 *    - Atributos privados com getters e setters em todas as classes
 * 
 * 3. HERANÇA
 *    - User (classe abstrata) -> Customer, DeliveryDriver, Administrator
 * 
 * 4. POLIMORFISMO
 *    - Método showData() implementado de forma diferente em cada subclasse de User
 *    - Método login() sobrescrito em cada subclasse
 * 
 * 5. CLASSE ABSTRATA
 *    - User é uma classe abstrata com métodos abstratos
 * 
 * 6. INTERFACE
 *    - Deliverable implementada pela classe Order
 * 
 * 7. ENUM
 *    - OrderStatus e PaymentType
 * 
 * 8. ASSOCIAÇÃO
 *    - Order tem Customer, Restaurant, DeliveryDriver, Products e Payment
 *    - Restaurant tem Products
 * 
 * 9. CONSTRUTORES
 *    - Todas as classes possuem construtores
 * 
 * 10. ARRAYLIST
 *     - DataService usa ArrayList para armazenar dados
 * 
 * 11. MVC
 *     - Model: classes de entidade
 *     - View: telas Swing
 *     - Controller: lógica de negócio
 */
public class Main {
    public static void main(String[] args) {
        // Configura o Look and Feel do sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inicia a aplicação na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
