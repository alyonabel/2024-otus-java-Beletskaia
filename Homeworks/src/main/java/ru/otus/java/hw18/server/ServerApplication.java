package ru.otus.java.hw18.server;

import java.io.IOException;
import java.sql.SQLException;

public class ServerApplication {
    public static void main(String[] args) throws IOException, SQLException {
        new Server(8189).start();
    }
}
