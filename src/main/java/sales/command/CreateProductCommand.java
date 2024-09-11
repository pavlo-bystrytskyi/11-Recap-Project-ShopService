package sales.command;

import sales.products.Product;
import sales.products.ProductRepository;

public class CreateProductCommand implements CommandInterface {
    private static final String INFO = "Please insert values separated by a semicolon: name; price; quantity";
    private static final String TITLE = "Create a product";
    private String result = "";

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public String getResult() {
        return this.result;
    }

    @Override
    public String getInfo() {
        return INFO;
    }

    @Override
    public CommandInterface[] getOptions() {
        return new CommandInterface[0];
    }

    @Override
    public void execute(String input) {
        String[] parts = input.split(";");
        if (parts.length != 3) {
            this.result = "Incorrect number of arguments: " + input;
            return;
        }
        String name = parts[0].trim();
        try {
            double price = Double.parseDouble(parts[1].trim());
            int quantity = Integer.parseInt(parts[2].trim());
            Product product = Product.create(name, price, quantity, true);
            ProductRepository.save(product);
            this.result = String.format("Product %s with id %d created", product.getName(), product.getId());
        } catch (NumberFormatException e) {
            this.result = "Incorrect input, please try again.";
        }
    }
}
