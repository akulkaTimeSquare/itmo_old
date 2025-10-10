package Command;

import Collection.HumanBeing;
import IOControl.Manager;

public class RemoveGreaterCommand implements Command {
    /** RemoveGreater removes all elements greater than current */
    @Override
    public void execute(Manager manager, String[] args) {
        HumanBeing el = new HumanBeing(0);
        if (el.buildElement(manager))
            manager.println("Выполнен выход из создания объекта");
        else {
            for (HumanBeing element: manager.getCollection()) {
                if (element.compareTo(el) > 0) {
                    manager.removeElement(element);
                    manager.println("\t" + element.simpleView() + "удалён");
                }
            }
            manager.println("Все элементы, большие заданного, удалены");
        }
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescr() {
        return "удаляет все элемент коллекции, превышающий заданный";
    }

    @Override
    public String[] getArgs() {
        return new String[]{};
    }
}
