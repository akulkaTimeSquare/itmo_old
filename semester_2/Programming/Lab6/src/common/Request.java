package common;

import common.collection.HumanBeing;

import java.io.Serializable;

public class Request implements Serializable {
    private final String command;
    private final String value;
    private HumanBeing element;

    public Request(String command, String value) {
        this.command = command;
        this.value = value;
    }

    public void setElement(HumanBeing element) {
        this.element = element;
    }

    public HumanBeing getElement() {
        return element;
    }

    public String getCommand() {
        return command;
    }

    public String getValue() {
        return value;
    }
}
