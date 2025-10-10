package Command;

import Collection.HumanBeing;
import IOControl.Manager;

import java.util.ArrayList;

public class ShowCommand implements Command {
    /** ShowCommand show the string view of a collection */
    @Override
    public void execute(Manager manager, String[] args) {
        if (manager.isEmptyCollection()) {
            manager.println("\tThere's nothing in collection");
        }
        else {
            ArrayList<HumanBeing> collection = manager.getSortedCollection();
            for (HumanBeing el : collection) {
                manager.println("\t" + el);
                if (collection.indexOf(el) != collection.size() - 1) manager.println("");
            }
        }
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescr() {
        return "Выводит в стандартный поток все элементы коллекции в строковом представлении";
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }
}
