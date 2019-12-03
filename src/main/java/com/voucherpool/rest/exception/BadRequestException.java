package com.voucherpool.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * BadRequestException class is used to handle bad request exception
 *
 * @author Nitesh Kumar
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(BadRequestException.class);

	private static final long serialVersionUID = -3916525550413865316L;


    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
