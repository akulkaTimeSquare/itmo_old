package server.commands;

import common.Command;
import common.collection.HumanBeing;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class HelpCommand extends ServerCommand {
    /** help command shows all available commands*/
    @Override
    public void execute(String value, HumanBeing element, String login, String password){
        Command[] commands = Command.values();
        for (int i = 0; i < commands.length; i++) {
            Command com = commands[i];
            addToResponse("\t" + com.getName());
            for (String arg: com.getArgs()) {
                addToResponse(" " + arg);
            }
            addToResponse(": ");
            addToResponse(com.getDescr() + "\n");
        }
    }
}
