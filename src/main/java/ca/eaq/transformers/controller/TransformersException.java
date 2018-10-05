package ca.eaq.transformers.controller;

import org.springframework.http.HttpStatus;

public class TransformersException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	    public HttpStatus getHttpStatus() {
	        return httpStatus;
	    }

	    /**
	     * Constructs a new runtime exception with the specified detail message.
	     */
	    public TransformersException(HttpStatus httpStatus, String message) {
	        super(message);
	        this.httpStatus = httpStatus;
	    }
	
}
