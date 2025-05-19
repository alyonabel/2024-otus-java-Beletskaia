package ru.otus.java.hw16.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private int port;
    private List<ClientHandler> clients;

    public AuthenticatedProvider getAuthenticatedProvider() {
        return authenticatedProvider;
    }

    private AuthenticatedProvider authenticatedProvider;

    public Server(int port) {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
        authenticatedProvider = new InMemoryAuthenticatedProvider(this);
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
        } return false;
    }




}
