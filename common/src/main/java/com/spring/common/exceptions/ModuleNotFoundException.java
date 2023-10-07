package com.spring.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception for module not being found
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Module not found!")
public class ModuleNotFoundException extends RuntimeException{
}
