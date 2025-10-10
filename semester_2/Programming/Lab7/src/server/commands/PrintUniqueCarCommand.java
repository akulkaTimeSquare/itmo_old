package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;
import server.serverIO.DataBaseManager;

import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintUniqueCarCommand extends ServerCommand {
    /** PrintDescending shows unique elements */
    @Override
    public void execute(String value, HumanBeing element, String login, String password) {
        DataBaseManager.getCollectionLock().lock();
        try {
            if (CollectionManager.isEmptyCollection()) addToResponse("Коллекция пуста");
            else {
                HashSet<String> cars = CollectionManager.getSortedCollection().stream()
                        .collect(
                                HashSet::new,
                                (set, item) -> set.add(item.getCar().getName()),
                                AbstractCollection::addAll);
                cars.forEach(string -> addToResponse("\t" + string + "\n"));
            }
        } finally {
            DataBaseManager.getCollectionLock().unlock();
        }
    }
}
