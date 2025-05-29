package ru.otus.java.hw16.server;

import java.io.IOException;

public class ServerApplication {
    public static void main(String[] args) throws IOException {
        new Server(8189).start();
    }
}
