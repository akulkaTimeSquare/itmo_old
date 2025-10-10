package server.commands;

import common.collection.HumanBeing;
import server.serverIO.ServerExecutor;

public class HistoryCommand extends ServerCommand {
    /** Shows history of commands from Executor*/
    @Override
    public void execute(String value, HumanBeing element){
        if (ServerExecutor.getCommandHistory().size() != 0){
            addToResponse("Последние команды:\n");
            String[] commandHistory = ServerExecutor.getCommandHistory().toArray(new String[0]);
            for (int i = commandHistory.length - 1; i > 0; i--) addToResponse("\t" + commandHistory[i] + "\n");
            addToResponse("\t" + ServerExecutor.getCommandHistory().getFirst());
        } else {
            addToResponse("\tИстория команд пуста!");
        }
    }
}
