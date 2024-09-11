package sales.products;

public class Product {
    private static int incrementId = 0;
    private final int id;
    private String name;
    private double price;
    private int quantity;
    private boolean available;

    private Product(int id, String name, int price, int quantity, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
    }

    public static Product create(String name, int price, int quantity, boolean available) {
        int id = ++incrementId;
        return new Product(id, name, price, quantity, available);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isAvailable() {
        return available;
    }
}
