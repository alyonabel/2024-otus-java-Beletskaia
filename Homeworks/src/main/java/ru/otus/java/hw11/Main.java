package ru.otus.java.hw11;

import java.util.Arrays;

public class Main {
    private static long time;

    public static void stamp() {
        time = System.nanoTime();
    }

    public static void printTime() {
        time = System.nanoTime() - time;
        System.out.println("Time: " + time);
    }

    public static void main(String[] args) {
        PersonDataBase personDataBase = new PersonDataBase();
        Person person1 = new Person("John", Position.BRANCH_DIRECTOR, 1L);
        Person person2 = new Person("Mike", Position.DRIVER, 2L);
        Person person3 = new Person("Kate", Position.MANAGER, 3L);
        personDataBase.add(person1);
        personDataBase.add(person2);
        personDataBase.add(person3);
        System.out.println("The person with id = " + 2L + " has name " + (personDataBase.findById(2L)).getName());
        System.out.println(personDataBase.isManager(person1));
        System.out.println(personDataBase.isManager(person2));
        System.out.println(personDataBase.isManager(person3));
        System.out.println(personDataBase.isEmployee(2L));


        int[] array = {55, 0, 113, 18, 5, 6, 4, 92, 43, 23, 60};
        System.out.println("Initial array " + Arrays.toString(array));
        stamp();
        SortClass.bubbleSort(array);
        printTime();
        System.out.println("Sorted array after sorting with the bubble method " + Arrays.toString(array));


        int[] array2 = {55, 0, 113, 18, 5, 6, 4, 92, 43, 23, 60};
        System.out.println("Initial array " + Arrays.toString(array2));
        stamp();
        SortClass.quickSort(array2);
        printTime();
        System.out.println("Sorted array after Hoare sorting O(n * log(n))  " + Arrays.toString(array2));
    }


}
