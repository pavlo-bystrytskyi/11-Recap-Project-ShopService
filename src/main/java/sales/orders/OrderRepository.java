package sales.orders;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class OrderRepository implements OrderRepositoryInterface {
    private static final Map<Integer, Order> orders = new HashMap<Integer, Order>();

    public Order get(int id) {
        Order order = orders.get(id);
        if (order == null) {
            throw new NoSuchElementException("Order with id " + id + " not found");
        }

        return order;
    }

    public void save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        orders.put(order.getId(), order);
    }

    public Map<Integer, Order> getAll() {
        return orders;
    }
}
