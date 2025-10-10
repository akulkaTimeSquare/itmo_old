package Command;

import Collection.HumanBeing;
import IOControl.Manager;

public class RemoveLowerCommand implements Command {
    /** RemoveGreater removes all elements lower than current */
    @Override
    public void execute(Manager manager, String[] args) {
        HumanBeing el = new HumanBeing(0);
        manager.println("Переход к созданию объекта (для выхода введите exit)");
        if (el.buildElement(manager))
            manager.println("Выполнен выход из создания объекта");
        else {
            for (HumanBeing element: manager.getCollection()) {
                if (element.compareTo(el) < 0) {
                    manager.removeElement(element);
                    manager.println("\t" + element.simpleView() + "удалён");
                }
            }
            manager.println("Все элементы, меньшие заданного, удалены");
        }
    }

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescr() {
        return "удаляет элементы из коллекции, меньшие заданного";
    }

    @Override
    public String[] getArgs() {
        return new String[]{};
    }
}
