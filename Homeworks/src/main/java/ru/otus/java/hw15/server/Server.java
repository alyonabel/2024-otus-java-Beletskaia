package ru.otus.java.hw15.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class Server {
    private int port;
    private List<ClientHandler> clients;

   public Server(int port) {
        this.port = port;
        clients = new Vector<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                subscribe(new ClientHandler(socket,this));
            }
        } catch (
                IOException e) {
        }
    }
    public void subscribe(ClientHandler clientHandler){
       clients.add(clientHandler);
    }
    public void unSubscribe(ClientHandler clientHandler){
        clients.remove(clientHandler);
        System.out.println("Из чата вышел " + clientHandler.getClientName());
    }
    
    public void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMsg(message);
        }
    }

    public void broadcastMessageOne(String clientName,String message){
            for (ClientHandler client : clients) {
                if(client.getClientName().equals(clientName))
                client.sendMsg(message);
            }
    }
}
