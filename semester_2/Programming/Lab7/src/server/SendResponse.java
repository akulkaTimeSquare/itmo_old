package server;

import common.Response;
import common.Serializer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

public class SendResponse implements Runnable {
    DatagramSocket ds;
    Response response;

    public SendResponse(DatagramSocket ds, Response response) {
        this.ds = ds;
        this.response = response;
    }

    @Override
    public void run() {
        try {
            byte[] byteResponse = new Serializer().serialize(response);
            DatagramPacket sendDP = new DatagramPacket(byteResponse, byteResponse.length);
            ds.send(sendDP);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
