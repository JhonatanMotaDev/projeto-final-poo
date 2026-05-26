package model;

/**
 * Classe Product - representa um produto do restaurante
 * Demonstra Encapsulamento e uso de objetos
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private String category;

    // Construtor
    public Product(int id, String name, double price, String description, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f", name, price);
    }
}
