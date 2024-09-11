package sales.command;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InteractiveProductManagement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine;
        int inputOption;
        CommandInterface mainCommand = new MainCommand();
        CommandInterface nextCommand = mainCommand;
        CommandInterface[] options;
        while (true) {
            System.out.println();
            options = nextCommand.getOptions();
            System.out.println(prepareInfo(nextCommand));
            if (options.length != 0) {
                try {
                    inputOption = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect input, please try again.");
                    // Drop line.
                    scanner.nextLine();
                    continue;
                }
                if (inputOption < 0 || inputOption > options.length) {
                    continue;
                }
                nextCommand = options[inputOption];
            } else {
                // Drop line.
                scanner.nextLine();
                inputLine = scanner.nextLine();
                nextCommand.execute(inputLine);
                System.out.println(nextCommand.getResult());
                nextCommand = mainCommand;
            }
        }
    }

    private static String prepareInfo(CommandInterface command) {
        StringBuilder builder = new StringBuilder();
        CommandInterface[] options = command.getOptions();
        builder.append(command.getInfo() + ":\n");
        for (int i = 0; i < options.length; i++) {
            builder.append(String.format("%d. %s.\n", i, options[i].getTitle()));
        }

        return builder.toString();
    }
}
