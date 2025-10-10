package client.clientIO;


import common.Request;
import common.Response;
import common.Serializer;
import common.exceptions.ExitException;
import common.exceptions.IncorrectCommandException;
import common.exceptions.WrongArgsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.time.Duration;
import java.time.LocalDateTime;

public class ClientShell {
    private final ClientExecutor executor = new ClientExecutor();
    private final Serializer serializer = new Serializer();

    /*
     * Run: runs command in a cycle
     */
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Приветствуем! Для вывода справки введите help, для выхода - exit.");
            SocketAddress address = new InetSocketAddress("localhost", 7446);
            DatagramChannel dc = DatagramChannel.open();
            dc.configureBlocking(false);

            while (true) {
                String input = br.readLine().strip();
                try {
                    Request request = executor.execute(input);
                    if (request != null) {
                        byte[] byteRequest = serializer.serialize(request);
                        ByteBuffer sendBuf = ByteBuffer.wrap(byteRequest);
                        dc.send(sendBuf, address);
                        sendBuf.clear();

                        ByteBuffer receivedBuf = ByteBuffer.allocate(10000);
                        LocalDateTime time = LocalDateTime.now();
                        while (true) {
                            if (Duration.between(LocalDateTime.now(), time).abs().getSeconds() > 10) {
                                System.out.println("Ответ от сервера не был получен, возвращение в оболочку...");
                                break;
                            }
                            if (dc.receive(receivedBuf) != null) {
                                Response response = (Response) serializer.deserialize(receivedBuf.array());
                                System.out.println(response.getAnswer());
                                break;
                            }
                        }
                    }
                } catch (IncorrectCommandException e) {
                    System.out.println("Команда не распознана!");
                } catch (WrongArgsException e) {
                    System.out.println(e.getMessage());
                } catch (ExitException ignored) {
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
