package ru.otus.java.hw18.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceJDBCImpl implements UserServiceJDBC {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/otus-db";
    private static final String USERS_QUERY = "select * from users;";
    private static final String USER_ROLE_QUERY = "select r.id, r.\"name\" from roles r\n" +
            " join users_to_roles ur on r.id=ur.role_id\n" +
            " where user_id = ?";

    private static final String IS_ADMIN_QUERY = " select count(1) from roles r\n" +
            " join users_to_roles ur on r.id=ur.role_id\n" +
            " where user_id=? and r.\"name\" = 'admin'";

    private final Connection connection;

    public UserServiceJDBCImpl() throws SQLException {
        this.connection = DriverManager.getConnection(DATABASE_URL, "admin", "password");
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(USERS_QUERY)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String password = resultSet.getString(2);
                    String mail = resultSet.getString(3);
                    String username = resultSet.getString(4);
                    User user = new User(id, password, mail, username);
                    allUsers.add(user);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(USER_ROLE_QUERY)) {
            for (User user : allUsers) {
                preparedStatement.setInt(1, user.getId());
                List<Role> roleList = new ArrayList<>();
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        Role role = new Role(id, name);
                        roleList.add(role);
                    }
                    user.setRoles(roleList);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public boolean isAdmin(int userId) {
        int flag = 0;
        try (PreparedStatement ps = connection.prepareStatement(IS_ADMIN_QUERY)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    flag = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag == 1;
    }
}
