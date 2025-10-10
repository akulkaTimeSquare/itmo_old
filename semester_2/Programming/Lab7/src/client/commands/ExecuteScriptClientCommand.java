package client.commands;

import client.clientIO.ClientExecutor;
import common.exceptions.ExitException;

import java.util.Stack;

public class ExecuteScriptClientCommand extends ClientCommand {
    /** ExecuteScript executes script by reading text from a file */
    public static Stack<String> stack = new Stack<>();

    @Override
    public void execute(String value) throws ExitException {
        if (stack.contains(value)){
            System.out.println("Скрипты вызываются бесконечно!");
            throw new ExitException();
        }
        stack.push(value);
        new ClientExecutor().runScript(value);
        stack.pop();
    }
}
