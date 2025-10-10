package server.commands;

import common.collection.HumanBeing;
import common.exceptions.WrongArgsException;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ExecuteScriptServerCommand extends ServerCommand {
    public void execute(String value, HumanBeing element, String login, String password) throws WrongArgsException {
        if (element != null) throw new WrongArgsException("Передан неожидаемый объект!\n");
    }
}
