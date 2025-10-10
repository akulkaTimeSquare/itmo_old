package client.commands;

import common.collection.HumanBeing;
import common.exceptions.ExitException;
import common.exceptions.WrongArgsException;

public class UpdateClientCommand extends ClientCommand {
    /** UpdateCommand updates element by id to current */
    @Override
    public void execute(String value) throws WrongArgsException, ExitException {
        try {
            if (!value.matches("\\d*"))
                throw new WrongArgsException("Аргументом должно служить число типа long");
            long id = Long.parseLong(value);

            HumanBeing element = new HumanBeing(id);
            System.out.println("Переход к созданию объекта (для выхода введите exit)");
            if (!element.buildElement()) {
                System.out.println("Выполнен выход из создания объекта");
                throw new ExitException();
            }
            setProduct(element);
            System.out.println("Объект успешно создан!");
        } catch (NumberFormatException e) {
            throw new WrongArgsException("Аргументом должно служить число типа long");
        }
    }

}
