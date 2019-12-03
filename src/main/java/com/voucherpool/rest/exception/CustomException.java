package com.voucherpool.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ResourceNotFoundException class is used to handle not found exception
 *
 * @author Nitesh Kumar
 */

public class CustomException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(CustomException.class);

	private static final long serialVersionUID = -3916525550413865316L;


    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
