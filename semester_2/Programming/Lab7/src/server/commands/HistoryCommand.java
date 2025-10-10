package server.commands;

import common.collection.HumanBeing;
import server.serverIO.ServerExecutor;

import java.util.Collection;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class HistoryCommand extends ServerCommand {
    /** Shows history of commands from Executor*/
    @Override
    public void execute(String value, HumanBeing element, String login, String password){
        if (!ServerExecutor.getCommandHistory().isEmpty()){
            addToResponse("Последние команды:\n");
            synchronized (ServerExecutor.getCommandHistory()) {
                for (String command : ServerExecutor.getCommandHistory()) addToResponse("\t" + command + "\n");
            }
        } else {
            addToResponse("\tИстория команд пуста!\n");
        }
    }
}
