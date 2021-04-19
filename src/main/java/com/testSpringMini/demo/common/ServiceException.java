
package com.testSpringMini.demo.common;


import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;


    public ServiceException(final String message, Throwable th) {
        super(message, th);
        this.message = message;
    }

    public ServiceException(final String message) {
        this.message = message;
    }

    public static void throwEx(String message) {
        throw new ServiceException(message);
    }


}

