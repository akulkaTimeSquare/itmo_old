package server.serverIO;


import server.ClientSession;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerShell {
    /** Run: runs command in a cycle*/
    private static final ArrayList<String> addresses = new ArrayList<>();
    public void run() {
        try (DatagramSocket ds = new DatagramSocket(null)) {
            ds.setReuseAddress(true);
            ds.bind(new InetSocketAddress(7446));
            Class.forName("org.postgresql.Driver");
            Properties properties = new Properties();
            properties.load(new FileInputStream("resources/db.cfg"));
            String url = "jdbc:postgresql://localhost:5432/studs";
            Connection conn = DriverManager.getConnection(url, properties);
            new Starter(conn).start();
            ExecutorService service = Executors.newFixedThreadPool(10);
            System.out.println("Сервер запущен!\nДля выхода введите exit, выведения количества соединений status.");

            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                        ServerExecutor.runConsoleCommand(bufferedReader.readLine());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();

            while (true) {
                byte[] receiveBytes = new byte[10000];
                DatagramPacket receivedDP = new DatagramPacket(receiveBytes, receiveBytes.length);
                ds.receive(receivedDP);
                addresses.add(receivedDP.getSocketAddress().toString());
                ClientSession getRequest = new ClientSession(receivedDP);
                service.execute(getRequest);
            }
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> getAddresses() {
        return addresses;
    }
}
