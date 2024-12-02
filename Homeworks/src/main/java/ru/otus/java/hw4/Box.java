package ru.otus.java.hw4;

import java.awt.*;

public class Box {

    public Color color;
    public final int height;
    public final int width;
    boolean idOpened;
    boolean isEmpty;


    public Box(Color color, int height, int width, boolean idOpened, boolean isEmpty) {
        this.color = color;
        this.height = height;
        this.width = width;
        this.idOpened = idOpened;
        this.isEmpty = isEmpty;
    }

    public static void openCloseBox(Box box, boolean a) {
        if (box.idOpened == a) {
            if (box.idOpened) System.out.println("Коробка уже открыта!");
            else System.out.println("Коробка уже закрыта!");
        } else {
            if (a) {
                box.idOpened = a;
                System.out.println("Коробка открыта успешно");
            } else {
                box.idOpened = a;
                System.out.println("Коробка закрыта успешно");
            }
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
