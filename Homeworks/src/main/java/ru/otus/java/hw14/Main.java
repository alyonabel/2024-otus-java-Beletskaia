package ru.otus.java.hw14;

public class Main {

    private static long time;

    public static void stamp() {
        time = System.nanoTime();
    }

    public static long getTime() {
        return System.nanoTime() - time;
    }

    public static void main(String[] args) throws InterruptedException {
        stamp();
//      Implementation#1
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.join();
        long time1 = getTime();
        System.out.println("Time for 1 thread: " + time1);

        stamp();
//      Implementation#2
        Thread thread1 = new Thread(new MyTask(0));
        Thread thread2 = new Thread(new MyTask(25_000_000));
        Thread thread3 = new Thread(new MyTask(50_000_000));
        Thread thread4 = new Thread(new MyTask(70_000_000));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        long time2 = getTime();

        System.out.println("Time for 4 threads: " + time2);
        System.out.println("The difference between first time and second: " + (time1 - time2));
        System.out.println("Count of availableProcessors on this computer: " + Runtime.getRuntime().availableProcessors());
    }
}
