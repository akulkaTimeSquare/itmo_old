package Command;

import Exception.WrongArgsException;
import IOControl.Manager;

public class RemoveByIdCommand implements Command {
    /** RemoveByIdCommand removes element by id */
    @Override
    public void execute(Manager manager, String[] args) throws WrongArgsException {
        try {
            long id = Long.parseLong(args[1]);
            if (manager.containsId(id))
                throw new WrongArgsException("id" + id + "не найден!");
            manager.removeElementById(id);
            manager.println("Элемент был успешно удалён!");
        } catch (NumberFormatException e) {
            throw new WrongArgsException("Аргументом должно служить число типа long");
        }
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescr() {
        return "удалить элемент из коллекции по id";
    }

    @Override
    public String[] getArgs() {
        return new String[]{"id"};
    }
}
