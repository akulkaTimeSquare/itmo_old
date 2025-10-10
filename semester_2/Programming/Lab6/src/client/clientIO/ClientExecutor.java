package client.clientIO;

import client.commands.*;
import common.Command;
import common.Request;
import common.Response;
import common.Serializer;
import common.exceptions.ExitException;
import common.exceptions.IncorrectCommandException;
import common.exceptions.WrongArgsException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

public class ClientExecutor {
    /** Executor contains HashMap of command, historyCommand and allows to parse input (parseCommand) and run it (run)
     *  it also contains historySize filed to control max history size*/
    private final HashMap<String, ClientCommand> clientCommands = new HashMap<>();

    public ClientExecutor() {
        clientCommands.put("add", new AddClientCommand());
        clientCommands.put("update", new UpdateClientCommand());
        clientCommands.put("exit", new ExitCommand());
        clientCommands.put("remove_greater", new RemoveCompareClientCommand());
        clientCommands.put("remove_lower", new RemoveCompareClientCommand());
        clientCommands.put("execute_script", new ExecuteScriptClientCommand());
    } 
    public String[] parseCommand(String inp) {
        return inp.split(" ", 3);
    }

    public Request execute(String input)
            throws IncorrectCommandException, WrongArgsException, IOException, ExitException {
        String[] parsedInp = parseCommand(input);
        String strCommand = parsedInp[0];
        String value;
        try {value = parsedInp[1];}
        catch (IndexOutOfBoundsException e) {value = null;}

        try {
            Command.valueOf(strCommand.toUpperCase());}
        catch (IllegalArgumentException e) {throw new IncorrectCommandException();}

        if (Command.valueOf(strCommand.toUpperCase()).getArgCount() != parsedInp.length - 1)
            throw new WrongArgsException("Задано неверное количество аргументов!");

        Request request = new Request(strCommand, value);
        if (clientCommands.containsKey(strCommand)) {
            ClientCommand command = clientCommands.get(strCommand);
            command.execute(value);

            request.setElement(command.getElement());
        }

        return request;
    }

    public void runScript(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine().strip();
            while (line != null) {
                try {
                    executeScript(line);
                    try {
                        line = br.readLine().strip();
                    } catch (NullPointerException exception) {
                        line = null;
                    }
                } catch (WrongArgsException | IncorrectCommandException exception) {
                    System.out.println("Некорректные данные в файле " + fileName + " : " + exception.getMessage());
                    break;
                } catch (NullPointerException | IllegalArgumentException exception) {
                    System.out.println(exception.getMessage());
                    break;
                } catch (ExitException exception) {
                    break;
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void executeScript(String string) throws IOException, WrongArgsException,
            IncorrectCommandException, ExitException {
        try (DatagramChannel datagramChannel = DatagramChannel.open()) {
            datagramChannel.configureBlocking(false);
            SocketAddress address = new InetSocketAddress("localhost", 7446);
            Request request = execute(string);
            if (request != null) {
                Serializer serializer = new Serializer();
                byte[] byteRequest = serializer.serialize(request);
                ByteBuffer sendBuf = ByteBuffer.wrap(byteRequest);
                datagramChannel.send(sendBuf, address);
                sendBuf.clear();

                ByteBuffer receivedBuf = ByteBuffer.allocate(10000);
                LocalDateTime time = LocalDateTime.now();
                while (true) {
                    if (Duration.between(LocalDateTime.now(), time).abs().getSeconds() > 10) {
                        System.out.println("Ответ от сервера не был получен, возвращение в оболочку...");
                        break;
                    }
                    if (datagramChannel.receive(receivedBuf) != null) {
                        Response response = (Response) serializer.deserialize(receivedBuf.array());
                        System.out.println(response.getAnswer());
                        break;
                    }
                }
            }
        }
    }

    public Collection<ClientCommand> getCommands() {
        return clientCommands.values();
    }


}
