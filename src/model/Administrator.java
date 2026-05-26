package model;

/**
 * Classe Administrator - demonstra Herança
 * Representa um administrador do sistema
 */
public class Administrator extends User {
    private String username;
    private String password;
    private String accessLevel;

    // Construtor
    public Administrator(int id, String name, String phone, String address, String username, String password) {
        super(id, name, phone, address);
        this.username = username;
        this.password = password;
        this.accessLevel = "FULL";
    }

    // Sobrescrita de método abstrato - Polimorfismo
    @Override
    public String showData() {
        return String.format("Administrador: %s | Username: %s | Nível: %s",
                getName(), username, accessLevel);
    }

    // Sobrescrita de método abstrato
    @Override
    public boolean login(String credential) {
        return this.username.equals(credential);
    }

    // Método específico do administrador
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Getters e Setters específicos
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
