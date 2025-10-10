package Command;

import IOControl.Manager;

import static java.lang.System.exit;

public class ExitCommand implements Command {
    /** ExitCommand exits from the program */
    @Override
    public void execute(Manager manager, String[] args){
        manager.print("Bye...");
        exit(0);
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescr() {
        return "завершает программу (без сохранения в файл)";
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }
}
