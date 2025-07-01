package ru.otus.java.hw18.client;

import java.io.IOException;

public class ClientApplication {
    public static void main(String[] args) {
        try {
            Client client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}