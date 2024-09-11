package sales.products;

public class Product {
    private static int incrementId = 0;
    private final int id;
    private String name;
    private double price;
    private int quantity;
    private boolean available;

    private Product(int id, String name, double price, int quantity, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
    }

    public static Product create(String name, double price, int quantity, boolean available) {
        int id = ++incrementId;
        return new Product(id, name, price, quantity, available);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", available=" + available +
                '}';
    }
}
