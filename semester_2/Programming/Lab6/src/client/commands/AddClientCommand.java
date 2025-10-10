package client.commands;

import common.collection.HumanBeing;
import common.exceptions.ExitException;

public class AddClientCommand extends ClientCommand {
    /** Add command adds element in collection */
    @Override
    public void execute(String value) throws ExitException {
        HumanBeing el = new HumanBeing();
        System.out.println("Переход к созданию объекта (для выхода введите exit)");
        if (!el.buildElement()) {
            System.out.println("Выполнен выход из создания объекта");
            throw new ExitException();
        } else {
            setProduct(el);
            System.out.println("Объект успешно создан!");
        }
    }
}
