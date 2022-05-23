package handler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketHandler extends Thread {
    private final Socket socket;
    private BufferedReader in;
    private DataOutputStream out;

    public SocketHandler(final Socket socket) {
        this.socket = socket;
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Starting new thread: " + Thread.currentThread().getId());
            String line = "";
            while(true) {
                try {
                    line = in.readLine();
                    System.out.println(line);
                    if(line == null || line == "exit") {
                        System.out.println("Closing socket");
                        break;
                    }
                    else {
                        out.writeBytes(line);
                        out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
