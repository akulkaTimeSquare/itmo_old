package server.commands;

import common.collection.HumanBeing;
import common.exceptions.WrongArgsException;
import server.serverIO.DataBaseManager;

import javax.xml.crypto.Data;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LoginServerCommand extends ServerCommand {
    @Override
    public void execute(String value, HumanBeing element, String login, String password) throws WrongArgsException {
        DataBaseManager.getCollectionLock().lock();
        try {
            if (DataBaseManager.checkUser(login, password)) {
                addToResponse("Авторизация прошла успешно!\n");
            } else {
                throw new WrongArgsException("Некорректные данные!\n");
            }
        } finally {
            DataBaseManager.getCollectionLock().unlock();
        }
    }
}
