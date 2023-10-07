package com.spring.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception for tutee not being found
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Tutee not found!")
public class TuteeNotFoundException extends RuntimeException{
}
