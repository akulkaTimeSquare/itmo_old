package client.commands;

import common.exceptions.ExitException;
import common.exceptions.WrongArgsException;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LoginClientCommand extends ClientCommand {
    private String login;
    private String password;

    public void execute(String value) throws WrongArgsException, ExitException {
        String username;
        String password;
        Console terminal = System.console();
        if (terminal != null) {
            username = terminal.readLine("Login: ");
            password = Arrays.toString(terminal.readPassword("Password: "));
        } else {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.print("Login(3+sym): ");
                username = bf.readLine().strip();
                System.out.print("Password(3+sym): ");
                password = bf.readLine().strip();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (username.length() >= 3 && password.length() >= 3) {
            this.login = username;
            this.password = password;
        } else throw new WrongArgsException("Поля не должны быть пустыми, длины полей НЕ меньше 3");
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
