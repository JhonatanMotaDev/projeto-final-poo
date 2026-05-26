package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Restaurant - representa um restaurante
 * Demonstra Associação (tem uma lista de produtos)
 */
public class Restaurant {
    private int id;
    private String name;
    private String category;
    private String address;
    private String phone;
    // Associação: Restaurant tem vários Products
    private List<Product> products;

    // Construtor
    public Restaurant(int id, String name, String category, String address, String phone) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.address = address;
        this.phone = phone;
        this.products = new ArrayList<>();
    }

    // Método para adicionar produto
    public void addProduct(Product product) {
        this.products.add(product);
    }

    // Método para remover produto
    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    // Getters e Setters
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", name, category);
    }
}
