package ru.otus.java.hw5;

public class Cat extends Animal {

    public Cat(String name, int velocityRun, int velocitySwim, double endurance) {
        super(name, velocityRun, velocitySwim, endurance);
    }

    @Override
    public void swim(double distance) {
        System.out.println("Кот плавать не умеет в этом классе");;
    }

}

