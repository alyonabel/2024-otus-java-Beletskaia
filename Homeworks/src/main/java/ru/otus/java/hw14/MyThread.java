package ru.otus.java.hw14;

public class MyThread extends Thread {
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        double [] arr = new double[100_000_000];
        for(int i=0; i<arr.length;i++){
            arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            System.out.println(thread + ",i equals " + arr[i]);
        }
    }
}
