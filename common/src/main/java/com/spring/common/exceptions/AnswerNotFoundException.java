package com.spring.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for answer not being found
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Assignment not found!")
public class AnswerNotFoundException extends RuntimeException{
}
