package ru.otus.java.hw16.server;

public interface AuthenticatedProvider {
    void initialize();
    boolean authenticate(ClientHandler clientHandler,String login, String password);
    boolean registration(ClientHandler clientHandler,String login, String password,String username);
    boolean kickOther(ClientHandler clientHandler, String username);
}

