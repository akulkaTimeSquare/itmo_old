package server.commands;

import common.Command;
import common.collection.HumanBeing;

public class HelpCommand extends ServerCommand {
    /** help command shows all available commands*/
    @Override
    public void execute(String value, HumanBeing element){
        Command[] commands = Command.values();
        for (int i = 0; i < commands.length; i++) {
            Command com = commands[i];
            addToResponse("\t" + com.getName());
            for (String arg: com.getArgs()) {
                addToResponse(" " + arg);
            }
            addToResponse(": ");
            addToResponse(com.getDescr() + (i != commands.length - 1 ? "\n" : ""));
        }
    }
}
