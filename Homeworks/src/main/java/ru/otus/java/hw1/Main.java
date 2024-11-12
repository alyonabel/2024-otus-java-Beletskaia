package ru.otus.java.hw1;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void greetings(String first, String second, String third, String fourth) {
        System.out.println(first);
        System.out.println(second);
        System.out.println(third);
        System.out.println(fourth);
    }

    public static void checkSign(int a, int b, int c) {
        int d = a + b + c;
        if (d >= 0) System.out.println("Сумма положительная");
        else System.out.println("Сумма отрицательная");
    }

    public static void selectColor() {
        int data = (int) (Math.random() * 30);
        if (data <= 10) System.out.println("Красный");
        else if (data <= 20) System.out.println("Желтый");
        else System.out.println("Зеленый");
    }

    public static void compareNumbers() {
        int a = (int) (Math.random() * 100);
        int b = (int) (Math.random() * 100);
        if (a >= b) System.out.println("a >= b");
        else System.out.println("a < b");
    }

    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        if (increment) System.out.println(initValue + delta);
        else System.out.println(initValue - delta);
    }

    public static void main(String[] args) {
        int a=(int) (Math.random() * 100);
        int b = (int) (Math.random() * 100)-100;

        Random random = new Random();
        boolean increment= random.nextBoolean();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число от 1 до 5 и потом нажмите Enter");
        int result = scanner.nextInt();
        if (result == 1) greetings("Hello", "World", "from", "Java");
        else if (result == 2) checkSign(a, b, a);
        else if (result == 3) selectColor();
        else if (result == 4) compareNumbers();
        else if (result == 5) addOrSubtractAndPrint(a, a, increment);
        else System.out.println("Вы неверно ввели номер");
    }
}
