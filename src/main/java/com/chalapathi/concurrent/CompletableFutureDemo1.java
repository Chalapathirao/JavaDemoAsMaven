package com.chalapathi.concurrent;

import java.util.concurrent.*;

public class CompletableFutureDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete("Hello World");
        });

        while (!completableFuture.isDone()) {
            System.out.println("Result hasn't yet returned");
        }

        System.out.println("Result -> " + completableFuture.get());
    }

}
