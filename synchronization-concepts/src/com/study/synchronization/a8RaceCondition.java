package com.study.synchronization;

public class a8RaceCondition {
    private int counter = 0;

    public void incCounter() {
//        synchronized (this) {             //Commented to create Race Condtition
            counter=counter+1;
//        }
    }

    public int getCounter() {
        synchronized (this) {
            return counter;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        a8RaceCondition rc = new a8RaceCondition();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                rc.incCounter();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                rc.incCounter();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(rc.getCounter());
    }
}
