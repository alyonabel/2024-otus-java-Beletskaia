package ru.otus.java.hw7;


public class Car implements Transport {

    private int fuelLevel;

    public Car(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    @Override
    public boolean move(int distance, Terrain type) {
        if ((type == Terrain.DENSE_FOREST) || (type == Terrain.SWAMP)) {
            System.out.println("Moving through this type of terrain is not possible");
            return false;
        } else if (fuelLevel == 0) return false;
        else {
            fuelLevel--;
            System.out.println("Traveling by car has been completed at a distance " + distance + " by type of terrain " + type);
            return true;
        }
    }
}