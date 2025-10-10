import IOControl.*;

import java.io.*;
import java.util.HashSet;
import java.util.Stack;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Приветствуем! Для вывода справки введите help, для выхода - exit.");
        Manager manager = new Manager(new Executor(),
                new Controller(new InputStreamReader(System.in), new OutputStreamWriter(System.out)),
                new CollectionManager(new HashSet<>()));
        manager.pushToStack(manager.getInput());
        Shell shell = new Shell(manager);
        if (args.length == 1) shell.updateCollection(args[0]);
        shell.run();
    }
}
