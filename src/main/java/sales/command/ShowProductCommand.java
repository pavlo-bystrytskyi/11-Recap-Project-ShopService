package sales.command;

import sales.products.Product;
import sales.products.ProductRepository;

import java.util.NoSuchElementException;

public class ShowProductCommand implements CommandInterface {
    private static final String TITLE = "Show product info";
    private static final String INFO = "Insert product id";
    private String result = "";

    @Override
    public String getInfo() {
        return INFO;
    }

    @Override
    public String getResult() {
        return this.result;
    }

    @Override
    public CommandInterface[] getOptions() {
        return new CommandInterface[0];
    }

    @Override
    public void execute(String input) {
        try {
            int productId = Integer.parseInt(input.trim());
            Product product = ProductRepository.get(productId);
            this.result = product.toString();
        } catch (NumberFormatException e) {
            this.result = "Incorrect input, please try again.";
        } catch (NoSuchElementException e) {
            this.result = e.getMessage();
        }
    }

    @Override
    public String getTitle() {
        return TITLE;
    }
}
