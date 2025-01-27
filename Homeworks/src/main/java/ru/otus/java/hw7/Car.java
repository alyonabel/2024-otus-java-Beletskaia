package ru.otus.java.hw7;


public class Car implements Transport {

    private int countPetrol;

    public Car(int countPetrol) {
        this.countPetrol = countPetrol;
    }

    @Override
    public boolean move(int distance, Terrain type) {
        if ((type == Terrain.DENSE_FOREST) | (type == Terrain.SWAMP)) {
            System.out.println("Moving through this type of terrain is not possible");
            return false;
        } else if (countPetrol == 0) return false;
        else {
            countPetrol--;
            System.out.println("Traveling by car has been completed at a distance " + distance + " by type of terrain " + type);
            return true;
        }
    }
}