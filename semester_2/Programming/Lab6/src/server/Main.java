package server;

import server.serverIO.ServerShell;

public class Main {
    public static void main(String[] args) {
        ServerShell serverShell = new ServerShell();
        if (args.length > 1) System.out.println("Передано слишком много аргументов, игнорирование...");
        if (args.length == 1) serverShell.updateCollection(args[0]);
        new ServerShell().run();
    }
}
