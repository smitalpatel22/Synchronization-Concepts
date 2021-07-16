package com.study.synchronization;

public class a4MultipleMonitorObjectsExchanger {
    private Object monitor1 = new Object();
    private Object monitor2 = new Object();

    private int counter1 = 0;
    private int counter2 = 0;

    public void incCounter1() {
        synchronized (monitor1) {
            counter1++;
        }
    }

    public void incCounter2() {
        synchronized (monitor2) {
            counter2++;
        }
    }

    /*
        ==> As the monitor objects are differnt, they will not mutually lock eachother out.
     */
}
