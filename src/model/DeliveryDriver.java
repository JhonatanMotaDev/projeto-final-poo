package model;

/**
 * Classe DeliveryDriver - demonstra Herança
 * Representa um entregador no sistema
 */
public class DeliveryDriver extends User {
    private String vehicleType;
    private String licensePlate;
    private boolean available;

    // Construtor
    public DeliveryDriver(int id, String name, String phone, String address, String vehicleType, String licensePlate) {
        super(id, name, phone, address);
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.available = true;
    }

    // Sobrescrita de método abstrato - Polimorfismo
    @Override
    public String showData() {
        return String.format("Entregador: %s | Veículo: %s | Placa: %s | Status: %s",
                getName(), vehicleType, licensePlate, available ? "Disponível" : "Ocupado");
    }

    // Sobrescrita de método abstrato
    @Override
    public boolean login(String credential) {
        return this.getPhone().equals(credential);
    }

    // Getters e Setters específicos
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
