package com.chalapathi.concurrent;

import java.util.concurrent.*;

public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> futureObj = Executors.newSingleThreadExecutor().submit(() -> {
            TimeUnit.SECONDS.sleep(4);
            System.out.println("Thread Name: "+Thread.currentThread().getName());
            return "Hello World!";
        });

        while (!futureObj.isDone()) {
            //futureObj.cancel(true);
            System.out.println("Thread Name: "+Thread.currentThread().getName());
            System.out.println("Result hasn't yet returned: Still waiting");
        }
        System.out.println("Thread Name: "+Thread.currentThread().getName());
        System.out.println("Result -> " + futureObj.get());
       // System.out.println(futureObj.get(2, TimeUnit.SECONDS));
    }

}
