package ru.otus.java.hw5;

public class Dog extends Animal {

    public Dog(String name, int velocityRun, int velocitySwim, double endurance) {
        super(name, velocityRun, velocitySwim, endurance);
    }

    @Override
    public void swim(double distance) {
        super.swim(distance * 2);
    }

}


