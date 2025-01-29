package ru.otus.java.hw8;

public class Main {

    public static int sumArray(String[][] array) throws AppArraySizeException, AppArrayDataException {
        int[][] result = new int[4][4];
        int sumResult = 0;
        if ((array.length == 4) & (array[0].length == 4)) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                   try {
                        result[i][j] = Integer.parseInt(array[i][j]);
                    } catch (NumberFormatException e) {
                       throw new AppArrayDataException();
                  }
                }
            }
        } else throw new AppArraySizeException();

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                sumResult = sumResult + result[i][j];
            }
        }
        return sumResult;
    }

    public static void main(String[] args) throws AppArrayDataException, AppArraySizeException {
        try {
        String[][] arr = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        System.out.println("The sum of all values in the array is equal " + sumArray(arr));

        String[][] arr2 = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        System.out.println("The sum of all values in the array is equal " + sumArray(arr2));

        String[][] arr3 = {{"1", "2", "Hello", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        System.out.println("The sum of all values in the array is equal " + sumArray(arr3));

        } catch (AppArraySizeException | AppArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
