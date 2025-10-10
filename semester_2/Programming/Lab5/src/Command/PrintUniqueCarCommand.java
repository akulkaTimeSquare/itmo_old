package Command;

import Collection.HumanBeing;
import IOControl.Manager;

import java.util.ArrayList;
import java.util.HashSet;

public class PrintUniqueCarCommand implements Command {
    /** PrintDescending shows unique elements */
    @Override
    public void execute(Manager manager, String[] args) {
        if (manager.isEmptyCollection()) manager.println("Коллекция пуста");
        else {
            HashSet<String> uniqueFields = new HashSet<>();
            ArrayList<HumanBeing> collection = manager.getSortedCollection();
            for (HumanBeing element : collection) {
                if (!uniqueFields.contains(element.getCar().getName()))
                    manager.println("\t" + element);
                uniqueFields.add(element.getCar().getName());
            }
        }
    }

    @Override
    public String getName() {
        return "print_unique_car";
    }

    @Override
    public String getDescr() {
        return "выводит уникальные значения поля car всех элементов коллекции";
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }
}
