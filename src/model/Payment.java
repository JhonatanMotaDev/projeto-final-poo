package model;

import enums.PaymentType;

/**
 * Classe Payment - representa um pagamento
 * Demonstra uso de Enum e encapsulamento
 */
public class Payment {
    private int id;
    private PaymentType paymentType;
    private double value;
    private boolean confirmed;

    // Construtor
    public Payment(int id, PaymentType paymentType, double value) {
        this.id = id;
        this.paymentType = paymentType;
        this.value = value;
        this.confirmed = false;
    }

    // Método para confirmar pagamento
    public boolean confirmPayment() {
        this.confirmed = true;
        return true;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f - %s",
                paymentType.getDescription(), value, confirmed ? "Confirmado" : "Pendente");
    }
}
