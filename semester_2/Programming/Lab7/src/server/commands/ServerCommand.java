package server.commands;

import common.collection.HumanBeing;
import common.exceptions.WrongArgsException;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class ServerCommand {
    private String errorMessage = "";
    protected String response = "";

    public abstract void execute(String value, HumanBeing element, String login, String password) throws WrongArgsException;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void addToResponse(String response) {
        this.response += response;
    }

    public String getResponse() {
        return response;
    }
}

