package org.nisum.employeeservice.infrastructure.messaging;

import org.nisum.employeeservice.application.EmployeeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageListener implements MessageListener {

    private EmployeeService employeeService;

    public RabbitMQMessageListener(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RabbitListener(queues = "employeeQueue")
    public void handleMessage(String message) {
        // Delegate the message processing to the employee service
        employeeService.processEmployeeMessage(message);
    }
}
