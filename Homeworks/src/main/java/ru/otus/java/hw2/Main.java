package ru.otus.java.hw2;


import java.util.Arrays;

public class Main {
    public static void printString(int a, String b) {
        while (a > 0) {
            System.out.println(b);
            a--;
        }
    }

    public static void printSumArray(int[] arr) {
        int result = 0;
        for (int i : arr)
            if (i > 5)
                result += i;

        System.out.println("Сумма всех элементов, значение которых больше 5: " + result);
    }

    public static int[] setArray(int a, int[] arr) {
        Arrays.fill(arr, a);
        return arr;
    }

    public static int[] increaseArray(int a, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] += a;
        }
        return arr;
    }

    public static void printHalfArray(int[] arr) {
        int sum1 = 0;
        int k = arr.length / 2;
        for (int i = 0; i <= k; i++)
            sum1 = sum1 + arr[i];

        int sum2 = 0;
        for (int i = k; i < arr.length; i++)
            sum2 = sum2 + arr[i];

        if (sum1 > sum2)
            System.out.println("Сумма элементов первой половины больше, так как " + sum1 + " больше " + sum2);
        else if (sum2 > sum1)
            System.out.println("Сумма элементов второй половины больше, так как " + sum2 + " больше " + sum1);
        else
            System.out.println("Суммы элементов первой и второй половины равныб так как " + sum1 + " и " + sum2 + " равны");
    }

    public static void main(String[] args) {
        printString(5, "Hello, I love you!");

        int[] arr = {1, 10, 3, 6, 0, 4, 5, 5, 8};
        printSumArray(arr);
        printHalfArray(arr);

        System.out.println("Исходный массив");
        int[] arr2 = {1, 2, 5, 4, 6, 1, 1, 1, 2};
        for (int i : arr2)
            System.out.print(i + "");
        int[] array = setArray(6, arr2);
        System.out.println("\nНовый массив с заменёнными значениями");
        for (int i : array)
            System.out.print(i + "");
        System.out.println("\n");

        System.out.println("Исходный массив");
        int[] arr3 = {4, 4, 0, 0, 0, 0, 1};
        for (int i : arr3)
            System.out.print(i + "");
        int[] array5 = increaseArray(5, arr3);
        System.out.println("\nНовый массив с увеличенным значениями");
        for (int i : array5)
            System.out.print(i + "");
    }
}