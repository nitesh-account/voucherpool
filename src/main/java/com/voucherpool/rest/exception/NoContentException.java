package com.voucherpool.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceNotFoundException class is used to handle not found exception
 *
 * @author Nitesh Kumar
 */

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(NoContentException.class);

	private static final long serialVersionUID = -3916525550413865316L;


    public NoContentException() {
        super();
    }

    public NoContentException(String message) {
        super(message);
    }

    public NoContentException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
