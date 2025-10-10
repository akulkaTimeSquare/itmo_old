package Command;

import Exception.WrongArgsException;
import IOControl.Manager;

public interface Command {
    void execute(Manager manager, String[] args) throws WrongArgsException;
    String getName();
    /** Giving you a description of command*/
    String getDescr();
    String[] getArgs();
    default int getArgCount(){
        return getArgs().length;
    }
}