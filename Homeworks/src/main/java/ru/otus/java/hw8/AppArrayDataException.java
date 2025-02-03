package ru.otus.java.hw8;

public class AppArrayDataException extends Exception {
    public AppArrayDataException(int row, int col, String value) {
        super(String.format("Invalid data at [%d, %d]: '%s'", row, col, value));
    }
}
