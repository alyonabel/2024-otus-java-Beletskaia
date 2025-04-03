package ru.otus.java.hw14;

public class MyThread extends Thread {

    double[] array;

    public MyThread(double[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        for (int i = 0; i < array.length; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            System.out.println(thread + ",i equals " + array[i]);
        }
    }
}
