package com.appsdeveloper.app.ws.mobileappws.exception;

import java.io.Serial;

public class UserServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID= 8269000253468612165L;
    public UserServiceException(String message) {
        super(message);
    }
}
