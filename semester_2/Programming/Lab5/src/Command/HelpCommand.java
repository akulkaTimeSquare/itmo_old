package Command;

import IOControl.Manager;

public class HelpCommand implements Command {
    /** help command shows all available commands*/
    @Override
    public void execute(Manager manager, String[] args){
        for (Command com: manager.getCommands()) {
            manager.print("\t", com.getName());
            for (String arg: com.getArgs()) {
                manager.print(" " + arg);
            }
            manager.print(": ");
            manager.println(com.getDescr());
        }
    }

    @Override
    public String getDescr() {
        return "Выводит справку по доступным командам";
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }
}
