package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;

import java.util.AbstractCollection;
import java.util.HashSet;

public class PrintUniqueCarCommand extends ServerCommand {
    /** PrintDescending shows unique elements */
    @Override
    public void execute(String value, HumanBeing element) {
        if (CollectionManager.isEmptyCollection()) addToResponse("Коллекция пуста");
        else {
            HashSet<String> cars = CollectionManager.getSortedCollection().stream()
                    .collect(
                            HashSet::new,
                            (set, item) -> set.add(item.getCar().getName()),
                            AbstractCollection::addAll);
            cars.forEach(string -> addToResponse("\t" + string + "\n"));
        }
    }
}
