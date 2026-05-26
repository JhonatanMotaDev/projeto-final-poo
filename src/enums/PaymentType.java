package enums;

/**
 * Enum para tipos de pagamento
 */
public enum PaymentType {
    CREDIT_CARD("Cartão de Crédito"),
    DEBIT_CARD("Cartão de Débito"),
    CASH("Dinheiro"),
    PIX("PIX");

    private final String description;

    PaymentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
