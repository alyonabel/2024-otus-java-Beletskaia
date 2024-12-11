package ru.otus.java.hw5;

public class Main {

    public static void main(String[] args) {
        Cat cat = new Cat("Мурзик", 3, 0, 30.5);
        cat.run(20.5);
        Dog dog = new Dog("Барсик", 4, 2, 30.7);
        dog.run(10);
        dog.swim(10);
        Horse horse = new Horse("Вороной", 5, 1, 50.2);
        horse.run(20);
        horse.swim(20);
    }
}
