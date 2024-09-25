package com.chalapathi.concurrent;

import com.chalapathi.concurrent.dto.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RunAsyncDemo {
    public Void saveEmployees(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        CompletableFuture<Void> runAsyncCompletableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Employee> employees = mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
                    });
                    //write logic to save list of employee object
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    //employees.forEach(System.out::println);
                    System.out.println(employees.size());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return runAsyncCompletableFuture.get();
    }

    public Void saveEmployeesWithLamda(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        Executor executor= Executors.newFixedThreadPool(10);
        CompletableFuture<Void> runAsyncCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                List<Employee> employees = mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
                });
                //write logic to save list of employee object
                System.out.println("Thread: " + Thread.currentThread().getName());
               // employees.forEach(System.out::println);
                System.out.println(employees.size());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return runAsyncCompletableFuture.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunAsyncDemo runAsyncDemo = new RunAsyncDemo();
        System.out.println(runAsyncDemo.saveEmployees(new File("employees.json")));
        runAsyncDemo.saveEmployeesWithLamda(new File("employees.json"));
    }
}
