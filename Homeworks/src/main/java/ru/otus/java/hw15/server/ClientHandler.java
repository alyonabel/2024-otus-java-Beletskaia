package ru.otus.java.hw15.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;

    public String getClientName() {
        return clientName;
    }

    private String clientName;
    private static int clientCount = 0;


    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        clientCount++;
        clientName = "client#" + clientCount;

        new Thread(() -> {
            try {
                System.out.println("Клиент подключился " + socket.getPort());
                while (true) {
                    String message = in.readUTF();
                    System.out.println(message);
                    if(message.startsWith("/")){
                        if(message.equalsIgnoreCase("/exit")){
                            sendMsg("/exitComplete");
                           break;
                        }
                        else if (message.startsWith("/w ")){
                            String [] splittedMes = message.split(" ",3);
                            server.broadcastMessageOne(splittedMes[1], splittedMes[2]);
                        }
                    }else {
                        server.broadcastMessage(clientName + " : " + message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                disconnect();
            }
        }).start();
    }

    public void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnect() {
        server.unSubscribe(this);
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

