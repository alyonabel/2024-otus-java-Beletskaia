package ru.otus.java.hw18.server;

import ru.otus.java.hw18.db.Role;
import ru.otus.java.hw18.db.User;
import ru.otus.java.hw18.db.UserServiceJDBCImpl;

import java.sql.SQLException;
import java.util.List;

public class InDBAuthenticatedProvider implements AuthenticatedProvider {

    private UserServiceJDBCImpl userServiceJDBC;
    private List<User> users;
    private Server server;

    public InDBAuthenticatedProvider(Server server) throws SQLException {
        this.userServiceJDBC = new UserServiceJDBCImpl();
        this.server = server;
        this.users = userServiceJDBC.getAll();
    }

    @Override
    public void initialize() {
        System.out.println("Initialization InDBAuthenticatedProvider");
    }

    private String getUsernameLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (user.getEMail().equals(login) && user.getPassword().equals(password)) {
                return user.getUsername();
            }
        }
        return null;
    }

    @Override
    public boolean authenticate(ClientHandler clientHandler, String login, String password) {
        //auth login password
        String authUsername = getUsernameLoginAndPassword(login, password);
        if (authUsername == null) {
            clientHandler.sendMsg("Неверный логин/пароль");
            return false;
        }
        if (server.isUsernameBusy(authUsername)) {
            clientHandler.sendMsg("Указанная учетная запись уже используется");
            return false;
        }
        clientHandler.setClientName(authUsername);
        clientHandler.setRole(getRoleByName(login));
        server.subscribe(clientHandler);
        clientHandler.sendMsg("/authOk " + authUsername);
        return true;
    }

//    private boolean isLoginAlreadyExists(String login) {
//        for (User user : users) {
//            if (user.login.equals(login))
//                return true;
//        }
//        return false;
//    }
//
//    private boolean isUserNameAlreadyExists(String username) {
//        for (User user : users) {
//            if (user.username.equals(username))
//                return true;
//        }
//        return false;
//    }
//    @Override
//    public boolean registration(ClientHandler clientHandler, String login, String password, String username) {
//        //reg login password username
//        if (login.length() < 3 || password.length() < 3 || username.length() < 3) {
//            clientHandler.sendMsg("Логин 3+ символа, пароль 3+ символа, имя пользователя 3+ символа");
//            return false;
//        }
//        if (isLoginAlreadyExists(login)) {
//            clientHandler.sendMsg("Указанный логин уже занят");
//            return false;
//        }
//        if (isUserNameAlreadyExists(username)) {
//            clientHandler.sendMsg("Указанное имя пользователя уже занято");
//            return false;
//        }
//        users.add(new User(login, password, username));
//        clientHandler.setClientName(username);
//        server.subscribe(clientHandler);
//        clientHandler.sendMsg("/regOk " + username);
//        return true;
//    }
//

    private Role getRoleByName(String login) {
        for (User user : users) {
            if (user.getEMail().equals(login)) {
                return user.getRoles().get(0);
            }
        }
        return null;
    }

    private Integer getIdUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getId();
            }
        }
        return null;
    }
    private boolean isUserAdmin(String login) {
        return (userServiceJDBC.isAdmin(getIdUser(login)));
    }

    @Override
    public boolean kickOther(ClientHandler clientHandler, String username) {
        //kick username
        if (!isUserAdmin(clientHandler.getClientName())) {
            clientHandler.sendMsg("У вас недостаточно прав для отключения пользователя из чата");
            return false;
        }

        ClientHandler targetClient = server.findClientByUsername(username);
        if (targetClient == null) {
            clientHandler.sendMsg("Пользователь с именем " + username + " не найден");
            return false;
        }
        if (clientHandler.getClientName().equals(username)) {
            clientHandler.sendMsg("Вы не можете исключить самого себя");
            return false;
        }
        targetClient.sendMsg("/kickOk " + username);
        server.unSubscribe(targetClient);
        clientHandler.sendMsg("Пользователь " + username + " успешно исключен из чата");
        return true;
    }
}
