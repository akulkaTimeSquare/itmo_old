package Command;

import Collection.HumanBeing;
import Exception.WrongArgsException;
import IOControl.Manager;

public class UpdateCommand implements Command {
    /** UpdateCommand updates element by id to current */
    @Override
    public void execute(Manager manager, String[] args) throws WrongArgsException {
        try {
            if (!args[1].matches("\\d*"))
                throw new WrongArgsException("Аргументом должно служить число типа long");
            long id = Long.parseLong(args[1]);
            if (manager.containsId(id))
                throw new WrongArgsException("id " + id + " не найден!");

            HumanBeing el = new HumanBeing(0);
            manager.println("Переход к созданию объекта (для выхода введите exit)");
            if (el.buildElement(manager))
                manager.println("Выполнен выход из создания объекта");
            else {
                manager.updateId(el);
                manager.println("Объект успешно обновлён!");
            }
        } catch (NumberFormatException e) {
            throw new WrongArgsException("Аргументом должно служить число типа long");
        }
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescr() {
        return "обновляет значение элемента коллекции по id";
    }

    @Override
    public String[] getArgs() {
        return new String[]{"id"};
    }
}
