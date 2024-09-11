package sales.orders;

import java.util.Map;

public class Order {
    private static int incrementId = 0;
    private final int id;
    private final String customerFirstName;
    private final String customerLastName;
    private final String customerEmail;
    private final Map<Integer, OrderItem> orderItems;

    private Order(int id, String customerFirstName, String customerLastName, String customerEmail, Map<Integer, OrderItem> orderItems) {
        this.id = id;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
        this.orderItems = orderItems;
    }

    public static Order create(String customerFirstName, String customerLastName, String customerEmail, Map<Integer, OrderItem> orderItems) {
        int id = ++incrementId;
        return new Order(id, customerFirstName, customerLastName, customerEmail, orderItems);
    }

    public int getId() {
        return id;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Map<Integer, OrderItem> getOrderItems() {
        return orderItems;
    }
}
