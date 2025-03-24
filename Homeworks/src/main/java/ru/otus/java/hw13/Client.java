package ru.otus.java.hw13;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket("localhost", 8080)) {
            ExampleClient client = null;
            while (true) {
                client = new ExampleClient(socket.getInputStream(), socket.getOutputStream());
                System.out.println("Hello! Let's  start to calculate!");
                System.out.println("Please choose the math operation:");
                System.out.println("Type 1 and press ENTER, if you choose +, addition");
                System.out.println("Type 2 and press ENTER, if you choose -, subtraction");
                System.out.println("Type 3 and press ENTER, if you choose *, multiplication");
                System.out.println("Type 4 and press ENTER, if you choose /, division");
                String resultOperation = scanner.nextLine();
                if (resultOperation.equals("exit")) {
                    System.out.println("You have successfully closed the program");
                    break;
                }
                System.out.println("Please type one number now and press ENTER");
                String number1 = scanner.nextLine();
                if (number1.equals("exit")) {
                    System.out.println("You have successfully closed the program");
                    break;
                }
                System.out.println("Please type second number now and press ENTER");
                String number2 = scanner.nextLine();
                if (number2.equals("exit")) {
                    System.out.println("You have successfully closed the program");
                    break;
                }
                System.out.println("Your result:");
                client.send(resultOperation, number1, number2);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

