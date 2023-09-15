package org.nisum.employeeservice.application.impl;

import org.nisum.employeeservice.domain.model.Employee;
import org.nisum.employeeservice.domain.repository.EmployeeRepository;
import org.nisum.employeeservice.application.EmployeeService;
import org.nisum.employeeservice.integration.notification.NotificationService;
import org.nisum.employeeservice.integration.payment.PaymentService;
import org.nisum.employeeservice.interfaces.dto.EmployeeDTO;
import org.nisum.employeeservice.mapper.EmployeeConverter;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private NotificationService notificationService;
    private PaymentService paymentService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               NotificationService notificationService,
                               PaymentService paymentService) {
        this.employeeRepository = employeeRepository;
        this.notificationService = notificationService;
        this.paymentService = paymentService;
    }

    public void createEmployee(EmployeeDTO employeeDTO) {

        // Convert the DTO to the domain model using the converter
        Employee employee = EmployeeConverter.toEntity(employeeDTO);

        // Save the employee in the repository
        employeeRepository.save(employee);

        // Send a notification
        String notificationMessage = "New employee created: " + employee.getName();
        notificationService.sendNotification(notificationMessage);

        // Process a payment
        double paymentAmount = calculatePaymentAmount(employee);
        paymentService.processPayment(paymentAmount);
    }

    private double calculatePaymentAmount(Employee employee) {
        // Calculate the payment amount based on employee data
        // Example: return a fixed amount for demonstration purposes
        return 1000.0;
    }

    public Employee getEmployeeById(String id) {
        return null;
    }

    public void createEmployee(String name, String email) {

    }

    public void updateEmployee(Employee employee) {

    }

    public void deleteEmployee(String id) {

    }
    public void processEmployeeMessage(String message) {
        // Process the employee message
        System.out.println("Received employee message: " + message);
        // Other processing logic
    }
    // Other service methods


    public static Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Future<String> completableFuture = EmployeeServiceImpl.calculateAsync();
        System.out.println("Hello: " + completableFuture.get());

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        System.out.println("Hello 1: " + future.get());


        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = completableFuture2.thenApply(s -> s + " World");
        System.out.println("Hello 2: " + future2.get());


        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<Void> future3 = completableFuture3.thenRun(() -> System.out.println("Computation finished."));
        System.out.println(future3.get());


        CompletableFuture<String> completableFuture4 = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        System.out.println("Hello World..."+ completableFuture4.get());



        CompletableFuture<String> completableFuture5 = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);
        System.out.println("Hello World..."+ completableFuture5.get());


        CompletableFuture future6 = CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> System.out.println(s1 + s2));

        CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future8 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future9 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future7, future8, future9);

        System.out.println(combinedFuture.get());

        String combined = Stream.of(future7, future8, future9)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        System.out.println(combined);

        assertEquals("Hello Beautiful World", combined);

    }

    private static boolean  assertEquals(String source, String destination) {
        return source.equals(destination);
    }
}
