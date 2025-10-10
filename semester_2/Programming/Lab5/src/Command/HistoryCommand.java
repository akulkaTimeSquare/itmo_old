package Command;

import IOControl.Manager;

import java.util.ArrayDeque;

public class HistoryCommand implements Command {
    /** Shows history of commands from Executor*/
    @Override
    public void execute(Manager manager, String[] args) {
        ArrayDeque<Command> commandHistory = manager.getCommandHistory();
        if (commandHistory.size() == 0) manager.println("\tИстория команд пуста");
        else {
            manager.println("\tПоследние выполненные команды:");
            for (Command cmd : commandHistory) {
                manager.println("\t" + "-" + cmd.getName());
            }
        }
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }

    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getDescr() {
        return "выводит последние 8 команд (без их аргументов)";
    }
}
