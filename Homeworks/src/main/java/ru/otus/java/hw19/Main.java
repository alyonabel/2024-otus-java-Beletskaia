package ru.otus.java.hw19;

public class Main {

    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>(new Apple(10), new Apple(15), new Apple(15));
        Box<Orange> orangeBox = new Box<>(new Orange(12), new Orange(12), new Orange(15));
        Box<Orange> orangeBox2 = new Box<>(new Orange(8), new Orange(13), new Orange(10));
        Box<Fruit> fruitBox = new Box<>(new Orange(15), new Orange(12), new Apple(12));

        appleBox.weigth();
        orangeBox.weigth();
        System.out.println("--\n");

        appleBox.compare(orangeBox);
        fruitBox.compare(orangeBox);
        System.out.println("--\n");

        orangeBox2.move(orangeBox);
        orangeBox2.weigth();
        orangeBox.weigth();
        System.out.println(orangeBox.getFruitBox().size());
        System.out.println(orangeBox2.getFruitBox().size());

        appleBox.move(orangeBox);
        appleBox.weigth();
        orangeBox.weigth();
        System.out.println(appleBox.getFruitBox().size());
        System.out.println(orangeBox.getFruitBox().size());

        orangeBox.move(fruitBox);
        System.out.println(fruitBox.getFruitBox().size());
        System.out.println(orangeBox.getFruitBox().size());
    }
}
