package com.spring.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception for task not being found
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Task not found!")
public class TaskNotFoundException extends RuntimeException{
}
