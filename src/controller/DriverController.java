package controller;

import model.DeliveryDriver;
import service.DataService;
import java.util.List;

/**
 * Controller para gerenciar operações de DeliveryDriver
 */
public class DriverController {
    private DataService dataService;

    public DriverController() {
        this.dataService = DataService.getInstance();
    }

    public void createDriver(String name, String phone, String address, String vehicleType, String licensePlate) {
        DeliveryDriver driver = new DeliveryDriver(0, name, phone, address, vehicleType, licensePlate);
        dataService.addDriver(driver);
    }

    public List<DeliveryDriver> getAllDrivers() {
        return dataService.getAllDrivers();
    }

    public List<DeliveryDriver> getAvailableDrivers() {
        return dataService.getAvailableDrivers();
    }
}
