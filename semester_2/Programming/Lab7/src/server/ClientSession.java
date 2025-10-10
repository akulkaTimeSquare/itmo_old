package server;

import common.Request;
import common.Serializer;

import java.io.IOException;
import java.net.*;


public class ClientSession implements Runnable {
    private final DatagramPacket receivedDP;
    private final Serializer serializer = new Serializer();

    public ClientSession(DatagramPacket receivedDP) {
        this.receivedDP = receivedDP;
    }

    @Override
    public void run() {
        try {
            if (receivedDP != null) {
                DatagramSocket datagramSocket = new DatagramSocket(null);
                datagramSocket.setReuseAddress(true);
                datagramSocket.bind(new InetSocketAddress(7446));
                datagramSocket.connect(receivedDP.getSocketAddress());
                System.out.println("Найдено новое подключение " + datagramSocket.getRemoteSocketAddress() + "!");

                DatagramPacket datagramPacket = receivedDP;
                while (datagramSocket.isConnected()) {
                    Request request = (Request) serializer.deserialize(datagramPacket.getData());
                    new Thread(new ResponseHandler(datagramSocket, request)).start();
                    byte[] receiveBytes = new byte[10000];
                    datagramPacket = new DatagramPacket(receiveBytes, receiveBytes.length);
                    datagramSocket.receive(datagramPacket);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
