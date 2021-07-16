package com.study.synchronization;

public class a7ReEntrance {
    private int count = 0;

    public synchronized void inc() {
        count++;
    }

    public synchronized int incAndGet() {   //As while entering this method the thread will have lock on calling object,
        inc();                              //the thread will be allowed to execute inc() as JVM will see the inc() is synchronized
        return count;                       //on same monitor and same thread has lock on same monitor. This means Java
    }                                       //synchronization is ReEntrant in nature.
}
