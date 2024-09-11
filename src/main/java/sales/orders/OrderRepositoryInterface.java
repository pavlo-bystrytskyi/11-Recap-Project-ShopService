package sales.orders;

import java.util.Map;

public interface OrderRepositoryInterface {
    Order get(int id);

    void save(Order order);

    Map<Integer, Order> getAll();
}
