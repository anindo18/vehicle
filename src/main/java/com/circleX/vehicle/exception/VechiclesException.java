package com.circleX.vehicle.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VechiclesException extends Exception {

	private static final long serialVersionUID = 1L;

	private HttpStatus statusCode;

	public VechiclesException() {
		super();
	}

	public VechiclesException(HttpStatus code, String message) {
		super(message);
		this.statusCode = code;
	}

}
