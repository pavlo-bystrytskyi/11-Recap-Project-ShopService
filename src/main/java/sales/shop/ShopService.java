package sales.shop;

import sales.orders.Order;
import sales.orders.OrderItem;
import sales.orders.OrderRepositoryInterface;
import sales.products.Product;
import sales.products.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class ShopService {
    private final Map<Integer, OrderItem> orderItems = new HashMap<>();
    private OrderRepositoryInterface orderRepository;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;

    public ShopService(OrderRepositoryInterface orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void addProduct(int productId, int quantity) {
        Product product = ProductRepository.get(productId);
        OrderItem orderItem = OrderItem.create(productId, product.getPrice(), quantity);
        orderItems.put(orderItem.getId(), orderItem);
    }

    public void clear() {
        orderItems.clear();
        this.customerFirstName = null;
        this.customerLastName = null;
        this.customerEmail = null;
    }

    public Order placeOrder() {
        if (this.customerFirstName == null) {
            throw new IllegalStateException("You must provide a customer first name");
        }
        if (this.customerLastName == null) {
            throw new IllegalStateException("You must provide a customer last name");
        }
        if (this.customerEmail == null) {
            throw new IllegalStateException("You must provide a customer email");
        }
        if (this.orderItems.size() == 0) {
            throw new IllegalStateException("You must provide at least one order item");
        }
        Order order = Order.create(customerFirstName, customerLastName, customerEmail, orderItems);
        this.orderRepository.save(order);
        this.clear();
        return order;
    }
}
