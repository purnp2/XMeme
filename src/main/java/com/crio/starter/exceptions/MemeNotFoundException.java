package com.crio.starter.exceptions;

import java.lang.RuntimeException;

public class MemeNotFoundException extends RuntimeException {
    public MemeNotFoundException(String message) {
        super(message);
    }
}
