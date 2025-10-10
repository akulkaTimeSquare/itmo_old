package Command;

import Collection.HumanBeing;
import IOControl.Manager;

public class AddCommand implements Command {
    /** Add command adds element in collection */
    @Override
    public void execute(Manager manager, String[] args) {
        HumanBeing el = new HumanBeing();
        manager.println("Переход к созданию объекта (для выхода введите exit)");
        if (!el.buildElement(manager))
            manager.println("Выполнен выход из создания объекта");
        else {
            manager.addElement(el);
            manager.println("Объект успешно создан!");
        }
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescr() {
        return "добавляет новый элемент в коллекцию";
    }

    @Override
    public String[] getArgs() {
        return new String[]{};
    }

}
