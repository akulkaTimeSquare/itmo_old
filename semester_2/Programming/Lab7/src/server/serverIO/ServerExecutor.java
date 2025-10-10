package server.serverIO;


import common.Command;
import common.Response;
import common.collection.HumanBeing;
import common.exceptions.IncorrectCommandException;
import common.exceptions.WrongArgsException;
import server.commands.*;

import java.io.Console;
import java.lang.management.LockInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.exit;

public class ServerExecutor {
    /** Executor contains HashMap of command, historyCommand and allows to parse input (parseCommand) and run it (run)
     *  it also contains historySize filed to control max history size*/
    private static final int historySize = 8;
    private static final ArrayDeque<String> commandHistory = new ArrayDeque<>();
    private static final HashMap<String, ServerCommand> commands = new HashMap<>();
    static {
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
        commands.put("login", new LoginServerCommand());
        commands.put("register", new RegisterServerCommand());
    }
    private static final HashMap<String, ServerCommand> loginCommands = new HashMap<>();
    static {
        loginCommands.put("login", new LoginServerCommand());
        loginCommands.put("register", new RegisterServerCommand());
    }


    public static Response execute(String strCommand, String value, HumanBeing element, String login, String password)
            throws WrongArgsException, IncorrectCommandException, SQLException {
        Response response = new Response();
        if (!DataBaseManager.checkUser(login, password) && !loginCommands.containsKey(strCommand)){
            response.setAnswer("Вы не авторизованы для выполнения команд!\n");
            return response;
        }
        if (commands.containsKey(strCommand)) {
            if (Command.valueOf(strCommand.toUpperCase()).getArgCount() != (value == null ? 0 : 1))
                throw new WrongArgsException("Задано неверное количество аргументов!\n");
            ServerCommand command = commands.get(strCommand);
            command.setResponse("");
            command.execute(value, element, login, password);

            synchronized (getCommandHistory()) {
                commandHistory.addFirst(strCommand);
                if (commandHistory.size() == historySize)
                    commandHistory.removeLast();
            }

            command.addToResponse(command.getErrorMessage());
            response.setAnswer(command.getResponse());
        } else throw new IncorrectCommandException();
        return response;
    }

    public static void runConsoleCommand(String input) {
        String[] parsedInp = input.strip().split(" ");
        if (parsedInp.length != 1) {System.out.println("Введены лишние данные");}
        else if (parsedInp[0].equals("exit")) {
            exit(0);
        } else if (parsedInp[0].equals("status")) {
            System.out.println("Найдено соединений: " +
                    ServerShell.getAddresses().size());
        } else if (parsedInp[0].equals("help")) {
            System.out.println("Status - вывод количества соединений, exit - выход");
        } else {
            System.out.println("Введена неверная команда!");
        }
    }

    public static Collection<ServerCommand> getCommands() {
        return commands.values();
    }

    public static ArrayDeque<String> getCommandHistory() {
        return commandHistory;
    }
}
