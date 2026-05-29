package model;

public class Customer extends User {
    private String email;
    private double creditBalance;

    public Customer(int id, String name, String phone, String address, String email) {
        super(id, name, phone, address);
        this.email = email;
        this.creditBalance = 0.0;
    }

    @Override
    public String showData() {
        return String.format("Cliente: %s | Telefone: %s | Email: %s | Endereço: %s",
                getName(), getPhone(), email, getAddress());
    }

    @Override
    public boolean login(String credential) {
        return this.email.equals(credential);
    }

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
