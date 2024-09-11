package sales.products;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private static Map<Integer, Product> products = new HashMap<>();

    public static Product get(int id) {
        return products.get(id);
    }

    public static void save(Product product) {
        products.put(product.getId(), product);
    }

    public static Map<Integer, Product> getAll() {
        return products;
    }
}
