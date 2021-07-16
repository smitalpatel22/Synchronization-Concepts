package com.study.synchronization;

public class a3MixedSynchronizationExchanger {

    public static Object staticObj = null;

    public static synchronized void setStaticObj(Object o) {
        staticObj = o;
    }

    public Object instanceObj = null;

    public synchronized  void setInstanceObj(Object o) {
        instanceObj = o;
    }

    /*
    ==> Here setStaticObj is synchronized on MixedSynchronizationExchanger.class object
    ==> Whereas the setInstanceObj is synchronized on calling object.
    ==> As both methods are synched on different monitors, hence 2 threads can enter them simultaneously even using same calling obj.
    ==> If we call using 2 threads using same MixedSynchronizationExchanger object, then only 1 thread can execute
     in static or instance method case. Same behaviour.
    ==> If we call using 2 threads using different MixedSynchronizationExchanger object,
     then only 1 call in static case. But both thread can execute in case of instance method case*/
}
