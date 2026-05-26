package interfaces;

/**
 * Interface que define o contrato para objetos que podem ser entregues
 * Demonstra o conceito de Interface em POO
 */
public interface Deliverable {
    void startDelivery();
    void finishDelivery();
    void updateStatus(String newStatus);
}
