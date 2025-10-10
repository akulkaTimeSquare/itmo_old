package Command;

import Exception.WrongArgsException;
import IOControl.Manager;
import IOControl.Shell;

import java.io.*;
import java.util.Stack;

public class ExecuteScriptCommand implements Command {
    /** ExecuteScript executes script by reading text from a file */
    @Override
    public void execute(Manager manager, String[] args) throws WrongArgsException {
        String filename = args[1];

        FileReader file;
        file = manager.getFile(filename);
        if (file == null) throw new WrongArgsException("Файл не найден");
        BufferedReader fileReader = new BufferedReader(file);
        BufferedWriter old_output = manager.getOutput();
        BufferedReader old_input = manager.getInput();
        Shell shell = new Shell(manager);
        try {
            manager.setInput(fileReader);
            if (!manager.isInStack(fileReader)) {
                manager.pushToStack(fileReader);
                if (shell.run()) manager.setInput(old_input);
            }
        } catch (RuntimeException ignored) {}
        manager.setInput(old_input);
        manager.setOutput(old_output);
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescr() {
        return "считывает и исполняет скрипт из указанного файла";
    }

    @Override
    public String[] getArgs() {
        return new String[]{"[file_name]"};
    }
}
