package ru.otus.java.hw16.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String clientName;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        startClientThread();
    }

    private void startClientThread() {
        new Thread(() -> {
            try {
                System.out.println("Клиент подключился на порту " + socket.getPort());
                if (handleAuthentication()) {
                    handleMessages();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();
    }

    private boolean handleAuthentication() throws IOException {
        sendMsg("Для начала работы нужно пройти аутентификацию. Формат команды /auth login password \n" +
                "Для регистрации формат команды /reg login password username");
        while (true) {
            String message = in.readUTF();
            System.out.println(message);
            if (message.startsWith("/")) {
                if (message.equalsIgnoreCase("/exit")) {
                    sendMsg("/exitComplete");
                    disconnect();
                    return false;
                }
                if (handleAuthCommand(message)) {
                    return true;
                }
                if (handleRegCommand(message)) {
                    return true;
                }
            }
        }
    }

    private boolean handleAuthCommand(String message) throws IOException {
        if (message.startsWith("/auth ")) {
            String[] elements = message.split(" ");
            if (elements.length != 3) {
                sendMsg("Неверный формат команды /auth");
                return false;
            }
            return server.getAuthenticatedProvider().authenticate(this, elements[1], elements[2]);
        }
        return false;
    }

    private boolean handleRegCommand(String message) throws IOException {
        if (message.startsWith("/reg ")) {
            String[] elements = message.split(" ");
            if (elements.length != 4) {
                sendMsg("Неверный формат команды /reg");
                return false;
            }
            return server.getAuthenticatedProvider().registration(this, elements[1], elements[2], elements[3]);
        }
        return false;
    }

    private void handleMessages() throws IOException {
        while (true) {
            String message = in.readUTF();
            System.out.println(message);
            if (message.startsWith("/")) {
                if (message.equalsIgnoreCase("/exit")) {
                    sendMsg("/exitComplete");
                    disconnect();
                    break;
                } else if (message.startsWith("/w ")) {
                    handlePrivateMessage(message);
                } else if (message.startsWith("/kick ")) {
                    handleKickCommand(message);
                }
            }
        }
    }

    private void handlePrivateMessage(String message) {
        String[] elements = message.split(" ", 3);
        if (elements.length < 3) {
            sendMsg("Неверный формат команды. Используйте: /w <ник> <сообщение>");
        } else {
            server.broadcastMessageOne(clientName, elements[1], elements[2]);
        }
    }

    private void handleKickCommand(String message) {
        String[] elements = message.split(" ");
        if (elements.length != 2) {
            sendMsg("Неверный формат команды /kick");
        } else {
            server.getAuthenticatedProvider().kickOther(this, elements[1]);
        }
    }

    void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void disconnect() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

