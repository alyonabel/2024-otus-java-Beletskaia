package ru.otus.java.hw18.db;

import java.util.List;

public interface UserServiceJDBC {
    List<User> getAll();
    boolean isAdmin(int userId);
}
