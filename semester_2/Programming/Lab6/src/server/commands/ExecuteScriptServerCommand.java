package server.commands;

import common.collection.HumanBeing;
import common.exceptions.WrongArgsException;

public class ExecuteScriptServerCommand extends ServerCommand {
    public void execute(String value, HumanBeing element) throws WrongArgsException {
        if (element != null) throw new WrongArgsException("Передан объект!");
    }
}
