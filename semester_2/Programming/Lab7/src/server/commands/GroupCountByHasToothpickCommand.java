package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;
import server.serverIO.DataBaseManager;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class GroupCountByHasToothpickCommand extends ServerCommand {
    /** GroupCountByHasToothpickCommand groups element in two categories: with toothpick and without */
    @Override
    public void execute(String value, HumanBeing element, String login, String password){
        DataBaseManager.getCollectionLock().lock();
        try {
            if (CollectionManager.isEmptyCollection()) addToResponse("Нет элементов в коллекции!\n");
            else {
                int[] groupSize = CollectionManager.getHasToothpickGroupSize();
                addToResponse("\thasToothpick true - " + groupSize[0] +
                        "; hasToothpick false - " + groupSize[1] + "\n");
            }
        } finally {
            DataBaseManager.getCollectionLock().unlock();
        }

    }
}
