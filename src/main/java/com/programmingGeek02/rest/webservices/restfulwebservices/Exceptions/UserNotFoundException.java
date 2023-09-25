package com.programmingGeek02.rest.webservices.restfulwebservices.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException
{
	public UserNotFoundException(String message)
	{
		super(message);
	}
	
}
