package ru.otus.java.hw4;

import java.awt.*;

public class Box {

    private Color color;
    private int height;
    private int width;
    private static boolean idOpened;
    private static boolean isEmpty;


    public Box(Color color, int height, int width, boolean idOpened, boolean isEmpty) {
        this.color = color;
        this.height = height;
        this.width = width;
        Box.idOpened = idOpened;
        this.isEmpty = isEmpty;
    }

    public static void open() {
        if (idOpened) System.out.println("Коробка уже открыта!");
        else {
            idOpened = true;
            System.out.println("Коробка открыта успешно");
        }
    }

    public static void close() {
        if (!idOpened) System.out.println("Коробка уже закрыта!");
        else {
            idOpened = false;
            System.out.println("Коробка закрыта успешно");
        }
    }

    public static void repaint(Box box, Color color) {
        System.out.println("Исходный цвет коробки: " + box.color.toString());
        box.color = color;
        System.out.println("Коробка перекрашена в цвет: " + color.toString());
    }

    public static void fill() {
        if (isEmpty) {
            isEmpty = false;
            System.out.println("Предмет поместили в коробку");
        } else System.out.println("Коробка уже наполнена, в неё нельзя положить ещё один предмет!");
    }

    public static void clean() {
        if (!isEmpty) {
            isEmpty = true;
            System.out.println("Предмет вынули из коробки");
        } else System.out.println("Коробка пуста, из неё нельзя достать предмет!");
    }
}
