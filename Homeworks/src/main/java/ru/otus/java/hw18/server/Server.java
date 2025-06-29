package ru.otus.java.hw18.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private int port;
    private List<ClientHandler> clients;
    private AuthenticatedProvider authenticatedProvider;

    public AuthenticatedProvider getAuthenticatedProvider() {
        return authenticatedProvider;
    }

    public Server(int port) throws SQLException {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
        authenticatedProvider = new InDBAuthenticatedProvider(this);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            authenticatedProvider.initialize();
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, this);
            }
        } catch (
                IOException e) {
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unSubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("Из чата вышел " + clientHandler.getClientName());
    }

    public void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMsg(message);
        }
    }

    public void broadcastMessageOne(String senderName, String clientName, String message) {
        for (ClientHandler client : clients) {
            if (client.getClientName().equals(clientName))
                client.sendMsg("from " + senderName + ": " + message);
        }
    }

    public boolean isUsernameBusy(String username) {
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getClientName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public ClientHandler findClientByUsername(String username) {
        ClientHandler clientHandler = null;
        for (ClientHandler client : clients) {
            if (client.getClientName().equals(username)) clientHandler = client;
        }
        return clientHandler;
    }
}
