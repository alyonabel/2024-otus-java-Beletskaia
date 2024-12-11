package ru.otus.java.hw4;

import java.awt.*;

public class Box {

    private Color color;
    private int height;
    private int width;
    private static boolean idOpened;
    private boolean isEmpty;


    public Box(Color color, int height, int width, boolean idOpened, boolean isEmpty) {
        this.color = color;
        this.height = height;
        this.width = width;
        Box.idOpened = idOpened;
        this.isEmpty = isEmpty;
    }

    public static void openBox() {
        if (idOpened) System.out.println("Коробка уже открыта!");
        else {
            idOpened = true;
            System.out.println("Коробка открыта успешно");
        }
    }

    public static void closeBox() {
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

    public static void fill(Box box) {
        if (box.isEmpty) {
            box.isEmpty = false;
            System.out.println("Предмет поместили в коробку");
        } else System.out.println("Коробка уже наполнена, в неё нельзя положить ещё один предмет!");
    }

    public static void clean(Box box) {
        if (!box.isEmpty) {
            box.isEmpty = true;
            System.out.println("Предмет вынули из коробки");
        } else System.out.println("Коробка пуста, из неё нельзя достать предмет!");
    }
}
