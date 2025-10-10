package IOControl;

import Command.*;
import Exception.IncorrectCommandException;
import Exception.WrongArgsException;

import java.util.*;

public class Executor {
    /** Executor contains HashMap of command, historyCommand and allows to parse input (parseCommand) and run it (run)
     *  it also contains historySize filed to control max history size*/
    private int historySize = 8;
    private final HashMap<String, Command> commands;
    private final ArrayDeque<Command> commandHistory = new ArrayDeque<>();
    public Executor() {
        commands = new HashMap<>();

        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("add", new AddCommand());
        commands.put("update", new UpdateCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("exit", new ExitCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("remove_lower", new RemoveLowerCommand());
        commands.put("history", new HistoryCommand());
        commands.put("group_counting_by_has_toothpick", new GroupCountByHasToothpickCommand());
        commands.put("print_descending", new PrintDescendingCommand());
        commands.put("print_unique_car", new PrintUniqueCarCommand());
    }
    public String[] parseCommand(String inp) {
        return inp.split(" ", 3);
    }

    public void runCommand(Manager man, String[] inp) throws IncorrectCommandException, WrongArgsException {
        if (commands.containsKey(inp[0])) {
            if (commands.get(inp[0]).getArgCount() != inp.length - 1)
                throw new WrongArgsException("Задано неверное количество аргументов");
            commands.get(inp[0]).execute(man, inp);
            commandHistory.addFirst(commands.get(inp[0]));
            if (commandHistory.size() == historySize)
                commandHistory.removeLast();
        }
        else
            throw new IncorrectCommandException();
    }

    public Collection<Command> getCommands() {
        return commands.values();
    }

    public ArrayDeque<Command> getCommandHistory() {
        return commandHistory;
    }

    public void setHistorySize(int historySize) {
        this.historySize = historySize;
    }

    public int getHistorySize() {
        return historySize;
    }
}
