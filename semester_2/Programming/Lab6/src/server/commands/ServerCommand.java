package server.commands;

import common.collection.HumanBeing;
import common.exceptions.WrongArgsException;

public abstract class ServerCommand {
    private String errorMessage = "";
    protected String response = "";

    public abstract void execute(String value, HumanBeing element) throws WrongArgsException;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addToResponse(String response) {
        this.response += response;
    }

    public String getResponse() {
        return response;
    }
}

