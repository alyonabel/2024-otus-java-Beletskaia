package ru.otus.java.hw16.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryAuthenticatedProvider implements AuthenticatedProvider {

    private class User {
        private String login;
        private String password;
        private String username;
        private Right right;


        public User(String login, String password, String username) {
            this.login = login;
            this.password = password;
            this.username = username;
            this.right = Right.USER;
        }
        public void setRight(Right right) {
            this.right = right;
        }
        public Right getRight() {
            return right;
        }
    }

    private List<User> users;
    private Server server;

    public InMemoryAuthenticatedProvider(Server server) {
        this.server = server;
        users = new CopyOnWriteArrayList<>();
        User userMain = new User("num1", "123", "us1");
        userMain.setRight(Right.ADMIN);
        users.add(userMain);
        users.add(new User("num2", "213", "us2"));
        users.add(new User("num3", "321", "us3"));
    }

    @Override
    public void initialize() {
        System.out.println("Initialization InMemoryAuthenticatedProvider");
    }

    private String getUserNameLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (user.login.equals(login) && user.password.equals(password)) {
                return user.username;
            }
        }
        return null;
    }

    @Override
    public boolean authenticate(ClientHandler clientHandler, String login, String password) {
        //auth login password
        String authUsername = getUserNameLoginAndPassword(login, password);
        if (authUsername == null) {
            clientHandler.sendMsg("Неверный логин/пароль");
            return false;
        }
        if (server.isUsernameBusy(authUsername)) {
            clientHandler.sendMsg("Указанная учетная запись уже используется");
            return false;
        }
        clientHandler.setClientName(authUsername);
        server.subscribe(clientHandler);
        clientHandler.sendMsg("/authOk " + authUsername);
        return true;
    }

    private boolean isLoginAlreadyExists(String login) {
        for (User user : users) {
            if (user.login.equals(login))
                return true;
        }
        return false;
    }

    private boolean isUserNameAlreadyExists(String username) {
        for (User user : users) {
            if (user.username.equals(username))
                return true;
        }
        return false;
    }

    @Override
    public boolean registration(ClientHandler clientHandler, String login, String password, String username) {
        //reg login password username
        if (login.length() < 3 || password.length() < 3 || username.length() < 3) {
            clientHandler.sendMsg("Логин 3+ символа, пароль 3+ символа, имя пользователя 3+ символа");
            return false;
        }
        if (isLoginAlreadyExists(login)) {
            clientHandler.sendMsg("Указанный логин уже занят");
            return false;
        }
        if (isUserNameAlreadyExists(username)) {
            clientHandler.sendMsg("Указанное имя пользователя уже занято");
            return false;
        }
        users.add(new User(login, password, username));
        clientHandler.setClientName(username);
        server.subscribe(clientHandler);
        clientHandler.sendMsg("/regOk " + username);
        return true;
    }

    private boolean isUserAdmin(String username) {
        for (User user : users) {
            if (user.username.equals(username) & user.right.equals(Right.ADMIN))
                return true;
        }
        return false;
    }
    @Override
    public boolean kickOther(ClientHandler clientHandler, String username) {
        //kick username
        if(!isUserAdmin(clientHandler.getClientName())) {
            clientHandler.sendMsg("У вас недостаточно прав для отключения пользователя из чата");
            return false;
        }

        clientHandler.setClientName(username);
        server.unSubscribe(clientHandler);
        clientHandler.sendMsg("/kickOk " + username);
        return true;
    }
}
