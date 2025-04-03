package ru.otus.java.hw14;

public class MyTask implements Runnable {

    int startIndex;
    double[] array;

    public MyTask(double[] array, int startIndex) {
        this.array = array;
        this.startIndex = startIndex;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        for (int i = startIndex; i < startIndex + array.length / 4; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            System.out.println(thread + ",i equals " + array[i]);
        }
    }
}
