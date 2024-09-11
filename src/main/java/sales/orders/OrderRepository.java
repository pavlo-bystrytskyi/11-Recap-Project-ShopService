package sales.orders;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository implements OrderRepositoryInterface {
    private static final Map<Integer, Order> orders = new HashMap<Integer, Order>();

    public Order get(int id) {
        return orders.get(id);
    }

    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    public Map<Integer, Order> getAll() {
        return orders;
    }
}
