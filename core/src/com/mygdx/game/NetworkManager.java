package com.mygdx.game;

import java.io.*;
import java.net.Socket;

public class NetworkManager extends Thread {
    public static NetworkManager instance;
    Socket socket;

    private OutputStream output;
    private PrintWriter writer;

    private InputStream input;
    private BufferedReader reader;
    boolean connected;
    private String status;
    boolean isHost = false;

    private String server = "127.0.0.1";
    private Integer port = 8888;

    Event<String> statusChanged = new Event<>();

    public static void instantiate() {
        instance = new NetworkManager();
    }

    public void run() {
        connected = connect(server, port);
    }

    private boolean connect(String serverAddress, int port) {
        changeStatus("CONNECTING");
        try {
            socket = new Socket(serverAddress, port);

            input = socket.getInputStream();
            output = socket.getOutputStream();

            writer = new PrintWriter(output, true);
            reader = new BufferedReader(new InputStreamReader(input));

            String response = NetworkManager.instance.readText();
            if (response.equals("ALLOW_JOIN_HOST")) {
                isHost = true;
            }

        } catch (IOException e) {
            changeStatus("FAILED");
            return false;
        }

        changeStatus("CONNECTED");
        return true;
    }

    public void sendText(String text) {
        writer.println(text);
    }

    public String readText() {
        try {
            return reader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    private void changeStatus(String status) {
        this.status = status;
        statusChanged.broadcast(status);
    }
}
