package ru.otus.java.hw14;

public class MyTask implements Runnable {

    int startIndex;

    public MyTask(int startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        double[] arr = new double[100_000_000];
        for (int i = startIndex; i < startIndex + arr.length / 4; i++) {
            arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            System.out.println(thread + ",i equals " + arr[i]);
        }
    }
}
