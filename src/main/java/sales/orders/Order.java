package sales.orders;

import sales.products.Product;
import sales.products.ProductRepository;

import java.util.Map;

public class Order {
    private static int incrementId = 0;
    private final int id;
    private final String customerFirstName;
    private final String customerLastName;
    private final String customerEmail;
    private final Map<Integer, OrderItem> orderItems;
    private final double totalPrice;

    private Order(int id, String customerFirstName, String customerLastName, String customerEmail, Map<Integer, OrderItem> orderItems) {
        this.id = id;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
        this.orderItems = orderItems;
        this.totalPrice = orderItems.values().stream().map(OrderItem::getTotalPrice).reduce(0.0, Double::sum);
    }

    public static Order create(String customerFirstName, String customerLastName, String customerEmail, Map<Integer, OrderItem> orderItems) {
        int id = ++incrementId;
        for (OrderItem orderItem : orderItems.values()) {
            Product product = ProductRepository.get(orderItem.getProductId());
            if (orderItem.getQuantity() > product.getQuantity()) {
                throw new IllegalArgumentException("The requested quantity for product with id " + orderItem.getProductId() + " is not available.");
            }
            if (!product.isAvailable()) {
                throw new IllegalArgumentException("The requested product with id " + orderItem.getProductId() + " is not available.");
            }
        }
        for (OrderItem orderItem : orderItems.values()) {
            Product product = ProductRepository.get(orderItem.getProductId());
            product.setQuantity(product.getQuantity() - orderItem.getQuantity());
            ProductRepository.save(product);
        }
        return new Order(id, customerFirstName, customerLastName, customerEmail, orderItems);
    }

    public int getId() {
        return this.id;
    }

    public String getCustomerFirstName() {
        return this.customerFirstName;
    }

    public String getCustomerLastName() {
        return this.customerLastName;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public Map<Integer, OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }
}
