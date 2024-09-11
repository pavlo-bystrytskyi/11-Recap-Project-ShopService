package sales.products;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class ProductRepositoryTest {
    private final Product mouse = Product.create("Mouse", 8, 50, true);
    private final Product usbStick = Product.create("USB stick", 15, 50, true);

    @Test
    void getTest_getExistentProduct() {
        ProductRepository.save(mouse);
        ProductRepository.save(usbStick);
        Product product = ProductRepository.get(mouse.getId());

        assertEquals(mouse, product);
    }

    @Test
    void getTest_getNonExistentProduct() {
        ProductRepository.save(usbStick);

        assertThrowsExactly(NoSuchElementException.class, () -> ProductRepository.get(mouse.getId()));
    }

    @Test
    void saveTest_saveNull() {
        assertThrowsExactly(IllegalArgumentException.class, () -> ProductRepository.save(null));
    }

    @Test
    void getAllTest_storageSizeChange() {
        int initialSize = ProductRepository.getAll().size();
        ProductRepository.save(mouse);
        ProductRepository.save(usbStick);
        int newSize = ProductRepository.getAll().size();

        assertEquals(2, newSize - initialSize);
    }
}