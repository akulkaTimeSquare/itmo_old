package server.commands;

import common.collection.HumanBeing;
import server.serverIO.CollectionManager;

import java.util.ArrayList;

public class ShowCommand extends ServerCommand {
    /** ShowCommand show the string view of a collection */
    @Override
    public void execute(String value, HumanBeing element) {
        if (CollectionManager.isEmptyCollection()) {
            addToResponse("\tThere's nothing in collection");
        }
        else {
            ArrayList<HumanBeing> collection = CollectionManager.getSortedCollection();
            for (HumanBeing el : collection) {
                addToResponse("\t" + el + (collection.size() - 1 != collection.indexOf(el) ? "\n\n" : ""));
            }
        }
    }
}
