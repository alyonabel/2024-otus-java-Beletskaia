package ru.otus.java.hw7;

public class Main {

    public static void main(String[] args) {
        Human human1 = new Human("John");
        Car car = new Car(100);
        human1.setCurrentTransport(car);
        human1.relocate(20, Terrain.PLAIN);
        human1.relocate(5, Terrain.SWAMP);
        human1.throwTransport();
        human1.relocate(20, Terrain.PLAIN);
        Horse horse = new Horse(120);
        human1.useTransport(horse);
        human1.relocate(20, Terrain.DENSE_FOREST);
        human1.relocate(20, Terrain.SWAMP);

    }
}
