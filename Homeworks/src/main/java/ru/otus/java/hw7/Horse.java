package ru.otus.java.hw7;

public class Horse implements Transport {

    private int horsepower;

    public Horse (int horsepower){
        this.horsepower = horsepower;
    }

    @Override
    public boolean move(int distance, Terrain type) {
        if (type == Terrain.SWAMP) {
            System.out.println("Moving through this type of terrain is not possible");
            return false;
        } else if (horsepower == 0) return false;
        else {
            horsepower--;
            System.out.println("Riding a horse has been completed at a distance "+ distance + " by type of terrain "  + type);
            return true;
        }

    }
}
