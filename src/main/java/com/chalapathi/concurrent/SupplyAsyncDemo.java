package com.chalapathi.concurrent;

import com.chalapathi.concurrent.database.EmployeeDatabase;
import com.chalapathi.concurrent.dto.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SupplyAsyncDemo {

    public List<Employee> getEmployees() throws ExecutionException, InterruptedException {
        Executor executor= Executors.newCachedThreadPool();
        CompletableFuture<List<Employee>> listCompletableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("Executed by :"+ Thread.currentThread().getName());
            return EmployeeDatabase.fetchEmployees();
        }, executor); // executor is optional, if we dont provide it will use fork join pool
        return listCompletableFuture.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SupplyAsyncDemo supplyAsyncDemo = new SupplyAsyncDemo();
        List<Employee> employees = supplyAsyncDemo.getEmployees();
        employees.forEach(System.out::println);

    }
}
