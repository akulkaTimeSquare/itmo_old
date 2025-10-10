package Command;

import Collection.HumanBeing;
import IOControl.Manager;
import java.util.ArrayList;

public class PrintDescendingCommand implements Command {
    /** PrintDescending shows elements lower than current */
    @Override
    public void execute(Manager manager, String[] args){
        if (manager.isEmptyCollection()) manager.println("Коллекция пуста.");
        else {
            ArrayList<HumanBeing> reversedCollection = manager.getReverseSortedCollection();
            for (HumanBeing el : reversedCollection) {
                manager.println("\t", el.simpleView());
            }
        }
    }

    @Override
    public String getName() {
        return "print_descending";
    }

    @Override
    public String getDescr() {
        return "выводит элементы коллекции в порядке убывания";
    }

    @Override
    public String[] getArgs() {
        return new String[]{};
    }
}
