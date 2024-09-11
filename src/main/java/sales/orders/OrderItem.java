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

    public static OrderItem create(int productId,  double productPrice, int quantity) {
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