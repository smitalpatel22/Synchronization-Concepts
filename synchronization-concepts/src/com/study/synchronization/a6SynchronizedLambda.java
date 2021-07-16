package com.study.synchronization;

import java.util.function.Consumer;

public class a6SynchronizedLambda {

    private static Object object = null;

    public static synchronized void setObject(Object o) {
        object = o;
    }

    public static void consumeObject(Consumer consumer) {
        consumer.accept(object);
    }

    public static void main(String[] args) {
        consumeObject((obj) -> {
            synchronized (a6SynchronizedLambda.class) {       //to make consumeObject synchronize on same as static method setObject
                System.out.println(obj);
            }
        });

        consumeObject((obj) -> {
            synchronized (String.class) {   //here consumeObject synchronizes on String.class and setObject on SynchronizedLambda.class
                System.out.println(obj);    //so they will not block eachother. ==> useless synchronization
            }                               //Also as Lambda dont belong to any Object, you cannot use this keyword as monitor.
        });
    }
}
