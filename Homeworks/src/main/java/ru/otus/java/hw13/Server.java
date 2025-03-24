package ru.otus.java.hw13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final List<ClientHandler> clientHandlers = new ArrayList<>();

    public static String typeMathResult(String userInput, String userInput2, String userInput3) {
        int result = 0;
        int operation = Integer.parseInt(userInput);
        System.out.println("Operation: " + operation);
        int number1 = Integer.parseInt(userInput2);
        System.out.println("NumberFirst: " + number1);
        int number2 = Integer.parseInt(userInput3);
        System.out.println("NumberSecond: " + number2);
        switch (operation) {
            case 1:
                result = number1 + number2;
                break;
            case 2:
                result = number1 - number2;
                break;
            case 3:
                result = number1 * number2;
                break;
            case 4:
                if (number2 == 0) {
                    return "Error: Division by zero";
                }
                result = number1 / number2;
                break;
        }
        return String.valueOf(result);
    }


    private static void handleClient(Socket clientSocket) throws IOException {
        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
        ClientHandler clientHandler = new ClientHandler(clientSocket, inputStream, outputStream);
        clientHandlers.add(clientHandler);
        String userInput = inputStream.readUTF();
        System.out.println(userInput);
        String userInput2 = inputStream.readUTF();
        System.out.println(userInput2);
        String userInput3 = inputStream.readUTF();
        System.out.println(userInput3);
        String result = typeMathResult(userInput, userInput2, userInput3);
        outputStream.writeUTF(result);
        outputStream.flush();
        System.out.println("Result " + result);
    }


    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        System.out.println("SERVER APPLICATION RUN");
        while (true) {
            Socket clientSocket = socket.accept();
            new Thread(() -> {
                try {
                    handleClient(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }

}
