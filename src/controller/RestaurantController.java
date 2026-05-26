package controller;

import model.Product;
import model.Restaurant;
import service.DataService;
import java.util.List;

/**
 * Controller para gerenciar operações de Restaurant
 */
public class RestaurantController {
    private DataService dataService;

    public RestaurantController() {
        this.dataService = DataService.getInstance();
    }

    public void createRestaurant(String name, String category, String address, String phone) {
        Restaurant restaurant = new Restaurant(0, name, category, address, phone);
        dataService.addRestaurant(restaurant);
    }

    public void addProductToRestaurant(Restaurant restaurant, String name, double price, String description, String category) {
        Product product = new Product(dataService.getNextProductId(), name, price, description, category);
        restaurant.addProduct(product);
    }

    public List<Restaurant> getAllRestaurants() {
        return dataService.getAllRestaurants();
    }
}
