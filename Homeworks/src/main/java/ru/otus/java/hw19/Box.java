package ru.otus.java.hw19;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private List<T> fruitBox;

    public List<T> getFruitBox() {
        return fruitBox;
    }

    public void setFruitBox(List<T> fruitBox) {
        this.fruitBox = fruitBox;
    }

    @SafeVarargs
    public Box(T... elements) {
        this.fruitBox = new ArrayList<T>(List.of(elements));
    }

    public int weigth() {
        int result = 0;
        for (T box : fruitBox) {
            result = result + box.weigth;
        }
        System.out.println("The weight of box is " + result);
        return result;
    }

    public boolean compare(Box<?> box) {
        int resultCurrent = weigth();
        int resultOtherBox = box.weigth();
        if (resultCurrent == resultOtherBox) {
            System.out.println("The boxes are equal");
            return true;
        } else
            System.out.println("The boxes aren't equal");
        return false;
    }

    public void move(Box<? extends Fruit> box) {
        for (int i = 0; i < box.getFruitBox().size(); i++) {
            if (box.getFruitBox().get(i).getClass().isInstance(fruitBox.get(i))) {
                fruitBox.add((T) box.getFruitBox().get(i));
                box.getFruitBox().remove(i);
            } else
                System.out.println("No other fruits can be moved into this box.");
        }
    }
}
