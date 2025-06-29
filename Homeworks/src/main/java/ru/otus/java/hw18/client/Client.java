package ru.otus.java.hw18.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Scanner scanner;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public Client() throws IOException {
        scanner = new Scanner(System.in);
        socket = new Socket("localhost", 8189);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.equalsIgnoreCase("/exitComplete")) {
                            break;
                        }
                        if (message.startsWith("/authOk ")) {
                            System.out.println("Удалось успешно войти в чат с именем пользователя " + message.split(" ")[1]);
                        }
                        if (message.startsWith("/regOk ")) {
                            System.out.println("Удалось успешно зарегистрироваться с именем пользователя " + message.split(" ")[1]);
                        }
                        if (message.startsWith("/kickOk ")) {
                            System.out.println("Вы (" + message.split(" ")[1] + ") были исключены из чата администратором ");
                            disconnect();
                            break;
                        }
                    } else {
                        System.out.println(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).
                start();
        while (true) {
            String message = scanner.nextLine();
            out.writeUTF(message);
            if (message.equalsIgnoreCase("/exit")) {
                break;
            }
        }
    }

    public void disconnect() {
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
