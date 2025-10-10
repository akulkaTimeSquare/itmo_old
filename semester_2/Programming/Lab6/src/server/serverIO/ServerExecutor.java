package server.serverIO;


import common.Command;
import common.Response;
import common.collection.HumanBeing;
import common.exceptions.IncorrectCommandException;
import common.exceptions.WrongArgsException;
import server.commands.*;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;

public class ServerExecutor {
    /** Executor contains HashMap of command, historyCommand and allows to parse input (parseCommand) and run it (run)
     *  it also contains historySize filed to control max history size*/
    private static final int historySize = 8;
    private static final ArrayDeque<String> commandHistory = new ArrayDeque<>();
    private final HashMap<String, ServerCommand> commands = new HashMap<>();
    public ServerExecutor() {
        commands.put("add", new AddServerCommand());
        commands.put("update", new UpdateServerCommand());
        commands.put("remove_greater", new RemoveGreaterServerCommand());
        commands.put("remove_lower", new RemoveLowerServerCommand());
        commands.put("clear", new ClearCommand());
        commands.put("history", new HistoryCommand());
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("group_counting_by_has_toothpick", new GroupCountByHasToothpickCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("show", new ShowCommand());
        commands.put("print_descending", new PrintDescendingCommand());
        commands.put("print_unique_car", new PrintUniqueCarCommand());
        commands.put("execute_script", new ExecuteScriptServerCommand());
    }

    public String[] parseCommand(String inp) {
        return inp.split(" ", 3);
    }

    public Response execute(String strCommand, String value, HumanBeing element)
            throws WrongArgsException, IncorrectCommandException {
        Response response = new Response();
        if (commands.containsKey(strCommand)) {
            if (Command.valueOf(strCommand.toUpperCase()).getArgCount() == 0 && value != null ||
                Command.valueOf(strCommand.toUpperCase()).getArgCount() == 1 && value == null)
                throw new WrongArgsException("Задано неверное количество аргументов!");
            ServerCommand command = commands.get(strCommand);

            command.execute(value, element);
            commandHistory.addFirst(strCommand);
            if (commandHistory.size() == historySize)
                commandHistory.removeLast();

            command.addToResponse(command.getErrorMessage());
            response.setAnswer(command.getResponse());
        }
        else throw new IncorrectCommandException();
        return response;
    }

    public Collection<ServerCommand> getCommands() {
        return commands.values();
    }

    public static ArrayDeque<String> getCommandHistory() {
        return commandHistory;
    }

    public static int getHistorySize() {
        return historySize;
    }
}
