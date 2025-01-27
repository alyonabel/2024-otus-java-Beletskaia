package ru.otus.java.hw7;

public class Bicycle implements Transport {

    @Override
    public boolean move(int distance, Terrain type) {
        if (type == Terrain.SWAMP) {
            System.out.println("Moving through this type of terrain is not possible");
            return false;
        } else {
            System.out.println("Cycling has been completed at a distance " + distance + " by type of terrain " + type);
            return true;
        }
    }
}