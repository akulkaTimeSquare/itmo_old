package client.commands;


import common.collection.HumanBeing;
import common.exceptions.ExitException;

public class RemoveCompareClientCommand extends ClientCommand {
    /** RemoveGreater removes all elements greater than current */
    @Override
    public void execute(String value) throws ExitException {
        HumanBeing el = new HumanBeing(0);
        if (!el.buildElement()) {
            System.out.println("Выполнен выход из создания объекта");
            throw new ExitException();
        }
        setProduct(element);
    }
}
