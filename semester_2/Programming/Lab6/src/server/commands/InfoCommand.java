package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;

import java.time.LocalDateTime;

public class InfoCommand extends ServerCommand {
    /** InfoCommand shows collection's info */
    @Override
    public void execute(String value, HumanBeing element) {
        LocalDateTime initDate = CollectionManager.getInitDate();
        addToResponse("\tType: " + CollectionManager.getCollectionType() + "\n" +
                      "\tInitializationDate: " + initDate.getDayOfMonth() + " " +
                      initDate.getMonth() + " " + initDate.getYear() + " " +
                      (initDate.getHour() < 10 ? "0" : "") + initDate.getHour() + ":" +
                      (initDate.getMinute() < 10 ? "0" : "") + initDate.getMinute() + ":" +
                      (initDate.getSecond() < 10 ? "0" : "") + initDate.getSecond() + "\n" +
                      "\tSize: " + CollectionManager.getCollectionSize() + " elements");
    }
}
