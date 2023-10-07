package com.spring.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception for feedback not being found
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Feedback not found!")
public class FeedbackNotFoundException extends RuntimeException{
}
