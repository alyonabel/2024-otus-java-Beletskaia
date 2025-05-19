package ru.otus.java.hw16.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String clientName;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                System.out.println("Клиент подключился на порту " + socket.getPort());
                //Цикл аутентификации
                while (true) {
                    sendMsg("Для начала работы нужно пройти аутентификацию. Формат команды /auth login password \n" + "Для регистрации формат команды /reg login password username");
                    String message = in.readUTF();
                    System.out.println(message);
                    if (message.startsWith("/")) {
                        if (message.equalsIgnoreCase("/exit")) {
                            sendMsg("/exitComplete");
                            disconnect();
                            break;
                        }
                        // auth login password
                        if (message.startsWith("/auth ")) {
                            String[] element = message.split(" ");
                            if (element.length != 3) {
                                sendMsg("Неверный формат команды /auth");
                                continue;
                            }
                            if (server.getAuthenticatedProvider().authenticate(this, element[1], element[2])) {
                                break;
                            }
                        }
                        // /reg login password username
                        if (message.startsWith("/reg ")) {
                            String[] element = message.split(" ");
                            if (element.length != 4) {
                                sendMsg("Неверный формат команды /reg");
                                continue;
                            }
                            if(server.getAuthenticatedProvider().registration(this,element[1],element[2],element[3])){
                                break;
                            }
                        }
                    }
                }
                //Цикл работы
                while (true) {
                    String message = in.readUTF();
                    System.out.println(message);
                    if (message.startsWith("/")) {
                        if (message.equalsIgnoreCase("/exit")) {
                            sendMsg("/exitComplete");
                            disconnect();
                            break;
                        } else if (message.startsWith("/w ")) {
                            String[] splittedMes = message.split(" ", 3);
                            if (splittedMes.length < 3) {
                                sendMsg("Неверный формат команды. Используйте: /w <ник> <сообщение>");
                            } else {
                                server.broadcastMessageOne(clientName, splittedMes[1], splittedMes[2]);
                            }
                        }
                    } //kick username
                    if (message.startsWith("/kick ")) {
                        String[] element = message.split(" ");
                        if (element.length != 2) {
                            sendMsg("Неверный формат команды /kick");
                            continue;
                        }
                        if(server.getAuthenticatedProvider().kickOther(this,element[1])){
                            continue;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        server.unSubscribe(this);
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

