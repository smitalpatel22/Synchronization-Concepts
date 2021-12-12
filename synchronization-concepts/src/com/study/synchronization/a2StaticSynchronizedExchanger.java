package com.study.synchronization;

public class a2StaticSynchronizedExchanger {

    private static Object obj = null;

    public static synchronized Object getObject() {
        System.out.println("Get : "+obj);
        return obj;
    }

    public static synchronized void setObject(Object o) {
        obj = o;
        System.out.println("Set : "+obj);
    }

    public static Object getObj() {
        synchronized(a2StaticSynchronizedExchanger.class) {
            return obj;
        }
    }

    public static void setObj(Object o) {
        synchronized(a2StaticSynchronizedExchanger.class) {
            obj = o;
        }
    }

    // ==> As the static methods dont belong to the instance, they synchronize
    // on the StaticSynchronizedExchangerClass.class object of the calling instance class
    // ==> So even though 2 threads have seperate objects, only 1 of the thread can aquire the lock as there is only 1
    // StaticSynchronizedExchangerClass.class object.

    public static void main(String[] args) {
        a2StaticSynchronizedExchanger monitor1 = new a2StaticSynchronizedExchanger();
        a2StaticSynchronizedExchanger monitor2 = new a2StaticSynchronizedExchanger();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                monitor1.setObject(""+i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                monitor2.getObject();
            }
        });

        t1.start();
        t2.start();
    }
}
