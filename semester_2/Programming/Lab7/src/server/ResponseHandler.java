package server;

import common.Request;
import common.Response;
import common.exceptions.IncorrectCommandException;
import common.exceptions.WrongArgsException;
import server.serverIO.ServerExecutor;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.SQLException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ResponseHandler implements Runnable {
    private final DatagramSocket ds;
    private final Request request;


    public ResponseHandler(DatagramSocket ds, Request request) {
        this.ds = ds;
        this.request = request;
    }

    @Override
    public void run() {
        Response response;
        try {
            response = ServerExecutor.execute(request.getCommand(), request.getValue(), request.getElement(),
                    request.getLogin(), request.getPassword());
        } catch (IncorrectCommandException e) {
            response = new Response();
            response.setAnswer("Задана неверная команда!");
        } catch (WrongArgsException e) {
            response = new Response();
            response.setAnswer(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        new Thread(new SendResponse(ds, response)).start();
    }
}
