package sales.command;

import sales.products.ProductRepository;

public class ShowAllProductsCommand implements CommandInterface {
    private static final String TITLE = "Show all products info";
    private static final String INFO = "Press enter to see all info";
    private String result = "";

    @Override
    public CommandInterface[] getOptions() {
        return new CommandInterface[0];
    }

    @Override
    public String getInfo() {
        return INFO;
    }

    @Override
    public String getResult() {
        return this.result;
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void execute(String input) {
        var products = ProductRepository.getAll();
        this.result = products.values().toString();
    }
}
