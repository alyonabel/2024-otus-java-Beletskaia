package ru.otus.java.hw3;

public class Main {

    public static int sumOfPositiveElements(int[][] array) {
        int result = 0;
        for (int[] ints : array) {
            for (int j = 0; j < array[0].length; j++) {
                if (ints[j] > 0) result += ints[j];
            }
        }
        return result;
    }

    public static void printSqr(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static int[][] nullDiagonal(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j) array[i][j] = 0;
            }
        }
        return array;
    }


    public static int findMax(int[][] array) {
        int result = array[0][0];
        for (int[] ints : array) {
            for (int j = 0; j < array[0].length; j++) {
                if (ints[j] > result) result = ints[j];
            }
        }
        return result;
    }

    public static int sumElementsSecondLine(int[][] array) {
        int result = 0;
        if (array.length < 2) result = -1;
        else {
            for (int j = 0; j < array[0].length; j++) {
                result += array[1][j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] myArray = {{10, 10, 15}, {0, 15, 0}, {20, 10, 0}};
        System.out.println("Сумма всех элементов массива, которые  больше 0, равна " + sumOfPositiveElements(myArray));

        int dl= 10;
        System.out.println("Квадрат из символов * со сторонами длины равной " + dl);
        printSqr(dl);

        int[][] myArray55 = {{10, 23, 43}, {2, 12, 23}, {43, 2, 34}};
        System.out.println("Массив до зануления диагонали: ");
        for (int[] ints : myArray55) {
            for (int j = 0; j < myArray55.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }

        nullDiagonal(myArray55);
        System.out.println("Массив после зануления диагонали: ");
        for (int[] ints : myArray55) {
            for (int j = 0; j < myArray55.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }

        System.out.println("Максимальный элемент массива равен " + findMax(myArray));
        System.out.println("Сумма элементов второй строки равна " + sumElementsSecondLine(myArray));
    }
}
