package ru.otus.java.hw9;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static ArrayList<Integer> makeArray(int min, int max) {
        ArrayList <Integer> arrayList = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            arrayList.add(i);
        }
        return arrayList;
    }

    public static int sumElements(ArrayList<Integer> array) {
        int sum = 0;
        for (Integer i : array) {
            if (i > 5) {
                sum += i;
            }
        }
        return sum;
    }

    public static void rewriteArray(int a, ArrayList<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            array.set(i,a);
        }
    }

    public static void decreaseArray(int a, ArrayList<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            array.set(i,array.get(i)+a);
        }
    }

    public static void main(String[] args) {
        System.out.println(makeArray(1, 8));
        ArrayList<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 10, 12, 4, 18));
        System.out.println(sumElements(elements));
        rewriteArray(4,elements);
        System.out.println(elements);
        decreaseArray(2,elements);
        System.out.println(elements);

        Employee employee1 = new Employee("John", 28);
        Employee employee2 = new Employee("Mike", 31);
        Employee employee3 = new Employee("Anna", 21);
        ArrayList<Employee> empl = new ArrayList<>(Arrays.asList(employee1,employee2,employee3));
        System.out.println(Employee.getNames(empl));
        System.out.println(Employee.sortEmpAge(empl,25));
        Employee.isAverageAge(empl,27);
        System.out.println("The most younger is " + Employee.findYoung(empl).getName() + " with age "+ Employee.findYoung(empl).getAge() );






    }


}
