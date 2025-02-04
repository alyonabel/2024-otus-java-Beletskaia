package ru.otus.java.hw6;

public class Plate {

    private int currentCountFood;
    private int maxCapacityFood;

    public Plate(int maxCapacityFood) {
        this.maxCapacityFood = maxCapacityFood;
    }

    public void addFood(int food) {
        currentCountFood = currentCountFood + food;
        if (currentCountFood > maxCapacityFood) currentCountFood = maxCapacityFood;
    }

    public boolean isEmptyAfterAdding(int food) {
        currentCountFood = currentCountFood - food;
        return currentCountFood >= 0;
    }

    public int getCurrentCountFood() {
        return currentCountFood;
    }

    public int getMaxCapacityFood() {
        return maxCapacityFood;
    }

    public void setCurrentCountFood(int currentCountFood) {
        this.currentCountFood = currentCountFood;
    }

    public void setMaxCapacityFood(int maxCapacityFood) {
        this.maxCapacityFood = maxCapacityFood;
    }
}
