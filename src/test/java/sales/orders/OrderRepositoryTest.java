package sales.orders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sales.products.Product;
import sales.products.ProductRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class OrderRepositoryTest {
    private static final String CUSTOMER_FIRST_NAME = "John";
    private static final String CUSTOMER_LAST_NAME = "Doe";
    private static final String CUSTOMER_EMAIL = "test@email.com";
    private final Map<Integer, OrderItem> orderItems = new HashMap<>();
    private Order order;

    @BeforeEach
    void initEach() {
        Product mouse = Product.create("Mouse", 8, 50, true);
        Product usbStick = Product.create("USB stick", 15, 50, true);
        ProductRepository.save(mouse);
        ProductRepository.save(usbStick);
        OrderItem mouseOrderItem = OrderItem.create(mouse.getId(), mouse.getPrice(), 1);
        OrderItem usbStickOrderItem = OrderItem.create(usbStick.getId(), usbStick.getPrice(), 2);
        orderItems.clear();
        orderItems.put(mouseOrderItem.getId(), mouseOrderItem);
        orderItems.put(usbStickOrderItem.getId(), usbStickOrderItem);
        this.order = Order.create(CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, CUSTOMER_EMAIL, orderItems);
    }

    @Test
    void getTest_getExistingOrder() {
        OrderRepository orderRepository = new OrderRepository();
        orderRepository.save(this.order);
        Order order = orderRepository.get(this.order.getId());

        Assertions.assertEquals(this.order, order);
    }

    @Test
    void getTest_getNonExistingOrder() {
        OrderRepository orderRepository = new OrderRepository();

        assertThrowsExactly(NoSuchElementException.class, () -> orderRepository.get(this.order.getId() + 1));
    }

    @Test
    void saveTest_saveNull() {
        OrderRepository orderRepository = new OrderRepository();

        assertThrowsExactly(IllegalArgumentException.class, () -> orderRepository.save(null));
    }

    @Test
    void getAllTest_storageSizeChange() {
        OrderRepository orderRepository = new OrderRepository();
        int initialSize = orderRepository.getAll().size();
        Order order = Order.create(CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, CUSTOMER_EMAIL, orderItems);
        orderRepository.save(order);
        int newSize = orderRepository.getAll().size();

        assertEquals(1, newSize - initialSize);
    }
}