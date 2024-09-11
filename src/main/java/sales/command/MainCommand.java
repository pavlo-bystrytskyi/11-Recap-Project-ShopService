package sales.command;

public class MainCommand implements CommandInterface {
    private static final String INFO = "Select a command";
    private static final String TITLE = "Main menu";
    private static CommandInterface[] commands = {
            new CreateProductCommand(),
            new ShowProductCommand(),
            new ShowAllProductsCommand()
    };

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public String getInfo() {
        return INFO;
    }

    @Override
    public CommandInterface[] getOptions() {
        return commands;
    }

    @Override
    public void execute(String input) {
        // Do nothing.
    }

    @Override
    public String getResult() {
        return "";
    }
}
