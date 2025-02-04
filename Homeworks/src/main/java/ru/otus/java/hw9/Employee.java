package ru.otus.java.hw9;

import java.util.ArrayList;

public class Employee {


    private String name;
    private int age;

    Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static ArrayList<String> getNames(ArrayList<Employee> employees) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            arrayList.add(employees.get(i).getName());
        }
        return arrayList;
    }

    public static ArrayList<String> sortEmpAge(ArrayList<Employee> employees, int minAge) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() >= minAge) {
                arrayList.add(employee.getName());
            }
        }
        return arrayList;
    }

    public static boolean isAverageAge(ArrayList<Employee> employees, int minAverageAge) {
        int average = 0;
        for (int i = 0; i < employees.size(); i++) {
            average = average + employees.get(i).getAge();
            if (average / employees.size() > minAverageAge) {
                System.out.println("Average age of employees is more than given age");
                return true;
            }
        }
        System.out.println("Average age of employees is less than given age");
        return false;
    }

    public static Employee findYoung(ArrayList<Employee> employees) {
        Employee employeeYoung = null;
        for (int i = 0; i < employees.size(); i++) {
            for (int j = 0; j < employees.size(); j++) {
                if (employees.get(i).getAge() <= employees.get(j).getAge()) {
                    employeeYoung = employees.get(i);
                } else employeeYoung = employees.get(j);
            }
        }
        return employeeYoung;
    }
}

