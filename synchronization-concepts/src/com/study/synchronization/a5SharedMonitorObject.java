package com.study.synchronization;

public class a5SharedMonitorObject {
    public Object monitor = null;

    private int counter = 0;

    public a5SharedMonitorObject(Object monitor) {
        if(monitor == null) {
            throw new IllegalArgumentException("Monitor Object cannot be null");
        }
        this.monitor = monitor;
    }

    public void incCounter(){
        synchronized (monitor) {
            counter++;
        }
    }

    public static void main(String[] args) {
        Object monitor1 = new Object();
        a5SharedMonitorObject smo1 = new a5SharedMonitorObject(monitor1);
        a5SharedMonitorObject smo2 = new a5SharedMonitorObject(monitor1);           // Dont use "string" or int etc it may not synchronize
        smo1.incCounter();
        smo2.incCounter();      //either of them blocked

        Object monitor2 = new Object();
        a5SharedMonitorObject smo3 = new a5SharedMonitorObject(monitor2);
        smo3.incCounter();      //not blocked as different monitor Object
    }
}
