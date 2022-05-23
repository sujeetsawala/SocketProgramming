package server;

import handler.SocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        final int port = 8080;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true) {
                Socket socket = serverSocket.accept();
                new Thread(new SocketHandler(socket)).start();
            }
        } catch (IOException e) {

        }
    }
}
