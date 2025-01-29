package ru.otus.java.hw8;

public class AppArraySizeException extends Exception {
    public AppArraySizeException() {
        System.out.println("This array isn't suitable, his length should be equal 4x4");
    }
}
