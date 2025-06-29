package ru.otus.java.hw18.server;

public interface AuthenticatedProvider {
    void initialize();

    boolean authenticate(ClientHandler clientHandler, String login, String password);

    // boolean registration(ClientHandler clientHandler, String login, String password, String username);
    boolean kickOther(ClientHandler clientHandler, String username);
}

