package ru.otus.java.hw5;

import java.text.DecimalFormat;

public class Animal {

    private String name;
    private int velocityRun;
    private int velocitySwim;
    private double endurance;

    public Animal(String name, int velocityRun, int velocitySwim, double endurance) {
        this.name = name;
        this.velocityRun = velocityRun;
        this.velocitySwim = velocitySwim;
        this.endurance = endurance;
    }

    DecimalFormat df = new DecimalFormat("#.##");

    public void run(double distance) {
        System.out.println(name + " на старте. Предстоит пробежка на дистанцию " + distance + " метров");
        endurance = endurance - distance;
        if (endurance <= 0) {
            System.out.println("Пробежка закончилась истощением выносливости. Время: - 1");
            info();
        } else {System.out.println("Пробежка закончена успешно. Время в секундах: " + df.format(distance / velocityRun));}
    }

    public void swim(double distance) {
        endurance = endurance - distance;
        if (endurance <= 0) {
            System.out.println("Плавание закончилось неудачей. Время: - 1");
            info();
        } else System.out.println("Плавание завершено. Время: " + df.format(distance / velocitySwim));
    }

    public void info() {
        if (endurance <= 0)
            System.out.println("Животному по имени " + name + " нужно отдохнуть. Запас выносливости равен " + endurance);
        else
            System.out.println("Животное по имени " + name + " имеет запас выносливости равной " + endurance + " единиц");
    }

}
