package org.nisum.employeeservice.infrastructure.messaging;

public interface MessageListener {
    void handleMessage(String message);
    // Other messaging-related methods
}
