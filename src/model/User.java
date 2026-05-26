package model;

/**
 * Classe abstrata User - demonstra Abstração e Herança
 * Serve como base para todos os tipos de usuários do sistema
 */
public abstract class User {
    // Encapsulamento: atributos privados
    private int id;
    private String name;
    private String phone;
    private String address;

    // Construtor
    public User(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    // Método abstrato - deve ser implementado pelas subclasses (Polimorfismo)
    public abstract String showData();

    // Método abstrato para login
    public abstract boolean login(String credential);

    // Getters e Setters - Encapsulamento
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name;
    }
}
