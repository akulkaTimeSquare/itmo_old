package server.commands;

import common.collection.HumanBeing;
import common.exceptions.WrongArgsException;
import server.serverIO.DataBaseManager;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RegisterServerCommand extends ServerCommand {
    @Override
    public void execute(String value, HumanBeing element, String login, String password) throws WrongArgsException {
        DataBaseManager.getCollectionLock().lock();
        try {
            if (DataBaseManager.checkUserLogin(login) || !DataBaseManager.checkUserPassword(password))
                throw new WrongArgsException("Некорректные данные!\n");
            DataBaseManager.insertUser(login, password);
        } finally {
            DataBaseManager.getCollectionLock().unlock();
        }
        addToResponse("Пользователь успешно зарегистрирован, теперь вы можете выполнять команды!\n");
    }
}
