package ru.otus.java.hw6;


public class Main {
    public static void main(String[] args) {
        Plate plate = new Plate(30);
        plate.addFood(30);
        Cat[] cats = new Cat[3];
        Cat cat1 = new Cat("Murzik", 10);
        Cat cat2 = new Cat("Vaska", 15);
        Cat cat3 = new Cat("Lapka", 11);
        cats[0] = cat1;
        cats[1] = cat2;
        cats[2] = cat3;

        for (Cat cat : cats) {
            cat.eat(plate);
        }

        cat1.infoAppetite();
        cat2.infoAppetite();
        cat3.infoAppetite();
    }
}
