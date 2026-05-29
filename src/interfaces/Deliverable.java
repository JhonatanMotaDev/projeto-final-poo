package interfaces;

public interface Deliverable {
    void startDelivery();
    void finishDelivery();
    void updateStatus(String newStatus);
}
