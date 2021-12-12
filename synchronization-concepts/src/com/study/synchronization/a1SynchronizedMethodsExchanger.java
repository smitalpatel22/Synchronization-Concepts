package com.study.synchronization;

public class a1SynchronizedMethodsExchanger {
    Object obj = null;

    public synchronized Object getObj() {
        System.out.println("Get Object : "+obj);
        return obj;
    }

    public synchronized void setObj(Object obj) {
        System.out.println("Set Object : "+obj);
        this.obj = obj;
    }

    public Object getObject() {
        synchronized (this) {
            System.out.println("Get Object : "+obj);
            return obj;
        }
    }

    public void setObject(Object obj) {
        synchronized (this) {
            System.out.println("Set Object : "+obj);
            this.obj = obj;
        }
    }

    /*
        ==> Here all 4 methods are synchronized on the same Monitor object i.e. this (object on which method is called)
        ==> So at a time only 1 thread can execute any 1 of the 4 methods at a time.
        ==> If Thread-2 has its own SynchronizedMethodsClass object then it can call any 1 method at same time
        as the monitor object is different.
        ==> Synchronized instance methods(not block), so they will be synchronized on calling object always.
        ==> Whereas the synchronized blocks can be on any Java Object ==> this or particular object
     */

    public static void main(String[] args) {
        a1SynchronizedMethodsExchanger monitor = new a1SynchronizedMethodsExchanger();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                monitor.setObj(""+i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                monitor.getObj();
            }
        });

        /*
        try adding/removing synchronized keyword from method signature
        without synchronized output ==>
            Set Object : 0
            Get Object : null
            Get Object : 0
        This means without synchronization, 2 threads could enter set & get method at same time. And even though the set method was called first it couldn't complete assigning value 0 to obj variable, & so Thread 2 calling Get method printed null.
        And just after printing null, the Get method printed 0 as the set method must have completed assigning the value 0 to obj.
        This can be called Race Condition.
        This will not happen in case the method is synchronized, the Get method will always print what set method has printed last, because only one thread can enter either method & once entered it will surely complete its task first.
        */

        t1.start();
        t2.start();
    }
}
