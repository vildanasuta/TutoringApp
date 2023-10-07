package com.spring.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception for question not being found
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Question not found!")
public class QuestionNotFoundException extends RuntimeException{
}
