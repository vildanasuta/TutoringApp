package com.spring.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception for tutor not being found
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Tutor not found!")
public class TutorNotFoundException extends RuntimeException{
}
