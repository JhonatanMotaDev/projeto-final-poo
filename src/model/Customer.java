package model;

/**
 * Classe Customer - demonstra Herança
 * Herda de User e implementa seus métodos abstratos
 */
public class Customer extends User {
    private String email;
    private double creditBalance;

    // Construtor
    public Customer(int id, String name, String phone, String address, String email) {
        super(id, name, phone, address); // Chama construtor da superclasse
        this.email = email;
        this.creditBalance = 0.0;
    }

    // Sobrescrita de método abstrato - Polimorfismo
    @Override
    public String showData() {
        return String.format("Cliente: %s | Telefone: %s | Email: %s | Endereço: %s",
                getName(), getPhone(), email, getAddress());
    }

    // Sobrescrita de método abstrato
    @Override
    public boolean login(String credential) {
        return this.email.equals(credential);
    }

    // Getters e Setters específicos
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }
}
