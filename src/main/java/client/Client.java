package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final int port = 8080;
        final String host = "127.0.0.1";
        try {
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String line = "Hello World";
            out.writeBytes(line);
            out.flush();
        } catch (IOException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
}
