package org.nisum.employeeservice.infrastructure.messaging;

public interface MessageProducer {
    void sendMessage(String message);
    // Other messaging-related methods
}
