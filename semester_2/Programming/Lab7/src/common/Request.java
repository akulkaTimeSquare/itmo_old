package common;

import common.collection.HumanBeing;

import java.io.Serializable;

public class Request implements Serializable {
    private final String command;
    private final String value;
    private HumanBeing element;
    private String login;
    private String password;

    public Request(String command, String value, String login, String password) {
        this.command = command;
        this.value = value;
        this.login = login;
        this.password = password;
    }

    public void setElement(HumanBeing element) {
        this.element = element;
    }

    public HumanBeing getElement() {
        return element;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getCommand() {
        return command;
    }

    public String getValue() {
        return value;
    }

}
