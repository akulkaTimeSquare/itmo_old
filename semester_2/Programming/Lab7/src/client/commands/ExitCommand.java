package client.commands;

import static java.lang.System.exit;

public class ExitCommand extends ClientCommand {
    /** ExitCommand exits from the program */
    @Override
    public void execute(String value) {
        exit(0);
    }
}
