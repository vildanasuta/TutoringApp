package com.spring.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception for tuteeModule not being found
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "TuteeModule not found!")
public class TuteeModuleNotFoundException extends RuntimeException{
}
