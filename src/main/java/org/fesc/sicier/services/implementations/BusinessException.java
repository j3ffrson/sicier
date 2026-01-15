package org.fesc.sicier.services.implementations;

public class BusinessException extends Throwable {
    public BusinessException(String message) {
        System.out.println(message);
    }
}
