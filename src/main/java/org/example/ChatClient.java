package org.example;
import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    private PrintWriter out;

    public static void main(String[] args) {
        new ChatClient().startClient();
    }

    public void startClient() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();


            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String userMessage;
            while ((userMessage = userInput.readLine()) != null) {
                out.println(userMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

