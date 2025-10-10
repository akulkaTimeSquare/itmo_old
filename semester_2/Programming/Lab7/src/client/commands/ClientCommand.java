package client.commands;

import common.collection.HumanBeing;
import common.exceptions.ExitException;
import common.exceptions.WrongArgsException;

public abstract class ClientCommand {
    HumanBeing element;

    public abstract void execute(String value) throws WrongArgsException, ExitException;

    public void setProduct(HumanBeing element) {
        this.element = element;
    }

    public HumanBeing getElement() {
        return element;
    }
}
