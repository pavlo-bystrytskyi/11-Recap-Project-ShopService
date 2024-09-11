package sales.orders;

import sales.products.Product;
import sales.products.ProductRepository;

public class OrderItem {
    private static int incrementId = 0;
    private final int id;
    private final int productId;
    private final int quantity;
    private final double totalPrice;

    private OrderItem(int id, int productId, int quantity, double totalPrice) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public static OrderItem create(int productId, double productPrice, int quantity) {
        Product product = ProductRepository.get(productId);
        if (!product.isAvailable()) {
            throw new IllegalArgumentException("The product with id " + productId + " is not available.");
        }
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("The requested quantity for product with id " + productId + " is not available.");
        }
        int id = ++incrementId;
        double totalPrice = productPrice * quantity;
        return new OrderItem(id, productId, quantity, totalPrice);
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}