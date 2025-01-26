package ru.otus.java.hw6;

public class Cat {

    private String name;
    private int appetite;
    private boolean isHungry;

    {
        this.isHungry = true;
    }

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;

    }

    public void eat(Plate plate) {
        if (plate.getCurrentCountFood() >= appetite) {
            plate.setCurrentCountFood(plate.getCurrentCountFood() - appetite);
            isHungry = false;
        } else isHungry = true;
    }

    public void infoAppetite() {
        if (isHungry) System.out.println("Cat with name " + name + " is still hungry");
        else System.out.println("Cat with name " + name + " is not hungry");
    }
}
