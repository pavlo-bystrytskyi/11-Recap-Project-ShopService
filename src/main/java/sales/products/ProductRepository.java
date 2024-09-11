package sales.products;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ProductRepository {
    private static Map<Integer, Product> products = new HashMap<>();

    public static Product get(int id) {
        Product product = products.get(id);
        if (product == null) {
            throw new NoSuchElementException("Product with id " + id + " not found");
        }

        return product;
    }

    public static void save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        products.put(product.getId(), product);
    }

    public static Map<Integer, Product> getAll() {
        return products;
    }
}
