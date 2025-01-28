package ru.otus.java.hw7;

public class Rover implements Transport {

    private int countPetrol;

    public Rover (int countPetrol){
        this.countPetrol = countPetrol;
    }

    @Override
    public boolean move(int distance, Terrain type) {
        if (countPetrol == 0) return false;
        else {
            countPetrol--;
            System.out.println("Traveling by rover has been completed at a distance "+ distance + " by type of terrain "  + type);
            return true;
        }
    }
}