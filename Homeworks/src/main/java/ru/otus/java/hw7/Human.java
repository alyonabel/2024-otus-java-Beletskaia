package ru.otus.java.hw7;

public class Human {

    private String name;
    private Transport currentTransport;

    public Human(String name) {
        this.name = name;
    }

    public void walk() {
        System.out.println("Just walking");
    }

    public void catchTransport(Transport transport) {
        setCurrentTransport(transport);
    }

    public void throwTransport() {
        setCurrentTransport(null);
    }

    public void relocate(int distance, Terrain type) {
        if (currentTransport == null) walk();
        else currentTransport.move(distance, type);
    }

    public Transport getCurrentTransport() {
        return currentTransport;
    }

    public void setCurrentTransport(Transport currentTransport) {
        this.currentTransport = currentTransport;
    }

}
