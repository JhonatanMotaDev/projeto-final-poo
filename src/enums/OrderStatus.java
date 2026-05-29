package enums;

public enum OrderStatus {
    PENDING("Pendente"),
    PREPARING("Em Preparação"),
    OUT_FOR_DELIVERY("Saiu para Entrega"),
    DELIVERED("Entregue"),
    CANCELED("Cancelado");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
