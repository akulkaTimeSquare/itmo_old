package server.commands;


import common.collection.HumanBeing;
import server.serverIO.CollectionManager;

import java.util.HashSet;
import java.util.stream.Collectors;

public class RemoveGreaterServerCommand extends ServerCommand {
    /**
     * RemoveGreater removes all elements greater than current
     */
    @Override
    public void execute(String value, HumanBeing element) {
        CollectionManager.setCollection((HashSet<HumanBeing>) CollectionManager.getCollection().stream()
                .filter(el -> element.compareTo(el) > 0)
                .peek(el -> addToResponse("\t" + el.simpleView() + "был удалён\n"))
                .collect(Collectors.toSet()));
        addToResponse("Все элементы, большие заданного, удалены");
    }
}
