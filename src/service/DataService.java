package service;

import java.util.ArrayList;
import java.util.List;
import model.*;

public class DataService {
    private static DataService instance;
    
    private List<Customer> customers;
    private List<DeliveryDriver> drivers;
    private List<Administrator> administrators;
    private List<Restaurant> restaurants;
    private List<Order> orders;
    
    private int customerIdCounter = 1;
    private int driverIdCounter = 1;
    private int adminIdCounter = 1;
    private int restaurantIdCounter = 1;
    private int productIdCounter = 1;
    private int orderIdCounter = 1;
    private int paymentIdCounter = 1;

    private DataService() {
        customers = new ArrayList<>();
        drivers = new ArrayList<>();
        administrators = new ArrayList<>();
        restaurants = new ArrayList<>();
        orders = new ArrayList<>();
        initializeMockData();
    }

    public static DataService getInstance() {
        if (instance == null) {
            instance = new DataService();
        }
        return instance;
    }

    public void addCustomer(Customer customer) {
        customer.setId(customerIdCounter++);
        customers.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public void addDriver(DeliveryDriver driver) {
        driver.setId(driverIdCounter++);
        drivers.add(driver);
    }

    public List<DeliveryDriver> getAllDrivers() {
        return drivers;
    }

    public List<DeliveryDriver> getAvailableDrivers() {
        List<DeliveryDriver> available = new ArrayList<>();
        for (DeliveryDriver driver : drivers) {
            if (driver.isAvailable()) {
                available.add(driver);
            }
        }
        return available;
    }

    public void addAdministrator(Administrator administrator) {
        administrator.setId(adminIdCounter++);
        administrators.add(administrator);
    }

    public List<Administrator> getAllAdministrators() {
        return administrators;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurant.setId(restaurantIdCounter++);
        restaurants.add(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public void addOrder(Order order) {
        order.setId(orderIdCounter++);
        orders.add(order);
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public int getNextProductId() {
        return productIdCounter++;
    }

    public int getNextPaymentId() {
        return paymentIdCounter++;
    }

    private void initializeMockData() {
        Customer c1 = new Customer(customerIdCounter++, "Joao Silva", "11987654321", "Rua das Flores, 123", "joao@email.com");
        Customer c2 = new Customer(customerIdCounter++, "Maria Santos", "11976543210", "Av. Paulista, 1000", "maria@email.com");
        Customer c3 = new Customer(customerIdCounter++, "Pedro Oliveira", "11965432109", "Rua Augusta, 500", "pedro@email.com");
        Customer c4 = new Customer(customerIdCounter++, "Ana Costa", "11954321098", "Rua Oscar Freire, 200", "ana@email.com");
        Customer c5 = new Customer(customerIdCounter++, "Lucas Ferreira", "11943210987", "Av. Brigadeiro, 789", "lucas@email.com");
        Customer c6 = new Customer(customerIdCounter++, "Juliana Alves", "11932109876", "Rua Consolacao, 456", "juliana@email.com");
        Customer c7 = new Customer(customerIdCounter++, "Rafael Souza", "11921098765", "Av. Reboucas, 321", "rafael@email.com");
        Customer c8 = new Customer(customerIdCounter++, "Camila Lima", "11910987654", "Rua Haddock Lobo, 654", "camila@email.com");
        Customer c9 = new Customer(customerIdCounter++, "Bruno Martins", "11909876543", "Av. Faria Lima, 987", "bruno@email.com");
        Customer c10 = new Customer(customerIdCounter++, "Patricia Rocha", "11898765432", "Rua Pamplona, 147", "patricia@email.com");
        Customer c11 = new Customer(customerIdCounter++, "Rodrigo Dias", "11887654321", "Av. Ibirapuera, 258", "rodrigo@email.com");
        Customer c12 = new Customer(customerIdCounter++, "Fernanda Gomes", "11876543210", "Rua Vergueiro, 369", "fernanda@email.com");
        Customer c13 = new Customer(customerIdCounter++, "Thiago Barbosa", "11865432109", "Av. Ipiranga, 741", "thiago@email.com");
        Customer c14 = new Customer(customerIdCounter++, "Mariana Cardoso", "11854321098", "Rua da Mooca, 852", "mariana@email.com");
        Customer c15 = new Customer(customerIdCounter++, "Gabriel Ribeiro", "11843210987", "Av. Tiradentes, 963", "gabriel@email.com");
        customers.add(c1); customers.add(c2); customers.add(c3); customers.add(c4); customers.add(c5);
        customers.add(c6); customers.add(c7); customers.add(c8); customers.add(c9); customers.add(c10);
        customers.add(c11); customers.add(c12); customers.add(c13); customers.add(c14); customers.add(c15);
        
        DeliveryDriver d1 = new DeliveryDriver(driverIdCounter++, "Carlos Moto", "11912345678", "Rua dos Entregadores, 10", "Moto", "ABC-1234");
        DeliveryDriver d2 = new DeliveryDriver(driverIdCounter++, "Roberto Bike", "11923456789", "Av. dos Ciclistas, 20", "Bicicleta", "BIK-5678");
        DeliveryDriver d3 = new DeliveryDriver(driverIdCounter++, "Fernando Carro", "11934567890", "Rua do Transporte, 30", "Carro", "XYZ-9012");
        DeliveryDriver d4 = new DeliveryDriver(driverIdCounter++, "Marcos Silva", "11945678901", "Av. Delivery, 40", "Moto", "DEF-3456");
        DeliveryDriver d5 = new DeliveryDriver(driverIdCounter++, "Paulo Santos", "11956789012", "Rua Rapida, 50", "Moto", "GHI-7890");
        DeliveryDriver d6 = new DeliveryDriver(driverIdCounter++, "Andre Costa", "11967890123", "Av. Expressa, 60", "Carro", "JKL-1234");
        DeliveryDriver d7 = new DeliveryDriver(driverIdCounter++, "Ricardo Lima", "11978901234", "Rua Veloz, 70", "Moto", "MNO-5678");
        DeliveryDriver d8 = new DeliveryDriver(driverIdCounter++, "Gustavo Alves", "11989012345", "Av. Agil, 80", "Bicicleta", "PQR-9012");
        drivers.add(d1); drivers.add(d2); drivers.add(d3); drivers.add(d4);
        drivers.add(d5); drivers.add(d6); drivers.add(d7); drivers.add(d8);
        
        Restaurant r1 = new Restaurant(restaurantIdCounter++, "Pizzaria Bella", "Italiana", "Rua da Pizza, 100", "11988887777");
        r1.addProduct(new Product(productIdCounter++, "Pizza Margherita", 45.90, "Molho, mussarela e manjericao", "Pizza"));
        r1.addProduct(new Product(productIdCounter++, "Pizza Calabresa", 48.90, "Molho, mussarela e calabresa", "Pizza"));
        r1.addProduct(new Product(productIdCounter++, "Pizza Portuguesa", 52.90, "Molho, mussarela, presunto, ovos e cebola", "Pizza"));
        r1.addProduct(new Product(productIdCounter++, "Pizza Quatro Queijos", 54.90, "Mussarela, gorgonzola, provolone e parmesao", "Pizza"));
        r1.addProduct(new Product(productIdCounter++, "Pizza Frango Catupiry", 49.90, "Frango desfiado com catupiry", "Pizza"));
        restaurants.add(r1);
        
        Restaurant r2 = new Restaurant(restaurantIdCounter++, "Burger House", "Hamburgueria", "Av. do Hamburguer, 200", "11977776666");
        r2.addProduct(new Product(productIdCounter++, "X-Burger", 28.90, "Hamburguer, queijo, alface e tomate", "Lanche"));
        r2.addProduct(new Product(productIdCounter++, "X-Bacon", 32.90, "Hamburguer, queijo, bacon e molho especial", "Lanche"));
        r2.addProduct(new Product(productIdCounter++, "X-Tudo", 38.90, "Hamburguer, queijo, bacon, ovo, presunto e salada", "Lanche"));
        r2.addProduct(new Product(productIdCounter++, "X-Salada", 26.90, "Hamburguer, queijo, alface, tomate e milho", "Lanche"));
        r2.addProduct(new Product(productIdCounter++, "X-Frango", 29.90, "Frango grelhado, queijo e salada", "Lanche"));
        restaurants.add(r2);
        
        Restaurant r3 = new Restaurant(restaurantIdCounter++, "Sushi Master", "Japonesa", "Rua do Japao, 300", "11966665555");
        r3.addProduct(new Product(productIdCounter++, "Combo Sushi 20 pecas", 65.90, "Variado de sushis e sashimis", "Japones"));
        r3.addProduct(new Product(productIdCounter++, "Temaki Salmao", 22.90, "Temaki de salmao com cream cheese", "Japones"));
        r3.addProduct(new Product(productIdCounter++, "Hot Roll", 28.90, "Hot roll empanado variado", "Japones"));
        r3.addProduct(new Product(productIdCounter++, "Sashimi Salmao", 45.90, "15 fatias de salmao fresco", "Japones"));
        r3.addProduct(new Product(productIdCounter++, "Combo Temaki", 55.90, "3 temakis variados", "Japones"));
        restaurants.add(r3);
        
        Restaurant r4 = new Restaurant(restaurantIdCounter++, "Churrascaria Grill", "Churrascaria", "Av. da Carne, 400", "11955554444");
        r4.addProduct(new Product(productIdCounter++, "Picanha 500g", 78.90, "Picanha grelhada com arroz e farofa", "Carne"));
        r4.addProduct(new Product(productIdCounter++, "Costela BBQ", 68.90, "Costela ao molho barbecue", "Carne"));
        r4.addProduct(new Product(productIdCounter++, "Frango Grelhado", 42.90, "Frango grelhado com legumes", "Carne"));
        r4.addProduct(new Product(productIdCounter++, "Maminha 400g", 65.90, "Maminha grelhada com vinagrete", "Carne"));
        r4.addProduct(new Product(productIdCounter++, "Linguica Artesanal", 38.90, "Linguica toscana com mandioca", "Carne"));
        restaurants.add(r4);
        
        Restaurant r5 = new Restaurant(restaurantIdCounter++, "Pasta & Vino", "Italiana", "Rua Italia, 500", "11944443333");
        r5.addProduct(new Product(productIdCounter++, "Spaghetti Carbonara", 42.90, "Massa com bacon, ovos e queijo", "Massa"));
        r5.addProduct(new Product(productIdCounter++, "Lasanha Bolonhesa", 48.90, "Lasanha com molho bolonhesa", "Massa"));
        r5.addProduct(new Product(productIdCounter++, "Ravioli Funghi", 52.90, "Ravioli recheado com cogumelos", "Massa"));
        r5.addProduct(new Product(productIdCounter++, "Fettuccine Alfredo", 45.90, "Massa com molho branco", "Massa"));
        restaurants.add(r5);
        
        Restaurant r6 = new Restaurant(restaurantIdCounter++, "Taco Loco", "Mexicana", "Av. Mexico, 600", "11933332222");
        r6.addProduct(new Product(productIdCounter++, "Tacos Carne", 35.90, "3 tacos com carne moida", "Mexicano"));
        r6.addProduct(new Product(productIdCounter++, "Burrito Frango", 38.90, "Burrito com frango e feijao", "Mexicano"));
        r6.addProduct(new Product(productIdCounter++, "Quesadilla", 32.90, "Tortilha com queijo e frango", "Mexicano"));
        r6.addProduct(new Product(productIdCounter++, "Nachos Supreme", 28.90, "Nachos com queijo e guacamole", "Mexicano"));
        restaurants.add(r6);
        
        Restaurant r7 = new Restaurant(restaurantIdCounter++, "Sabor Mineiro", "Brasileira", "Rua Minas, 700", "11922221111");
        r7.addProduct(new Product(productIdCounter++, "Feijao Tropeiro", 38.90, "Feijao com linguica e torresmo", "Brasileira"));
        r7.addProduct(new Product(productIdCounter++, "Frango com Quiabo", 42.90, "Frango caipira com quiabo", "Brasileira"));
        r7.addProduct(new Product(productIdCounter++, "Tutu de Feijao", 35.90, "Tutu com bisteca e couve", "Brasileira"));
        r7.addProduct(new Product(productIdCounter++, "Costelinha Mineira", 55.90, "Costelinha com angu", "Brasileira"));
        restaurants.add(r7);
        
        Restaurant r8 = new Restaurant(restaurantIdCounter++, "Veggie Life", "Vegetariana", "Av. Verde, 800", "11911110000");
        r8.addProduct(new Product(productIdCounter++, "Bowl Vegano", 32.90, "Quinoa, legumes e tofu", "Vegetariano"));
        r8.addProduct(new Product(productIdCounter++, "Hamburguer Vegetal", 28.90, "Hamburguer de grao de bico", "Vegetariano"));
        r8.addProduct(new Product(productIdCounter++, "Salada Completa", 25.90, "Mix de folhas e legumes", "Vegetariano"));
        r8.addProduct(new Product(productIdCounter++, "Wrap Vegano", 29.90, "Wrap com hummus e vegetais", "Vegetariano"));
        restaurants.add(r8);
    }

    public void clearAllData() {
        customers.clear();
        drivers.clear();
        restaurants.clear();
        orders.clear();
        customerIdCounter = 1;
        driverIdCounter = 1;
        restaurantIdCounter = 1;
        orderIdCounter = 1;
        productIdCounter = 1;
        paymentIdCounter = 1;
    }
}
