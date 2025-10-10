package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;

public class PrintDescendingCommand extends ServerCommand {
    /** PrintDescending shows elements lower than current */
    @Override
    public void execute(String value, HumanBeing element){
        if (CollectionManager.isEmptyCollection()) addToResponse("There's nothing in collection");
        else {
            CollectionManager.getReversedCollection().stream().
                    forEachOrdered(el -> addToResponse("\t" + el.simpleView() + "\n"));
        }
    }
}
