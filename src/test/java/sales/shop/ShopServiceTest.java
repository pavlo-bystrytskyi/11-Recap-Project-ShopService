package sales.shop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sales.orders.Order;
import sales.orders.OrderRepository;
import sales.orders.OrderRepositoryInterface;
import sales.products.Product;
import sales.products.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShopServiceTest {
    private static final String CUSTOMER_FIRST_NAME = "John";
    private static final String CUSTOMER_LAST_NAME = "Doe";
    private static final String CUSTOMER_EMAIL = "test@email.com";
    private static final Product mouse = Product.create("Mouse", 8, 50, true);
    private static final Product usbStick = Product.create("USB stick", 15, 50, true);
    private final OrderRepositoryInterface orderRepository = new OrderRepository();

    @BeforeAll
    static void initAll() {
        ProductRepository.save(mouse);
        ProductRepository.save(usbStick);
    }

    @Test
    void clearTest_isCleared() {
        ShopService shopService = new ShopService(orderRepository);
        shopService.setCustomerEmail(CUSTOMER_EMAIL);
        shopService.setCustomerFirstName(CUSTOMER_FIRST_NAME);
        shopService.setCustomerLastName(CUSTOMER_LAST_NAME);
        shopService.addProduct(mouse.getId(), 1);
        shopService.addProduct(usbStick.getId(), 2);
        shopService.clear();

        assertThatThrownBy(() -> shopService.placeOrder())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void placeOrderTest_endToEnd() {
        ShopService shopService = new ShopService(orderRepository);
        shopService.setCustomerEmail(CUSTOMER_EMAIL);
        shopService.setCustomerFirstName(CUSTOMER_FIRST_NAME);
        shopService.setCustomerLastName(CUSTOMER_LAST_NAME);
        shopService.addProduct(mouse.getId(), 1);
        shopService.addProduct(usbStick.getId(), 2);
        Order order = shopService.placeOrder();

        assertThat(order.getOrderItems()).hasSize(2);
        assertThat(order).isNotNull();
        assertThat(order.getCustomerEmail()).isEqualTo(CUSTOMER_EMAIL);
        assertThat(order.getCustomerFirstName()).isEqualTo(CUSTOMER_FIRST_NAME);
        assertThat(order.getCustomerLastName()).isEqualTo(CUSTOMER_LAST_NAME);
        assertThat(order.getTotalPrice()).isEqualTo(38);
    }

    @Test
    void placeOrderTest_emailNotSet() {
        ShopService shopService = new ShopService(orderRepository);
        shopService.setCustomerFirstName(CUSTOMER_FIRST_NAME);
        shopService.setCustomerLastName(CUSTOMER_LAST_NAME);
        shopService.addProduct(mouse.getId(), 1);
        shopService.addProduct(usbStick.getId(), 2);

        assertThatThrownBy(() -> shopService.placeOrder())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Customer email not set");
    }

    @Test
    void placeOrderTest_firstNameNotSet() {
        ShopService shopService = new ShopService(orderRepository);
        shopService.setCustomerEmail(CUSTOMER_EMAIL);
        shopService.setCustomerLastName(CUSTOMER_LAST_NAME);
        shopService.addProduct(mouse.getId(), 1);
        shopService.addProduct(usbStick.getId(), 2);

        assertThatThrownBy(() -> shopService.placeOrder())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Customer first name not set");
    }

    @Test
    void placeOrderTest_lastNameNotSet() {
        ShopService shopService = new ShopService(orderRepository);
        shopService.setCustomerEmail(CUSTOMER_EMAIL);
        shopService.setCustomerFirstName(CUSTOMER_FIRST_NAME);
        shopService.addProduct(mouse.getId(), 1);
        shopService.addProduct(usbStick.getId(), 2);

        assertThatThrownBy(() -> shopService.placeOrder())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Customer last name not set");
    }

    @Test
    void placeOrderTest_productsNotSet() {
        ShopService shopService = new ShopService(orderRepository);
        shopService.setCustomerEmail(CUSTOMER_EMAIL);
        shopService.setCustomerFirstName(CUSTOMER_FIRST_NAME);
        shopService.setCustomerLastName(CUSTOMER_LAST_NAME);

        assertThatThrownBy(() -> shopService.placeOrder())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Order products not set");
    }
}