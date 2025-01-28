package ru.otus.java.hw7;

public class Human {

    private String name;
    private Transport currentTransport;

    public Human(String name) {
        this.name = name;
    }

    public void walk(int distance, Terrain type) {
        System.out.println("Just walking at the distance " + distance + " meters through " + type);
    }

    public void useTransport(Transport transport) {
        setCurrentTransport(transport);
    }

    public void throwTransport() {
        setCurrentTransport(null);
    }

    public void relocate(int distance, Terrain type) {
        if (currentTransport == null) {
            walk(distance, type);
        } else {
            currentTransport.move(distance, type);
        }
    }

    public Transport getCurrentTransport() {
        return currentTransport;
    }

    public void setCurrentTransport(Transport currentTransport) {
        this.currentTransport = currentTransport;
    }

}
