package sales.command;

public interface CommandInterface {
    public String getTitle();
    public String getInfo();
    public CommandInterface[] getOptions();
    public void execute(String input);
    public String getResult();
}
